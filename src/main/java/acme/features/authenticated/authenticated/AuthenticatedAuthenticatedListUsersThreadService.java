
package acme.features.authenticated.authenticated;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThreads.MessageThread;
import acme.features.authenticated.messageThread.AuthenticatedMessageThreadRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedAuthenticatedListUsersThreadService implements AbstractListService<Authenticated, Authenticated> {

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

		threadId = request.getModel().getInteger("id");
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

	}

	@Override
	public Collection<Authenticated> findMany(final Request<Authenticated> request) {
		assert request != null;

		Collection<Authenticated> result;
		int threadId;

		threadId = request.getModel().getInteger("id");
		result = this.repository.findManyUsersInThread(threadId);
		return result;
	}

	@Override
	public void onSuccess(final Request<Authenticated> request, final Response<Authenticated> response) {
		assert request != null;
		assert response != null;

		HttpSession session;
		int threadId;
		threadId = request.getModel().getInteger("id");
		session = request.getServletRequest().getSession();
		session.setAttribute("add", "false");
		session.setAttribute("threadId", threadId);
	}

}
