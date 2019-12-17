
package acme.features.authenticated.message;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisationParameters.CustomisationParameters;
import acme.entities.messageThreads.MessageThread;
import acme.entities.messages.Message;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessageCreateService implements AbstractCreateService<Authenticated, Message> {

	@Autowired
	AuthenticatedMessageRepository repository;


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;

		boolean result;
		int threadId;
		Collection<Authenticated> users;
		Principal principal;

		threadId = request.getModel().getInteger("threadId");
		users = this.repository.findManyUsersByThread(threadId);
		principal = request.getPrincipal();
		result = users.stream().map(x -> x.getId()).anyMatch(x -> x == principal.getActiveRoleId());
		return result;
	}

	@Override
	public void bind(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "tags", "body");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("confirmation", "false");
			int threadId = request.getModel().getInteger("threadId");
			model.setAttribute("threadId", threadId);
		} else {
			request.transfer(model, "confirmation", "threadId");
		}

	}

	@Override
	public Message instantiate(final Request<Message> request) {
		assert request != null;
		Message result;

		result = new Message();
		return result;
	}

	@Override
	public void validate(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isConfirmed = request.getModel().getBoolean("confirmation");
		errors.state(request, isConfirmed, "confirmation", "authenticated.message.error.must-confirm");

		CustomisationParameters cp;
		String body;
		Set<String> spamWords;
		boolean isSpam;

		cp = this.repository.findCustomParameters();

		spamWords = new HashSet<>();
		body = request.getModel().getString("body");
		Arrays.stream(cp.getSpamWordsEn().split(",")).map(x -> x.trim()).forEach(x -> spamWords.add(x));
		Arrays.stream(cp.getSpamWordsSp().split(",")).map(x -> x.trim()).forEach(x -> spamWords.add(x));

		int count = 0;

		for (String s : spamWords) {
			Pattern p = Pattern.compile("." + s + ".", Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(body);
			while (m.find()) {
				count++;
			}
		}

		isSpam = count / (double) body.split(" ").length * 100. > cp.getThreshold();
		errors.state(request, !isSpam, "body", "authenticated.message.error.spam");

	}

	@Override
	public void create(final Request<Message> request, final Message entity) {
		assert request != null;
		assert entity != null;

		MessageThread thread;
		Collection<Message> messages;
		int threadId;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);

		threadId = request.getModel().getInteger("threadId");
		thread = this.repository.findOneMessageThreadById(threadId);
		messages = thread.getMessages();
		messages.add(entity);
		thread.setMessages(messages);
		this.repository.save(thread);

	}

}
