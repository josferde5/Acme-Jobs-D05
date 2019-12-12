
package acme.features.worker.job;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface WorkerJobRepository extends AbstractRepository {

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int id);

	///A job is said to be active if it was published and its deadline line has not elapsed
	@Query("select j from Job j  where j.deadline > curdate() and j.finalMode = true")
	Collection<Job> findManyActiveJobs();

}
