
package acme.features.auditor.job;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorJobRepository extends AbstractRepository {

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int id);

	@Query("select j from Job j where exists(select r from AuditRecord r where r.job.id = j.id and r.auditor.id = ?1) and j.finalMode = true")
	Collection<Job> findManyMineByAuditorId(int idAuditor);

	@Query("select j from Job j where not exists(select r from AuditRecord r where r.job.id = j.id and r.auditor.id = ?1) and j.finalMode = true")
	Collection<Job> findManyNotMineByAuditorId(int idAuditor);
}
