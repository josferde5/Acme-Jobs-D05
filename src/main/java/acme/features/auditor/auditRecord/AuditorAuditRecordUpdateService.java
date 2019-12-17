
package acme.features.auditor.auditRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuditorAuditRecordUpdateService implements AbstractUpdateService<Auditor, AuditRecord> {

	@Autowired
	AuditorAuditRecordRepository repository;


	@Override
	public boolean authorise(final Request<AuditRecord> request) {
		assert request != null;
		boolean result;
		int auditRecordId = request.getModel().getInteger("id");
		AuditRecord ar = this.repository.findOneAuditRecordById(auditRecordId);
		Auditor auditor = ar.getAuditor();
		Principal principal = request.getPrincipal();
		result = !ar.isFinalMode() && auditor.getUserAccount().getId() == principal.getAccountId();
		return result;
	}

	@Override
	public void bind(final Request<AuditRecord> request, final AuditRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "creationMoment", "auditor", "job");
	}

	@Override
	public void unbind(final Request<AuditRecord> request, final AuditRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "title", "body", "finalMode");
	}

	@Override
	public AuditRecord findOne(final Request<AuditRecord> request) {
		assert request != null;
		AuditRecord result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneAuditRecordById(id);
		return result;
	}

	@Override
	public void validate(final Request<AuditRecord> request, final AuditRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void update(final Request<AuditRecord> request, final AuditRecord entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);
	}

}
