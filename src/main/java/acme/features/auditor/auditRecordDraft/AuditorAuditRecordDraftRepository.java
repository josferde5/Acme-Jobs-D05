
package acme.features.auditor.auditRecordDraft;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorAuditRecordDraftRepository extends AbstractRepository {

	@Query("select r from AuditRecord r where r.id = ?1")
	AuditRecord findOneAuditRecordById(int id);

	@Query("select r from AuditRecord r where r.job.id = ?1 and r.finalMode = true")
	Collection<AuditRecord> findManyByJobId(int idJob);

	@Query("select a from Auditor a where a.id = ?1")
	Auditor findOneAuditorById(int id);

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int id);

	@Query("select r from AuditRecord r where r.id = ?1 and r.finalMode = false")
	Collection<AuditRecord> findManyAuditRecordNotFinalById(int id);

}
