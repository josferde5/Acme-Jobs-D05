
package acme.features.employer.duty;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.duties.Duty;
import acme.entities.roles.Employer;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/employer/duty/")
public class EmployerDutyController extends AbstractController<Employer, Duty> {

	@Autowired
	EmployerDutyListDutiesService	listService;

	@Autowired
	EmployerDutyShowService			showService;

	@Autowired
	EmployerDutyCreateService		createService;

	@Autowired
	EmployerDutyDeleteService		deleteService;

	@Autowired
	EmployerDutyUpdateService		updateService;


	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_DUTIES, BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}

}
