
package acme.features.sponsor.banner.nonCommercial;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.NonCommercial;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorBannerNonCommercialRepository extends AbstractRepository {

	@Query("select a from NonCommercial a where id = ?1")
	NonCommercial findOneNonCommercialBanner(int id);

}
