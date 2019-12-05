
package acme.features.employer.auditRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class EmployerAuditRecordShowService implements AbstractShowService<Employer, AuditRecord> {

	@Autowired
	private EmployerAuditRecordRepository repository;


	@Override
	public boolean authorise(final Request<AuditRecord> request) {
		assert request != null;
		int auditRecordId = request.getModel().getInteger("id");
		AuditRecord ar = this.repository.findOneAuditRecordById(auditRecordId);
		return ar.isFinalMode();
	}

	@Override
	public void unbind(final Request<AuditRecord> request, final AuditRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationMoment", "body", "status");
	}

	@Override
	public AuditRecord findOne(final Request<AuditRecord> request) {
		assert request != null;
		int id = request.getModel().getInteger("id");
		AuditRecord result = this.repository.findOneAuditRecordById(id);
		return result;
	}

}
