
package acme.features.employer.application;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerApplicationRepository extends AbstractRepository {

	@Query("select j from Application j where j.id = ?1")
	Application findOneById(int id);

	@Query("select j from Application j where j.job.id=?1")
	Collection<Application> findManyByJobId(int id);
}
