
package acme.entities.applications;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Application extends DomainEntity {

	private static final long	serialVersionUID	= 2L;

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^[a-zA-Z]{4}[-]{1}[a-zA-Z]{4}[:]{1}[a-zA-Z]{4}$")
	private String				referenceNumber;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				creationMoment;

	@NotBlank
	private String				statement;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Status				status;

	@NotBlank
	private String				skills;

	@NotBlank
	private String				qualifications;

	///Add justification for rejected or denied D05

	@NotNull
	private String				justification;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Worker				worker;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Job					job;
}
