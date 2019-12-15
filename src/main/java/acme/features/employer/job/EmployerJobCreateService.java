
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

		Boolean finalMode = request.getModel().getBoolean("finalMode");
		String description = request.getModel().getString("descriptor.description");

		//check validations only if employer puts job as published
		if (finalMode) {
			boolean isDescriptor = description != null;
			//show error
			errors.state(request, isDescriptor, "descriptor.description", "employer.job.error.must-have-descriptor");

			//The duties sum up 100%
			Collection<Duty> duties = entity.getDescriptor().getDuties();
			int aux = duties.stream().mapToInt(x -> new Integer(x.getPercentTime().replaceAll("%", ""))).sum();
			Boolean isPercentUnder = aux == 100;
			errors.state(request, isPercentUnder, "finalMode", "employer.job.error.hundred-under");

			//Not considered spam
			//			CustomisationParameters cp;
			//			cp = this.repository.findCustomParameters();
			//			String[] spamEn = cp.getSpamWordsEn().split(",");
			//			String[] spamSp = cp.getSpamWordsSp().split(",");
			//			Double thold = cp.getThreshold();
			//			Arrays.asList(spamEn).stream().

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
