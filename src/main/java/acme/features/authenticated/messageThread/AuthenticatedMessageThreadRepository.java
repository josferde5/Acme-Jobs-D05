
package acme.features.authenticated.messageThread;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messageThreads.MessageThread;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageThreadRepository extends AbstractRepository {

	@Query("select a from MessageThread a where a.id = ?1")
	MessageThread findOneById(int id);

	@Query("select a from MessageThread a join a.users r where r.id = ?1")
	Collection<MessageThread> findManyByUserId(int userId);

}
