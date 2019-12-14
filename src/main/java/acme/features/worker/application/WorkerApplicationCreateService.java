
package acme.features.worker.application;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.applications.Status;
import acme.entities.roles.Worker;
import acme.features.worker.job.WorkerJobRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class WorkerApplicationCreateService implements AbstractCreateService<Worker, Application> {

	@Autowired
	private WorkerApplicationRepository	repository;
	@Autowired
	private WorkerJobRepository			jobRepository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "referenceNumber", "creationMoment", "status", "skills", "qualifications", "job", "worker");
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		model.setAttribute("idJob", request.getModel().getString("idJob"));
		request.unbind(entity, model, "skills", "qualifications", "statement");
	}

	@Override
	public Application instantiate(final Request<Application> request) {
		Application result;

		result = new Application();
		Worker worker = this.repository.findWorkerById(request.getPrincipal().getActiveRoleId());
		result.setJob(this.jobRepository.findOneJobById(request.getModel().getInteger("idJob")));
		String referenceNumber = result.getJob().getReference() + ":" + (char) (worker.getId() % 24 + 66) + (char) (worker.getId() / 24 % 24 + 66) + (char) (worker.getId() % 24 + 66) + (char) (worker.getId() / 24 % 24 + 66);
		result.setReferenceNumber(referenceNumber);
		result.setCreationMoment(new Date(System.currentTimeMillis() - 1));
		result.setQualifications(worker.getQualificationsRecord());
		result.setSkills(worker.getSkillsRecord());
		result.setStatus(Status.PENDING);
		result.setWorker(worker);
		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<Application> request, final Application entity) {
		assert request != null;

		this.repository.save(entity);
	}

}
