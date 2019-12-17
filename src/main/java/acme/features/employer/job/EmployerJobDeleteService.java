
package acme.features.employer.job;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;

@Service
public class EmployerJobDeleteService implements AbstractDeleteService<Employer, Job> {

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

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "title", "deadline", "salary", "status", "moreInfo", "descriptor.description", "finalMode");

	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;
		int id = request.getModel().getInteger("id");
		Job result = this.repository.findOneJobById(id);
		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		//A job can be deleted as long as no worker has applied for it.
		assert request != null;
		assert entity != null;
		assert errors != null;

		int id = request.getModel().getInteger("id");
		Collection<Application> result = this.repository.findApplicationsForJobById(id);
		boolean hasApplications = result.isEmpty();
		errors.state(request, hasApplications, "title", "employer.job.error.applications-delete");

	}

	@Override
	public void delete(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);

	}

}
