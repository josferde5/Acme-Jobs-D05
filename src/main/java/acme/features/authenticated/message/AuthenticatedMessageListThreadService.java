
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messages.Message;
import acme.features.authenticated.messageThread.AuthenticatedMessageThreadRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedMessageListThreadService implements AbstractListService<Authenticated, Message> {

	@Autowired
	AuthenticatedMessageRepository			repository;

	@Autowired
	AuthenticatedMessageThreadRepository	threadRepository;


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;

		boolean result;
		int threadId;
		Collection<Authenticated> users;
		Principal principal;

		threadId = request.getModel().getInteger("id");
		users = this.repository.findManyUsersByThread(threadId);
		principal = request.getPrincipal();
		result = users.stream().map(x -> x.getId()).anyMatch(x -> x == principal.getActiveRoleId());
		return result;
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment");

	}

	@Override
	public Collection<Message> findMany(final Request<Message> request) {
		assert request != null;

		Collection<Message> result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findManyAllByThread(id);

		return result;
	}

}
