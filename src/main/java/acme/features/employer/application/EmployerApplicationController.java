
package acme.features.employer.application;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.applications.Application;
import acme.entities.roles.Employer;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/employer/application/")
public class EmployerApplicationController extends AbstractController<Employer, Application> {

	@Autowired
	private EmployerApplicationListService					listService;

	@Autowired
	private EmployerApplicationShowService					showService;

	@Autowired
	private EmployerApplicationUpdateService				updateService;

	@Autowired
	private EmployerApplicationListGroupCreationService		listCreationService;

	@Autowired
	private EmployerApplicationListGroupReferenceService	listReferenceService;

	@Autowired
	private EmployerApplicationListGroupStatusService		listStatusService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addCustomCommand(CustomCommand.LIST_GROUP_CREATION, BasicCommand.LIST, this.listCreationService);
		super.addCustomCommand(CustomCommand.LIST_GROUP_REFERENCE, BasicCommand.LIST, this.listReferenceService);
		super.addCustomCommand(CustomCommand.LIST_GROUP_STATUS, BasicCommand.LIST, this.listStatusService);

	}

}
