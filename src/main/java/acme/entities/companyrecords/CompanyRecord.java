
package acme.entities.companyrecords;

import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CompanyRecord extends DomainEntity {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				company;

	@Valid
	private Boolean				incorporated;

	@NotBlank
	private String				sector;

	@NotBlank
	private String				ceo;

	@NotBlank
	private String				description;

	@NotBlank
	@Pattern(regexp = "^([+]{1}[1-9][0-9]{0,2})?[(]{1}[0-9]{1,4}[)]{1}[0-9]{6,10}$")
	private String				telephone;

	@NotBlank
	@URL
	private String				website;

	@NotBlank
	@Email
	private String				email;

	@Range(min = 0, max = 5)
	private Integer				stars;

}
