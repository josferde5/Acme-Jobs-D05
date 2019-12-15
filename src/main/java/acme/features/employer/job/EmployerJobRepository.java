
package acme.features.employer.job;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerJobRepository extends AbstractRepository {

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int id);

	@Query("select j from Job j where j.employer.id = ?1")
	Collection<Job> findManyByEmployerId(int idEmployer);

	///Retrieve applications for a job restriction for deleting a job
	@Query("select ap from Application ap where ap.job.id = ?1")
	Collection<Application> findApplicationsForJobById(int id);

	//Retrieve employer
	@Query("select emp from Employer emp where emp.id=?1")
	Employer findEmployerById(int id);

	//Retrieve duties
	@Query("select j.descriptor.duties from Job j where j.id = ?1")
	Collection<Duty> getDutiesByJobId(int id);

	//Retrieve spam
	//	@Query("select cp from CustomisationParameters cp")
	//	CustomisationParameters findCustomParameters();

}
