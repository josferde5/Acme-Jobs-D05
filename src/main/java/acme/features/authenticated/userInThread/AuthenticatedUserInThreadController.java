
package acme.features.authenticated.userInThread;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.usersInThread.UserInThread;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/user-in-thread/")
public class AuthenticatedUserInThreadController extends AbstractController<Authenticated, UserInThread> {

	@Autowired
	private AuthenticatedUserInThreadCreateService	createService;

	@Autowired
	private AuthenticatedUserInThreadDeleteService	deleteService;

	@Autowired
	private AuthenticatedUserInThreadListService	listService;

	@Autowired
	private AuthenticatedUserInThreadShowService	showService;


	@PostConstruct
	private void initialise() {
		this.addBasicCommand(BasicCommand.CREATE, this.createService);
		this.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		this.addBasicCommand(BasicCommand.LIST, this.listService);
		this.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
