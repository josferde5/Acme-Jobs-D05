
package acme.features.anonymous.bulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bulletins.PantojaBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousPantojaBulletinRepository extends AbstractRepository {

	@Query("select s from PantojaBulletin s")
	Collection<PantojaBulletin> findMany();

}
