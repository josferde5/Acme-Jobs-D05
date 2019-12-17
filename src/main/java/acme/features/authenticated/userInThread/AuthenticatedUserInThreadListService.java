
package acme.features.authenticated.userInThread;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThreads.MessageThread;
import acme.entities.usersInThread.UserInThread;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedUserInThreadListService implements AbstractListService<Authenticated, UserInThread> {

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
	public void unbind(final Request<UserInThread> request, final UserInThread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "authenticated.userAccount.username", "authenticated.identity.name", "authenticated.identity.surname");

	}

	@Override
	public Collection<UserInThread> findMany(final Request<UserInThread> request) {
		assert request != null;

		Collection<UserInThread> result;
		int id;
		id = request.getModel().getInteger("threadId");
		result = this.repository.findManyAllByThreadId(id);

		return result;
	}

}
