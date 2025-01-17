
package acme.features.authenticated.employer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.entities.UserAccount;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedEmployerCreateService implements AbstractCreateService<Authenticated, Employer> {

	@Autowired
	AuthenticatedEmployerRepository repository;


	@Override
	public boolean authorise(final Request<Employer> request) {
		assert request != null;
		boolean res = true;
		//Not be able to create other employer with the same account
		if (this.repository.existsEmployerById(this.repository.findUserAccountIdAuthenticatedById(request.getPrincipal().getActiveRoleId())) == 1) {
			res = false;
		}
		return res;
	}

	@Override
	public void bind(final Request<Employer> request, final Employer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Employer> request, final Employer entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "company", "sector");

	}

	@Override
	public Employer instantiate(final Request<Employer> request) {
		assert request != null;
		Employer result;
		Principal principal;
		int userAccountId;
		UserAccount user;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		user = this.repository.findOneUserAccountById(userAccountId);
		result = new Employer();
		result.setUserAccount(user);

		return result;
	}

	@Override
	public void validate(final Request<Employer> request, final Employer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<Employer> request, final Employer entity) {
		assert request != null;

		this.repository.save(entity);

	}

	@Override
	public void onSuccess(final Request<Employer> request, final Response<Employer> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}

	}

}
