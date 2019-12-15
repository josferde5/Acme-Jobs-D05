
package acme.features.administrator.banner.nonCommercial;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.NonCommercial;
import acme.entities.customisationParameters.CustomisationParameters;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorBannerNonCommercialRepository extends AbstractRepository {

	@Query("select a from NonCommercial a where id = ?1")
	NonCommercial findOneNonCommercialBanner(int id);

	@Query("select a from CustomisationParameters a")
	CustomisationParameters findCustomParameters();
}
