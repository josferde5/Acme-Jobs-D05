
package acme.entities.announcements;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Announcement extends DomainEntity {

	private static final long	serialVersionUID	= 3L;

	@NotBlank
	private String				title;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				moment;

	@NotBlank
	@URL
	private String				moreInfo;

	@NotBlank
	private String				text;

}
