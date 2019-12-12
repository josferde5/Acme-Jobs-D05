
package acme.features.worker.application;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface WorkerApplicationRepository extends AbstractRepository {

	@Query("select r from Application r where r.id = ?1")
	Application findOneById(int id);

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int id);

	@Query("select r from Worker r where r.id = ?1")
	Worker findWorkerById(int id);

	@Query("select r from Application r where r.worker.id = ?1")
	Collection<Application> findManyAll(int employerId);
}
