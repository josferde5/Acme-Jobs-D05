
package acme.entities.banners;

import java.beans.Transient;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

import acme.entities.roles.Sponsor;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public abstract class Banner extends DomainEntity {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 2L;

	@URL
	@NotBlank
	private String				picture;

	@NotBlank
	private String				slogan;

	@URL
	@NotBlank
	private String				target;

	//If sponsor is null means that the banner has been created by the administrator
	@ManyToOne(optional = true)
	private Sponsor				sponsor;


	@Transient
	public String getType() {
		String result;
		result = this instanceof Commercial ? "Commercial" : "Non commercial";
		return result;
	}

}
