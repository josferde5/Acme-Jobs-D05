
package acme.features.employer.duty;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.duties.Duty;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerDutyRepository extends AbstractRepository {

	@Query("select r from Descriptor d join d.duties r where d.id=?1 ")
	Collection<Duty> findManyDutiesById(int id);

	@Query("select du from Duty du where du.id=?1")
	Duty findOneById(int id);

}
