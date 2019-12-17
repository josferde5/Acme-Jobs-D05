
package acme.features.sponsor.banner.nonCommercial;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.NonCommercial;
import acme.entities.customisationParameters.CustomisationParameters;
import acme.entities.roles.Sponsor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorBannerNonCommercialRepository extends AbstractRepository {

	@Query("select a from NonCommercial a where id = ?1")
	NonCommercial findOneNonCommercialBanner(int id);

	@Query("select a from Sponsor a where a.id=?1")
	Sponsor getSponsorById(int id);

	@Query("select a from CustomisationParameters a")
	CustomisationParameters findCustomParameters();
}
