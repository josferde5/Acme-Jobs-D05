
package acme.features.authenticated.employer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Employer;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedEmployerRepository extends AbstractRepository {

	@Query("select e from Employer e where e.userAccount.id=?1")
	Employer findOneEmployerByUserAccountId(int id);

	@Query("select ua from UserAccount ua where ua.id=?1")
	UserAccount findOneUserAccountById(int id);

	@Query("select count(a) from Employer a where a.userAccount.id = ?1")
	Integer existsEmployerById(int id);

	@Query("select userAccount.id from Authenticated a where a.id = ?1")
	Integer findUserAccountIdAuthenticatedById(int id);

}
