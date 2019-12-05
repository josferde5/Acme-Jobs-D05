
package acme.features.worker.application;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.roles.Worker;
import acme.framework.components.Model;
import acme.framework.services.AbstractListService;

@Service
public class WorkerApplicationListService implements AbstractListService<Worker, Application> {

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

		request.unbind(entity, model, "referenceNumber", "creationMoment", "statement", "status");

	}

	@Override
	public Collection<Application> findMany(final acme.framework.components.Request<Application> request) {
		assert request != null;
		Collection<Application> result = this.repository.findManyAll(request.getPrincipal().getActiveRoleId());
		return result;
	}

}
