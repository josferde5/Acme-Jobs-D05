
package acme.features.sponsor.banner;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Sponsor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorRepository extends AbstractRepository {

	@Query("select a from Sponsor a where a.id=?1")
	Sponsor getOneById(int id);

}
