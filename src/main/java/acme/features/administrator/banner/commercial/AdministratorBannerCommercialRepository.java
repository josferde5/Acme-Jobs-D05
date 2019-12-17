
package acme.features.administrator.banner.commercial;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.Commercial;
import acme.entities.customisationParameters.CustomisationParameters;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorBannerCommercialRepository extends AbstractRepository {

	@Query("select a from Commercial a where id = ?1")
	Commercial findOneCommercialBanner(int id);
	@Query("select a from CustomisationParameters a")
	CustomisationParameters findCustomParameters();
}
