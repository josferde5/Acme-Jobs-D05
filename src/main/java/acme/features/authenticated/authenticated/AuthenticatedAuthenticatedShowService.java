
package acme.features.authenticated.authenticated;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThreads.MessageThread;
import acme.features.authenticated.messageThread.AuthenticatedMessageThreadRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedAuthenticatedShowService implements AbstractShowService<Authenticated, Authenticated> {

	@Autowired
	AuthenticatedAuthenticatedRepository	repository;

	@Autowired
	AuthenticatedMessageThreadRepository	threadRepository;


	@Override
	public boolean authorise(final Request<Authenticated> request) {
		assert request != null;

		boolean result;
		int threadId;
		MessageThread thread;
		Collection<Authenticated> users;
		Principal principal;
		HttpSession session;

		session = request.getServletRequest().getSession();
		threadId = (int) session.getAttribute("threadId");
		thread = this.threadRepository.findOneById(threadId);
		users = thread.getUsers();
		principal = request.getPrincipal();
		result = users.stream().map(x -> x.getId()).anyMatch(x -> x == principal.getActiveRoleId());
		return result;
	}

	@Override
	public void unbind(final Request<Authenticated> request, final Authenticated entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "userAccount.username", "identity.name", "identity.surname");

		HttpSession session;
		int threadId;
		String add;
		session = request.getServletRequest().getSession();
		threadId = (int) session.getAttribute("threadId");
		add = (String) session.getAttribute("add");

		model.setAttribute("add", add);
		model.setAttribute("threadId", threadId);

		session.removeAttribute("add");
		session.removeAttribute("threadId");

	}

	@Override
	public Authenticated findOne(final Request<Authenticated> request) {
		assert request != null;

		Authenticated result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

}
