
package acme.features.anonymous.raposoBulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.RaposoBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousRaposoBulletinCreateService implements AbstractCreateService<Anonymous, RaposoBulletin> {

	@Autowired
	AnonymousRaposoBulletinRepository repository;


	@Override
	public boolean authorise(final Request<RaposoBulletin> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<RaposoBulletin> request, final RaposoBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<RaposoBulletin> request, final RaposoBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "place", "text");

	}

	@Override
	public RaposoBulletin instantiate(final Request<RaposoBulletin> request) {
		assert request != null;

		RaposoBulletin result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new RaposoBulletin();
		result.setAuthor("John Doe");
		result.setPlace("World");
		result.setText("Lorem ipsum");
		result.setMoment(moment);

		return result;
	}

	@Override
	public void validate(final Request<RaposoBulletin> request, final RaposoBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<RaposoBulletin> request, final RaposoBulletin entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);

	}

}
