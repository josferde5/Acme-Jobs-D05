
package acme.entities.banners;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Commercial extends Banner {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	@CreditCardNumber
	@NotBlank
	private String				creditCard;

}
