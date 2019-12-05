
package acme.features.sponsor.banner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.Banner;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorBannerRepository extends AbstractRepository {

	//	@Query("select a from Banner a")
	//	Collection<Banner> findManyAll();
	@Query("select a from Banner a where a.sponsor.id = ?1")
	Collection<Banner> findManyAll(int sponsorId);

	@Query("select a from Banner a where a.id=?1")
	Banner getOneById(int id);

	default Banner abc(final int id) {
		return this.getOneById(id);
	}

}
