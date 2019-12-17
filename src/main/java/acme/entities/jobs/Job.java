
package acme.entities.jobs;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.descriptors.Descriptor;
import acme.entities.roles.Employer;
import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Job extends DomainEntity {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	@Column(unique = true)
	@NotBlank
	@Length(min = 5, max = 10)
	@Pattern(regexp = "^([A-Z]{4}-[A-Z]{4})$")
	private String				reference;

	@NotBlank
	private String				title;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				deadline;

	@NotNull
	@Valid
	private Money				salary;

	@URL
	private String				moreInfo;

	private boolean				finalMode;


	////////Derived Attributes
	@Transient
	public String getStatus() {
		StringBuilder res = new StringBuilder();
		if (this.finalMode) {
			res.append("published");
		} else {
			res.append("draft");
		}
		return res.toString();

	}


	///////Relationships
	@NotNull //Validate in client
	@Valid ///Validates the value recursively
	@ManyToOne(optional = false) //validate in DB (for this reason we use NotNull, for avoid 500 error framework) 
	private Employer	employer;

	@NotNull
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	private Descriptor	descriptor;
}
