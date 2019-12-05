
package acme.entities.auditRecords;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AuditRecord extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				title;

	@NotBlank
	private String				body;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				creationMoment;

	@Valid
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
	private Auditor	auditor;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Job		job;
}
