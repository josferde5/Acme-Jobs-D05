
package acme.features.authenticated.authenticated;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedAuthenticatedRepository extends AbstractRepository {

	@Query("select a from Authenticated a where a not in(select u from MessageThread mt join mt.users u where mt.id=?1)")
	Collection<Authenticated> findManyAllNotInThread(int id);

	@Query("select a from Authenticated a where a.id = ?1")
	Authenticated findOneById(int id);

	@Query("select mt.users from MessageThread mt where mt.id = ?1")
	Collection<Authenticated> findManyUsersInThread(int id);

}
