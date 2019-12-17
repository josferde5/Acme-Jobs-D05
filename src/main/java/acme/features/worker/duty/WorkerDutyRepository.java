
package acme.features.worker.duty;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.descriptors.Descriptor;
import acme.entities.duties.Duty;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface WorkerDutyRepository extends AbstractRepository {

	@Query("select r from Descriptor d join d.duties r where d.id=?1 ")
	Collection<Duty> findManyDutiesById(int id);

	@Query("select du from Duty du where du.id=?1")
	Duty findOneById(int id);

	@Query("select des from Descriptor des where des.id=?1")
	Descriptor findDescriptorById(int id);

	//Retrieve Descriptor id
	@Query("select d.id from Descriptor d left join d.duties du where du.id=?1")
	int findDescriptorIDbyDutyId(int id);

}
