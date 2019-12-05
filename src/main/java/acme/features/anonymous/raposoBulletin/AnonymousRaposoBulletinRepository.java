
package acme.features.anonymous.raposoBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bulletins.RaposoBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousRaposoBulletinRepository extends AbstractRepository {

	@Query("select s from RaposoBulletin s")
	Collection<RaposoBulletin> findMany();

}
