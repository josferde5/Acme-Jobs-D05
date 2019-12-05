
package acme.features.administrator.banner.commercial;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.banners.Commercial;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/banner/commercial")
public class AdministratorBannerCommercialController extends AbstractController<Administrator, Commercial> {

	@Autowired
	private AdministratorBannerCommercialShowService	showService;

	@Autowired
	private AdministratorBannerCommercialCreateService	createService;

	@Autowired
	private AdministratorBannerCommercialUpdateService	updateService;

	@Autowired
	private AdministratorBannerCommercialDeleteService	deleteService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}

}
