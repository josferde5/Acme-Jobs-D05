
package acme.features.sponsor.banner;

import java.io.IOException;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import acme.components.CustomCommand;
import acme.entities.banners.Banner;
import acme.entities.roles.Sponsor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/sponsor/banner")
public class SponsorBannerController extends AbstractController<Sponsor, Banner> {

	@Autowired
	private SponsorBannerListService	listService;

	@Autowired
	private SponsorBannerRepository		repository;


	@RequestMapping("/show")
	private void redirect(@RequestParam final Map<String, Object> model, final HttpServletRequest servletRequest, final HttpServletResponse servletResponse) throws IOException {
		StringBuilder uri = new StringBuilder();
		int id = Integer.valueOf((String) model.get("id"));

		Boolean b = GrepCommercial.isCommercial(id);

		if (b) {
			uri.append(servletRequest.getContextPath());
			uri.append("/sponsor/banner/commercial/show?id=");
			uri.append(model.get("id"));

			servletResponse.sendRedirect(uri.toString());
		} else {
			uri.append(servletRequest.getContextPath());
			uri.append("/sponsor/banner/non-commercial/show?id=");
			uri.append(model.get("id"));

			servletResponse.sendRedirect(uri.toString());
		}
	}

	@PostConstruct
	private void initialise() {
		//		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listService);
		@SuppressWarnings("unused")
		GrepCommercial fc = GrepCommercial.instantiate(this.repository);
	}

}
