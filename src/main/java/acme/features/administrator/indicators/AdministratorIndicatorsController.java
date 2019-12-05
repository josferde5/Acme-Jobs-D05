
package acme.features.administrator.indicators;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;
import acme.indicators.Indicator;

@Controller
@RequestMapping("/administrator/indicator/")
public class AdministratorIndicatorsController extends AbstractController<Administrator, Indicator> {

	@Autowired
	private AdministratorIndicatorsDisplayService displayIndicatorsService;


	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.DISPLAY, BasicCommand.SHOW, this.displayIndicatorsService);
	}

}
