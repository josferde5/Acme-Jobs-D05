
package acme.features.authenticated.messageThread;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThreads.MessageThread;
import acme.entities.usersInThread.UserInThread;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessageThreadCreateService implements AbstractCreateService<Authenticated, MessageThread> {

	@Autowired
	AuthenticatedMessageThreadRepository repository;


	@Override
	public boolean authorise(final Request<MessageThread> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<MessageThread> request, final MessageThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment", "messages", "users");

	}

	@Override
	public void unbind(final Request<MessageThread> request, final MessageThread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment");

	}

	@Override
	public MessageThread instantiate(final Request<MessageThread> request) {
		assert request != null;

		MessageThread result;
		result = new MessageThread();
		result.setMessages(new ArrayList<>());
		return result;
	}

	@Override
	public void validate(final Request<MessageThread> request, final MessageThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<MessageThread> request, final MessageThread entity) {
		assert request != null;
		assert entity != null;

		Date moment;
		//		int userId;
		//		Authenticated user;
		//		Collection<Authenticated> users;
		//
		//		userId = request.getPrincipal().getActiveRoleId();
		//		user = this.authRepository.findOneById(userId);
		//		users = entity.getUsers();
		//		users.add(user);
		//		entity.setUsers(users);
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		this.repository.save(entity);

		UserInThread uit;
		Authenticated a;
		int userId;
		userId = request.getPrincipal().getActiveRoleId();
		a = this.repository.getOneAuthenticated(userId);
		uit = new UserInThread();
		uit.setAuthenticated(a);
		uit.setMessageThread(entity);

		this.repository.save(uit);

	}

}
