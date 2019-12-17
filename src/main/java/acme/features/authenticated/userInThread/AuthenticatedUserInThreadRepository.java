
package acme.features.authenticated.userInThread;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messageThreads.MessageThread;
import acme.entities.usersInThread.UserInThread;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedUserInThreadRepository extends AbstractRepository {

	@Query("select uit from UserInThread uit where uit.messageThread.id = ?1")
	Collection<UserInThread> findManyAllByThreadId(int id);

	@Query("select uit from UserInThread uit where uit.id = ?1")
	UserInThread findOneById(int id);

	@Query("select a from Authenticated a where a not in(select uit.authenticated from UserInThread uit where uit.messageThread.id = ?1)")
	Collection<Authenticated> findAllUsernamesNotInThread(int id);

	@Query("select a.id from Authenticated a where a not in(select uit.authenticated.id from UserInThread uit where uit.messageThread.id = ?1)")
	Collection<String> findAllAuthenticatedIdsNotInThread(int id);

	@Query("select a from MessageThread a where a.id = ?1")
	MessageThread findOneThreadById(int id);

	@Query("select a from Authenticated a where a.id = ?1")
	Authenticated findOneAuthenticated(int id);

	@Query("select a.id from Authenticated a")
	Collection<Integer> findManyAllAuthenticatedId();

	@Query("select uit.authenticated.id from UserInThread uit where uit.messageThread.id = ?1")
	Collection<Integer> findUserIdInThread(int id);

	@Query("select uit.messageThread from UserInThread uit where uit.id = ?1")
	MessageThread findOneMessageThreadByUserInThreadId(int id);

	@Query("select uit.authenticated from UserInThread uit where uit.messageThread.id = ?1")
	Collection<Authenticated> findAllUsersInThread(int id);

}
