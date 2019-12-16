
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
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedUserInThreadShowService implements AbstractShowService<Authenticated, UserInThread> {

	@Autowired
	AuthenticatedUserInThreadRepository repository;


	@Override
	public boolean authorise(final Request<UserInThread> request) {
		assert request != null;

		boolean result;
		int userInThreadId;
		MessageThread thread;
		Collection<Authenticated> users;
		Principal principal;

		userInThreadId = request.getModel().getInteger("id");
		thread = this.repository.findOneMessageThreadByUserInThreadId(userInThreadId);
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

		request.unbind(entity, model, "authenticated.userAccount.username", "authenticated.identity.name", "authenticated.identity.surname", "authenticated.id", "messageThread.id");

	}

	@Override
	public UserInThread findOne(final Request<UserInThread> request) {
		assert request != null;

		UserInThread result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

}
