
package acme.features.auditor.auditRecordDraft;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.roles.Auditor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/auditor/audit-record-draft/")
public class AuditorAuditRecordDraftController extends AbstractController<Auditor, AuditRecord> {

	@Autowired
	private AuditorAuditRecordDraftListService		listService;

	@Autowired
	private AuditorAuditRecordDraftShowService		showService;

	@Autowired
	private AuditorAuditRecordDraftUpdateService	updateService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}

}
