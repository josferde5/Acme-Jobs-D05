
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.customisationParameters.CustomisationParameters;
import acme.entities.messageThreads.MessageThread;
import acme.entities.messages.Message;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageRepository extends AbstractRepository {

	@Query("select a from Message a where a.id = ?1")
	Message findOneById(int id);

	@Query("select a from MessageThread r join r.messages a where r.id = ?1")
	Collection<Message> findManyAllByThread(int threadId);

	@Query("select uit.authenticated from UserInThread uit where uit.messageThread.id = ?1")
	Collection<Authenticated> findManyUsersByThread(int id);

	@Query("select a from CustomisationParameters a")
	CustomisationParameters findCustomParameters();

	@Query("select a from MessageThread a where a.id = ?1")
	MessageThread findOneMessageThreadById(int id);

}
