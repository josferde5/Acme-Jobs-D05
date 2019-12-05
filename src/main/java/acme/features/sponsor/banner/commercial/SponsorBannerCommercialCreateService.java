
package acme.features.sponsor.banner.commercial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.Commercial;
import acme.entities.roles.Sponsor;
import acme.features.sponsor.banner.SponsorRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class SponsorBannerCommercialCreateService implements AbstractCreateService<Sponsor, Commercial> {

	@Autowired
	SponsorBannerCommercialRepository	repository;
	@Autowired
	SponsorRepository					sponsorRepository;


	@Override
	public boolean authorise(final Request<Commercial> request) {
		assert request != null;
		Boolean authorized = this.sponsorRepository.getOneById(request.getPrincipal().getActiveRoleId()).getCreditCard() != null;
		return authorized;
	}

	@Override
	public void bind(final Request<Commercial> request, final Commercial entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Commercial> request, final Commercial entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "slogan", "target", "creditCard");

	}

	@Override
	public Commercial instantiate(final Request<Commercial> request) {
		assert request != null;

		Commercial result;
		result = new Commercial();
		result.setSponsor(this.sponsorRepository.getOneById(request.getPrincipal().getActiveRoleId()));
		result.setCreditCard(this.sponsorRepository.getOneById(request.getPrincipal().getActiveRoleId()).getCreditCard());

		return result;
	}

	@Override
	public void validate(final Request<Commercial> request, final Commercial entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<Commercial> request, final Commercial entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
