
package acme.features.administrator.customisationParameters;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.customisationParameters.CustomisationParameters;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorCustomisationParametersRepository extends AbstractRepository {

	@Query("select a from CustomisationParameters a")
	CustomisationParameters findCustomParameters();

}
