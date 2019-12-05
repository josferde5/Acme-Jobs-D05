
package acme.features.anonymous.sanchezbulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.bulletins.SanchezBulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/sanchez-bulletin/")
public class AnonymousSanchezBulletinController extends AbstractController<Anonymous, SanchezBulletin> {

	@Autowired
	private AnonymousSanchezBulletinListService		listService;

	@Autowired
	private AnonymousSanchezBulletinCreateService	createService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
