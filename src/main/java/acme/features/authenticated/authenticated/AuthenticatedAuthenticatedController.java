
package acme.features.authenticated.authenticated;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/authenticated/")
public class AuthenticatedAuthenticatedController extends AbstractController<Authenticated, Authenticated> {

	@Autowired
	private AuthenticatedAuthenticatedAddUserThreadService		addUserService;

	@Autowired
	private AuthenticatedAuthenticatedDeleteUserThreadService	deleteUserService;

	@Autowired
	private AuthenticatedAuthenticatedAddUserListService		addUserListService;

	@Autowired
	private AuthenticatedAuthenticatedListUsersThreadService	listUsersThreadService;

	@Autowired
	private AuthenticatedAuthenticatedShowService				showService;


	@PostConstruct
	private void initialise() {
		this.addBasicCommand(BasicCommand.SHOW, this.showService);
		this.addCustomCommand(CustomCommand.ADD_USER_THREAD, BasicCommand.UPDATE, this.addUserService);
		this.addCustomCommand(CustomCommand.DELETE_USER_THREAD, BasicCommand.UPDATE, this.deleteUserService);
		this.addCustomCommand(CustomCommand.LIST_USERS_THREAD, BasicCommand.LIST, this.listUsersThreadService);
		this.addCustomCommand(CustomCommand.ADD_USER_LIST, BasicCommand.LIST, this.addUserListService);
	}

}
