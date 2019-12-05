
package acme.features.sponsor.banner.commercial;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.banners.Commercial;
import acme.entities.roles.Sponsor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/sponsor/banner/commercial")
public class SponsorBannerCommercialController extends AbstractController<Sponsor, Commercial> {

	@Autowired
	private SponsorBannerCommercialShowService		showService;

	@Autowired
	private SponsorBannerCommercialCreateService	createService;

	@Autowired
	private SponsorBannerCommercialUpdateService	updateService;

	@Autowired
	private SponsorBannerCommercialDeleteService	deleteService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}

}
