
package acme.features.anonymous.sanchezbulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bulletins.SanchezBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousSanchezBulletinRepository extends AbstractRepository {

	@Query("select s from SanchezBulletin s")
	Collection<SanchezBulletin> findMany();

}
