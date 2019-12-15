
package acme.features.employer.job;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.descriptors.Descriptor;
import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerJobCreateService implements AbstractCreateService<Employer, Job> {

	@Autowired
	EmployerJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "status");

	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "title", "deadline", "salary", "moreInfo", "descriptor.description", "finalMode");

		if (request.getModel().getAttribute("descriptor.description") != null) {
			model.setAttribute("hasDescriptor", "true");
		} else {
			model.setAttribute("hasDescriptor", "false");
		}
		Collection<Duty> duties = entity.getDescriptor().getDuties();
		int aux = duties.stream().mapToInt(x -> new Integer(x.getPercentTime().replaceAll("%", ""))).sum();
		if (aux == 100) {
			model.setAttribute("isPercentUnder", "true");
		} else {
			model.setAttribute("isPercentUnder", "false");
		}
	}

	@Override
	public Job instantiate(final Request<Job> request) {
		assert request != null;
		Job result;
		result = new Job();
		//Association with Employer
		int principalID = request.getPrincipal().getActiveRoleId();
		Employer employer = this.repository.findEmployerById(principalID);
		result.setEmployer(employer);
		//Association with Descriptor
		Descriptor descriptor = new Descriptor();
		String description = request.getModel().getString("descriptor.description");
		//with the duties
		Collection<Duty> duties = new ArrayList<>();
		descriptor.setDescription(description);
		descriptor.setDuties(duties);

		result.setDescriptor(descriptor);

		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (entity.isFinalMode() == true && !entity.getDescriptor().getDescription().isEmpty()) {
			boolean isDescriptor = request.getModel().getBoolean("hasDescriptor");
			//show error
			errors.state(request, isDescriptor, "descriptor.description", "employer.job.error.must-have-descriptor");

		}
		if (entity.isFinalMode() == true) {
			//The duties sum up 100%
			boolean isPercentUnder = request.getModel().getBoolean("isPercentUnder");
			errors.state(request, isPercentUnder, "finalMode", "employer.job.error.hundred-under");
		}

	}

	@Override
	public void create(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;
		Descriptor descriptor = entity.getDescriptor();
		this.repository.save(descriptor);

		this.repository.save(entity);

	}

}
