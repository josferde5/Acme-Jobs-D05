
package acme.features.sponsor.banner.nonCommercial;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.banners.NonCommercial;
import acme.entities.roles.Sponsor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/sponsor/banner/non-commercial")
public class SponsorBannerNonCommercialController extends AbstractController<Sponsor, NonCommercial> {

	@Autowired
	private SponsorBannerNonCommercialShowService	showService;

	@Autowired
	private SponsorBannerNonCommercialCreateService	createService;

	@Autowired
	private SponsorBannerNonCommercialUpdateService	updateService;

	@Autowired
	private SponsorBannerNonCommercialDeleteService	deleteService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}

}
