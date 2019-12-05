
package acme.features.auditor.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class AuditorJobShowService implements AbstractShowService<Auditor, Job> {

	@Autowired
	private AuditorJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;
		int jobId = request.getModel().getInteger("id");
		Job job = this.repository.findOneJobById(jobId);
		return job.isFinalMode();
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "title", "deadline", "salary", "moreInfo", "status", "descriptor.description");
		int id = entity.getDescriptor().getId();
		model.setAttribute("descriptorId", id);
		int jobId = entity.getId();
		model.setAttribute("jobId", jobId);
	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;
		int id = request.getModel().getInteger("id");
		Job result = this.repository.findOneJobById(id);
		return result;
	}

}
