
package acme.entities.descriptors;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.entities.duties.Duty;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Descriptor extends DomainEntity {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				description;

	/////Relationships
	@NotNull
	@Valid
	@OneToMany(cascade = CascadeType.ALL)///Need custom validation for mandatory fetch = FetchType.EAGER, cascade = CascadeType.ALL
	private Collection<Duty>	duties;

}
