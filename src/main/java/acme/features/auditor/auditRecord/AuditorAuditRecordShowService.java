
package acme.features.auditor.auditRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuditorAuditRecordShowService implements AbstractShowService<Auditor, AuditRecord> {

	@Autowired
	private AuditorAuditRecordRepository repository;


	@Override
	public boolean authorise(final Request<AuditRecord> request) {
		assert request != null;
		boolean result;
		int auditRecordId = request.getModel().getInteger("id");
		AuditRecord ar = this.repository.findOneAuditRecordById(auditRecordId);
		Auditor auditor = ar.getAuditor();
		Principal principal = request.getPrincipal();
		result = ar.isFinalMode() || !ar.isFinalMode() && auditor.getUserAccount().getId() == principal.getAccountId();
		return result;
	}

	@Override
	public void unbind(final Request<AuditRecord> request, final AuditRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		model.setAttribute("auditorId", entity.getAuditor().getId());
		model.setAttribute("principalId", request.getPrincipal().getActiveRoleId());
		if (entity.getStatus().equals("draft")) {
			model.setAttribute("finalMode", false);
		} else {
			model.setAttribute("finalMode", true);
		}
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
