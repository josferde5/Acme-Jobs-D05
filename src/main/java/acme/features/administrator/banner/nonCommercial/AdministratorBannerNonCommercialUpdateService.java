
package acme.features.administrator.banner.nonCommercial;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.NonCommercial;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorBannerNonCommercialUpdateService implements AbstractUpdateService<Administrator, NonCommercial> {

	@Autowired
	AdministratorBannerNonCommercialRepository repository;


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

		String stringTarget = "";
		int stringOccurrences = 0;
		for (String s : this.repository.findCustomParameters().getSpamWordsEn().split("[,]")) {
			stringTarget = s.trim().toLowerCase();
			stringOccurrences += StringUtils.countMatches(entity.getSlogan().toLowerCase(), stringTarget);
		}
		for (String s : this.repository.findCustomParameters().getSpamWordsSp().split("[,]")) {
			stringTarget = s.trim().toLowerCase();
			stringOccurrences += StringUtils.countMatches(entity.getSlogan().toLowerCase(), stringTarget);
		}
		boolean condition = (double) stringOccurrences / entity.getSlogan().split("[ \n]").length * 100 < this.repository.findCustomParameters().getThreshold();
		errors.state(request, condition, "slogan", "sponsor.banner.commercial.form.spam");

	}

	@Override
	public void update(final Request<NonCommercial> request, final NonCommercial entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
