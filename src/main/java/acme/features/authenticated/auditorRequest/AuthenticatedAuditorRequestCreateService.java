
package acme.features.authenticated.auditorRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditorRequests.AuditorRequest;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedAuditorRequestCreateService implements AbstractCreateService<Authenticated, AuditorRequest> {

	@Autowired
	AuthenticatedAuditorRequestRepository repository;


	@Override
	public boolean authorise(final Request<AuditorRequest> request) {
		assert request != null;
		boolean res = true;
		if (this.repository.existsAuditorById(this.repository.findUserAccountIdAuthenticatedById(request.getPrincipal().getActiveRoleId())) == 1) {
			res = false;
		}
		return res;
	}

	@Override
	public void bind(final Request<AuditorRequest> request, final AuditorRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "authenticated");

	}

	@Override
	public void unbind(final Request<AuditorRequest> request, final AuditorRequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "firm", "responsibilityStatement");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("confirm", "false");
		} else {
			request.transfer(model, "confirm");
		}

	}

	@Override
	public AuditorRequest instantiate(final Request<AuditorRequest> request) {
		AuditorRequest result;
		result = new AuditorRequest();
		Authenticated auth = this.repository.findOneAuthenticatedById(request.getPrincipal().getActiveRoleId());
		result.setAuthenticated(auth);
		return result;
	}

	@Override
	public void validate(final Request<AuditorRequest> request, final AuditorRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		boolean isConfirmed = request.getModel().getBoolean("confirm");
		errors.state(request, isConfirmed, "confirm", "authenticated.auditorRequest.error.must-confirm");
		boolean isNew = this.repository.findAuditorRequestsOfUserById(request.getPrincipal().getActiveRoleId()) == 0;
		errors.state(request, isNew, "confirm", "authenticated.auditorRequest.error.must-be-unique");
	}

	@Override
	public void create(final Request<AuditorRequest> request, final AuditorRequest entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);
	}
}
