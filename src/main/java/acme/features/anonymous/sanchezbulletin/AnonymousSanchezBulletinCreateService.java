
package acme.features.anonymous.sanchezbulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.SanchezBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousSanchezBulletinCreateService implements AbstractCreateService<Anonymous, SanchezBulletin> {

	@Autowired
	AnonymousSanchezBulletinRepository repository;


	@Override
	public boolean authorise(final Request<SanchezBulletin> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<SanchezBulletin> request, final SanchezBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<SanchezBulletin> request, final SanchezBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text");

	}

	@Override
	public SanchezBulletin instantiate(final Request<SanchezBulletin> request) {
		assert request != null;

		SanchezBulletin result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new SanchezBulletin();
		result.setAuthor("John Doe");
		result.setText("Lorem ipsum");
		result.setMoment(moment);

		return result;
	}

	@Override
	public void validate(final Request<SanchezBulletin> request, final SanchezBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<SanchezBulletin> request, final SanchezBulletin entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);

	}

}
