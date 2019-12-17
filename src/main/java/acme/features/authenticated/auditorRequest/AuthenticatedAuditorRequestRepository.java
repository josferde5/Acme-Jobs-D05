
package acme.features.authenticated.auditorRequest;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedAuditorRequestRepository extends AbstractRepository {

	@Query("select a from Authenticated a where a.id = ?1")
	Authenticated findOneAuthenticatedById(int id);

	@Query("select count(ar) from AuditorRequest ar where ar.authenticated.id = ?1")
	Integer findAuditorRequestsOfUserById(int id);

	@Query("select count(a) from Auditor a where a.userAccount.id = ?1")
	Integer existsAuditorById(int id);

	@Query("select userAccount.id from Authenticated a where a.id = ?1")
	Integer findUserAccountIdAuthenticatedById(int id);

}
