
package acme.features.authenticated.userInThread;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThreads.MessageThread;
import acme.entities.usersInThread.UserInThread;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedUserInThreadCreateService implements AbstractCreateService<Authenticated, UserInThread> {

	@Autowired
	AuthenticatedUserInThreadRepository repository;


	@Override
	public boolean authorise(final Request<UserInThread> request) {
		assert request != null;

		boolean result;
		int threadId;
		MessageThread thread;
		Collection<Authenticated> users;
		Principal principal;

		threadId = request.getModel().getInteger("threadId");
		thread = this.repository.findOneThreadById(threadId);
		users = this.repository.findAllUsersInThread(thread.getId());
		principal = request.getPrincipal();
		result = users.stream().map(x -> x.getId()).anyMatch(x -> x == principal.getActiveRoleId());
		return result;
	}

	@Override
	public void bind(final Request<UserInThread> request, final UserInThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "messageThread", "authenticated");

	}

	@Override
	public void unbind(final Request<UserInThread> request, final UserInThread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model);

		int id;
		id = request.getModel().getInteger("threadId");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("userId", "");
			model.setAttribute("threadId", id);
		} else {
			request.transfer(model, "userId", "threadId");
		}

		Collection<Authenticated> authenticateds;
		String[] usernamesArray;
		Collection<String> userIds;
		String[] userIdsArray;

		authenticateds = this.repository.findAllUsernamesNotInThread(id);
		userIds = this.repository.findAllAuthenticatedIdsNotInThread(id);
		usernamesArray = new String[authenticateds.size()];
		userIdsArray = new String[userIds.size()];

		usernamesArray = authenticateds.stream().map(x -> x.getUserAccount().getUsername()).collect(Collectors.toList()).toArray(usernamesArray);
		userIdsArray = userIds.toArray(userIdsArray);

		int lengthCol = authenticateds.size() - 1;

		model.setAttribute("usernames", usernamesArray);
		model.setAttribute("userIds", userIdsArray);
		model.setAttribute("listLength", lengthCol);

	}

	@Override
	public UserInThread instantiate(final Request<UserInThread> request) {
		assert request != null;

		UserInThread result;
		Authenticated a;
		MessageThread mt;
		if (request.isMethod(HttpMethod.POST)) {
			int threadId;
			int userId;
			userId = request.getModel().getInteger("userId");
			threadId = request.getModel().getInteger("threadId");
			mt = this.repository.findOneThreadById(threadId);
			a = this.repository.findOneAuthenticated(userId);
		} else {
			mt = new MessageThread();
			a = new Authenticated();
		}

		result = new UserInThread();

		result.setAuthenticated(a);
		result.setMessageThread(mt);
		return result;
	}

	@Override
	public void validate(final Request<UserInThread> request, final UserInThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean userExists;
		Collection<Integer> idOfUsers;
		int userId;

		userId = request.getModel().getInteger("userId");
		idOfUsers = this.repository.findManyAllAuthenticatedId();
		userExists = idOfUsers.contains(userId);
		if (userExists) {
			boolean userIsNotInThread;
			Collection<Integer> idOfUsersInThread;
			int threadId;

			threadId = request.getModel().getInteger("threadId");
			idOfUsersInThread = this.repository.findUserIdInThread(threadId);
			userIsNotInThread = !idOfUsersInThread.contains(userId);

			errors.state(request, userIsNotInThread, "userId", "error.user-in-thread");
		} else {
			errors.state(request, userExists, "userId", "error.user-not-exists");
		}

	}

	@Override
	public void create(final Request<UserInThread> request, final UserInThread entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
