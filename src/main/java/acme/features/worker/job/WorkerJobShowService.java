
package acme.features.worker.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class WorkerJobShowService implements AbstractShowService<Worker, Job> {

	@Autowired
	private WorkerJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "title", "deadline", "salary", "moreInfo", "status", "descriptor.description", "id");
		//Collection<Duty> dutiess = entity.getDescriptor().getDuties();
		int id = entity.getDescriptor().getId();
		//		StringBuilder buffer = new StringBuilder();
		//		for (Duty duty : dutiess) {
		//			buffer.append("Duty number:");
		//			buffer.append(duty.getId());
		//			buffer.append("\n");
		//			buffer.append("Description:");
		//			buffer.append(duty.getDescription());
		//			buffer.append("\n");
		//			buffer.append("Title:");
		//			buffer.append(duty.getTitle());
		//			buffer.append("\n");
		//			buffer.append("Time in percent:");
		//			buffer.append(duty.getPercentTime());
		//			buffer.append("\n");
		//		}
		//		model.setAttribute("duties", buffer.toString());
		model.setAttribute("descriptorId", id);
		int jobId = entity.getId();
		model.setAttribute("jobId", jobId);

	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;
		int id = request.getModel().getInteger("id");
		Job result = this.repository.findOneJobById(id);
		result.getDescriptor().getDuties().size();
		return result;
	}

}
