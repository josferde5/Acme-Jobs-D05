
package acme.features.administrator.auditorRequest;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.auditorRequests.AuditorRequest;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("administrator/auditor-request/")
public class AdministratorAuditorRequestController extends AbstractController<Administrator, AuditorRequest> {

	@Autowired
	AdministratorAuditorRequestAcceptService	acceptService;
	@Autowired
	AdministratorAuditorRequestRejectService	rejectService;
	@Autowired
	AdministratorAuditorRequestListService		listService;
	@Autowired
	AdministratorAuditorRequestShowService		showService;


	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.ACCEPT, BasicCommand.UPDATE, this.acceptService);
		super.addCustomCommand(CustomCommand.REJECT, BasicCommand.DELETE, this.rejectService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
