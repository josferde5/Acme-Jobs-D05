
package acme.features.authenticated.message;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.messages.Message;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/message/")
public class AuthenticatedMessageController extends AbstractController<Authenticated, Message> {

	@Autowired
	private AuthenticatedMessageListThreadService	listThreadService;

	@Autowired
	private AuthenticatedMessageShowService			showService;

	@Autowired
	private AuthenticatedMessageCreateService		createService;


	@PostConstruct
	private void initialise() {
		this.addCustomCommand(CustomCommand.LIST_THREAD, BasicCommand.LIST, this.listThreadService);
		this.addBasicCommand(BasicCommand.SHOW, this.showService);
		this.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
