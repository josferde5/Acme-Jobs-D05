
package acme.features.administrator.customisationParameters;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.customisationParameters.CustomisationParameters;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/customisation-parameters/")
public class AdministratorCustomisationParametersController extends AbstractController<Administrator, CustomisationParameters> {

	@Autowired
	private AdministratorCustomisationParametersDisplayService	displayService;
	@Autowired
	private AdministratorCustomisationParametersUpdateService	updateService;


	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.DISPLAY, BasicCommand.SHOW, this.displayService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);

	}
}
