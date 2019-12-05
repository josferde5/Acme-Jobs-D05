
package acme.features.anonymous.bulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.PantojaBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousPantojaBulletinListService implements AbstractListService<Anonymous, PantojaBulletin> {

	@Autowired
	AnonymousPantojaBulletinRepository repository;


	@Override
	public boolean authorise(final Request<PantojaBulletin> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<PantojaBulletin> request, final PantojaBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text", "moment", "contact");

	}

	@Override
	public Collection<PantojaBulletin> findMany(final Request<PantojaBulletin> request) {
		assert request != null;

		Collection<PantojaBulletin> result;

		result = this.repository.findMany();

		return result;
	}

}
