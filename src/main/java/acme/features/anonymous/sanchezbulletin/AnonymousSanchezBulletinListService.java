
package acme.features.anonymous.sanchezbulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.SanchezBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousSanchezBulletinListService implements AbstractListService<Anonymous, SanchezBulletin> {

	@Autowired
	AnonymousSanchezBulletinRepository repository;


	@Override
	public boolean authorise(final Request<SanchezBulletin> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<SanchezBulletin> request, final SanchezBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text", "moment");

	}

	@Override
	public Collection<SanchezBulletin> findMany(final Request<SanchezBulletin> request) {
		assert request != null;

		Collection<SanchezBulletin> result;

		result = this.repository.findMany();

		return result;
	}

}
