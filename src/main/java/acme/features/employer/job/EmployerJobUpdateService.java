
package acme.features.employer.job;

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
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerJobUpdateService implements AbstractUpdateService<Employer, Job> {

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

	}

	@Override
	public Job findOne(final Request<Job> request) {
		int id = request.getModel().getInteger("id");
		Job result = this.repository.findOneJobById(id);
		Descriptor descriptor = result.getDescriptor();
		descriptor.setDescription("descriptor.description");
		return result;

	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//Job cannot be updated if is published, just for draft
		if (entity.isFinalMode()) {
			boolean isDescriptor = entity.getDescriptor().getDescription() != null;
			//show error
			errors.state(request, isDescriptor, "descriptor.description", "employer.job.error.must-have-descriptor");

			//The duties sum up 100%
			Collection<Duty> duties = this.repository.getDutiesByJobId(entity.getId());
			int aux = duties.stream().mapToInt(x -> new Integer(x.getPercentTime().replaceAll("%", ""))).sum();
			Boolean isPercentUnder = aux == 100;
			errors.state(request, isPercentUnder, "finalMode", "employer.job.error.hundred-under");
		}

	}

	@Override
	public void update(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;
		Descriptor descriptor = entity.getDescriptor();
		this.repository.save(descriptor);

		this.repository.save(entity);

	}

}
