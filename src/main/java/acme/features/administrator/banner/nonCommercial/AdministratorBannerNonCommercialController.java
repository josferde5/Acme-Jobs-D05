
package acme.features.administrator.banner.nonCommercial;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.banners.NonCommercial;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/banner/non-commercial")
public class AdministratorBannerNonCommercialController extends AbstractController<Administrator, NonCommercial> {

	@Autowired
	private AdministratorBannerNonCommercialShowService		showService;

	@Autowired
	private AdministratorBannerNonCommercialCreateService	createService;

	@Autowired
	private AdministratorBannerNonCommercialUpdateService	updateService;

	@Autowired
	private AdministratorBannerNonCommercialDeleteService	deleteService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}

}
