
package acme.features.provider.request;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.requests.Request;
import acme.entities.roles.Provider;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/provider/request/")
public class ProviderRequestController extends AbstractController<Provider, Request> {

	@Autowired
	ProviderRequestCreateService	createService;
	@Autowired
	ProviderRequestListService		listService;
	@Autowired
	ProviderRequestShowService		showService;


	@PostConstruct
	private void initialase() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);

	}

}
