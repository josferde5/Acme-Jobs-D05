
package acme.features.authenticated.authenticated;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThreads.MessageThread;
import acme.features.authenticated.messageThread.AuthenticatedMessageThreadRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuthenticatedAuthenticatedAddUserThreadService implements AbstractUpdateService<Authenticated, Authenticated> {

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

		threadId = request.getModel().getInteger("threadId");
		thread = this.threadRepository.findOneById(threadId);
		users = thread.getUsers();
		principal = request.getPrincipal();
		result = users.stream().map(x -> x.getId()).anyMatch(x -> x == principal.getActiveRoleId());
		return result;
	}

	@Override
	public void bind(final Request<Authenticated> request, final Authenticated entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "userAccount.username", "identity.name", "identity.surname");

	}

	@Override
	public void unbind(final Request<Authenticated> request, final Authenticated entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model);

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

	@Override
	public void validate(final Request<Authenticated> request, final Authenticated entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void update(final Request<Authenticated> request, final Authenticated entity) {
		assert request != null;
		assert entity != null;

		int threadId;
		MessageThread thread;
		Collection<Authenticated> users;

		threadId = request.getModel().getInteger("threadId");
		thread = this.threadRepository.findOneById(threadId);
		users = thread.getUsers();
		users.add(entity);
		thread.setUsers(users);
		this.threadRepository.save(thread);

	}

}
