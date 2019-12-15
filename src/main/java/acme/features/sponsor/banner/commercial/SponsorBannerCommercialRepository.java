
package acme.features.sponsor.banner.commercial;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.Commercial;
import acme.entities.customisationParameters.CustomisationParameters;
import acme.entities.roles.Sponsor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorBannerCommercialRepository extends AbstractRepository {

	@Query("select a from Commercial a where id = ?1")
	Commercial findOneCommercialBanner(int id);

	@Query("select a from Sponsor a where a.id=?1")
	Sponsor getSponsorById(int id);

	@Query("select a from CustomisationParameters a")
	CustomisationParameters findCustomParameters();
}
