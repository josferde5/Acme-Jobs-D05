
package acme.features.sponsor.banner.nonCommercial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.NonCommercial;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class SponsorBannerNonCommercialUpdateService implements AbstractUpdateService<Sponsor, NonCommercial> {

	@Autowired
	SponsorBannerNonCommercialRepository repository;


	@Override
	public boolean authorise(final Request<NonCommercial> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<NonCommercial> request, final NonCommercial entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<NonCommercial> request, final NonCommercial entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "slogan", "target", "jingle");
	}

	@Override
	public NonCommercial findOne(final Request<NonCommercial> request) {
		assert request != null;

		NonCommercial result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneNonCommercialBanner(id);
		return result;
	}

	@Override
	public void validate(final Request<NonCommercial> request, final NonCommercial entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void update(final Request<NonCommercial> request, final NonCommercial entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
