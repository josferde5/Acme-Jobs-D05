
package acme.features.anonymous.raposoBulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.RaposoBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousRaposoBulletinListService implements AbstractListService<Anonymous, RaposoBulletin> {

	@Autowired
	AnonymousRaposoBulletinRepository repository;


	@Override
	public boolean authorise(final Request<RaposoBulletin> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<RaposoBulletin> request, final RaposoBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "place", "text", "moment");

	}

	@Override
	public Collection<RaposoBulletin> findMany(final Request<RaposoBulletin> request) {
		assert request != null;

		Collection<RaposoBulletin> result;

		result = this.repository.findMany();

		return result;
	}

}
