
package acme.features.anonymous.bulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.PantojaBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousPantojaBulletinCreateService implements AbstractCreateService<Anonymous, PantojaBulletin> {

	@Autowired
	AnonymousPantojaBulletinRepository repository;


	@Override
	public boolean authorise(final Request<PantojaBulletin> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<PantojaBulletin> request, final PantojaBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<PantojaBulletin> request, final PantojaBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text", "contact");

	}

	@Override
	public PantojaBulletin instantiate(final Request<PantojaBulletin> request) {
		assert request != null;

		PantojaBulletin result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new PantojaBulletin();
		result.setAuthor("John Doe");
		result.setText("Lorem ipsum");
		result.setContact("johnDoe@gmail.com");
		result.setMoment(moment);

		return result;
	}

	@Override
	public void validate(final Request<PantojaBulletin> request, final PantojaBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<PantojaBulletin> request, final PantojaBulletin entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);

	}

}
