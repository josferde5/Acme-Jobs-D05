
package acme.features.anonymous.fernandez_bulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.bulletins.FernandezBulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/fernandez-bulletin/")
public class AnonymousFernandezBulletinController extends AbstractController<Anonymous, FernandezBulletin> {

	@Autowired
	private AnonymousFernandezBulletinListService	listService;

	@Autowired
	private AnonymousFernandezBulletinCreateService	createService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
