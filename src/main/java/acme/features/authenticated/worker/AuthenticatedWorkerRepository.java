
package acme.features.authenticated.worker;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Worker;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AutheticatedWorkerRepository extends AbstractRepository {

	@Query("select a from UserAccount a where a.id = ?1")
	UserAccount findOneUserAccountById(int id);

	@Query("select a from Worker a where a.userAccount.id = ?1 ")
	Worker findOneWorkerById(int id);

}
