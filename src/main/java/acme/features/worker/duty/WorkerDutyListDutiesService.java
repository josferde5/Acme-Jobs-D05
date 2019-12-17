
package acme.features.worker.duty;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.roles.Worker;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class WorkerDutyListDutiesService implements AbstractListService<Worker, Duty> {

	@Autowired
	WorkerDutyRepository repository;


	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "percentTime");

	}

	@Override
	public Collection<Duty> findMany(final Request<Duty> request) {
		assert request != null;
		int id = request.getModel().getInteger("id");
		Collection<Duty> result = this.repository.findManyDutiesById(id);
		return result;
	}

}
