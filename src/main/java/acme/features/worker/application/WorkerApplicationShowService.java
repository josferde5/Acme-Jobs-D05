
package acme.features.worker.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.roles.Worker;
import acme.framework.components.Model;
import acme.framework.services.AbstractShowService;

@Service
public class WorkerApplicationShowService implements AbstractShowService<Worker, Application> {

	@Autowired
	private WorkerApplicationRepository repository;


	@Override
	public boolean authorise(final acme.framework.components.Request<Application> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final acme.framework.components.Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "referenceNumber", "creationMoment", "statement", "status", "skills", "qualifications");

	}

	@Override
	public Application findOne(final acme.framework.components.Request<Application> request) {
		assert request != null;
		int id;
		id = request.getModel().getInteger("id");
		Application result = this.repository.findOneById(id);
		return result;

	}

}
