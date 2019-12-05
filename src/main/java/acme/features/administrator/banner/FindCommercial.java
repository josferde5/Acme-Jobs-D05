
package acme.features.administrator.banner;

import org.springframework.stereotype.Component;

import acme.entities.banners.Commercial;
import lombok.Getter;

@Component
@Getter
public class FindCommercial {

	private static AdministratorBannerRepository repository;


	private FindCommercial(final AdministratorBannerRepository repository) {
		FindCommercial.repository = repository;
	}

	public static FindCommercial instantiate(final AdministratorBannerRepository repository) {
		return new FindCommercial(repository);
	}

	public static Boolean isCommercial(final int id) {
		Object a;

		a = FindCommercial.repository.getOneById(id);

		return a instanceof Commercial ? true : false;
	}

}
