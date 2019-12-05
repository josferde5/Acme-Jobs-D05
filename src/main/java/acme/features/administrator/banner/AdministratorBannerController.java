
package acme.features.administrator.banner;

import java.io.IOException;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import acme.entities.banners.Banner;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/banner")
public class AdministratorBannerController extends AbstractController<Administrator, Banner> {

	@Autowired
	private AdministratorBannerListService	listService;

	@Autowired
	private AdministratorBannerRepository	repository;


	@RequestMapping("/show")
	private void redirect(@RequestParam final Map<String, Object> model, final HttpServletRequest servletRequest, final HttpServletResponse servletResponse) throws IOException {
		StringBuilder uri = new StringBuilder();
		int id = Integer.valueOf((String) model.get("id"));

		Boolean b = FindCommercial.isCommercial(id);

		if (b) {
			uri.append(servletRequest.getContextPath());
			uri.append("/administrator/banner/commercial/show?id=");
			uri.append(model.get("id"));

			servletResponse.sendRedirect(uri.toString());
		} else {
			uri.append(servletRequest.getContextPath());
			uri.append("/administrator/banner/non-commercial/show?id=");
			uri.append(model.get("id"));

			servletResponse.sendRedirect(uri.toString());
		}
	}

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		@SuppressWarnings("unused")
		FindCommercial fc = FindCommercial.instantiate(this.repository);
	}

}
