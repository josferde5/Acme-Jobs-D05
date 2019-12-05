
package acme.features.worker.application;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface WorkerApplicationRepository extends AbstractRepository {

	@Query("select r from Application r where r.id = ?1")
	Application findOneById(int id);

	@Query("select r from Application r where r.worker.id = ?1")
	Collection<Application> findManyAll(int employerId);
}
