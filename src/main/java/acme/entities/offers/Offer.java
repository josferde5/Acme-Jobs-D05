
package acme.entities.offers;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Offer extends DomainEntity {

	private static final long	serialVersionUID	= 4L;

	@NotBlank
	private String				title;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				creationMoment;

	@Temporal(TemporalType.TIMESTAMP)
	private Date				deadline;

	@NotBlank
	private String				text;

	@NotNull
	@Valid
	private Money				minMoney;

	@NotNull
	@Valid
	private Money				maxMoney;

	@NotBlank
	@Pattern(regexp = "^(O[A-Z]{4}-[0-9]{5})$")
	@Column(unique = true)
	private String				ticker;


	//Derived attributes

	@Transient
	public String getMoneyRange() {
		StringBuilder result;
		result = new StringBuilder();
		result.append(this.minMoney.getCurrency() + " ");
		result.append(this.minMoney.getAmount());
		result.append(" - ");
		result.append(this.maxMoney.getCurrency() + " ");
		result.append(this.maxMoney.getAmount());
		return result.toString();
	}

}
