
package acme.features.sponsor.banner;

import org.springframework.stereotype.Component;

import acme.entities.banners.Commercial;
import lombok.Getter;

@Component
@Getter
public class GrepCommercial {

	private static SponsorBannerRepository repository;


	private GrepCommercial(final SponsorBannerRepository repository) {
		GrepCommercial.repository = repository;
	}

	public static GrepCommercial instantiate(final SponsorBannerRepository repository) {
		return new GrepCommercial(repository);
	}

	public static Boolean isCommercial(final int id) {
		Object a;

		a = GrepCommercial.repository.getOneById(id);

		return a instanceof Commercial ? true : false;
	}

}
