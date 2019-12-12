
package acme.entities.messageThreads;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import acme.entities.messages.Message;
import acme.framework.entities.Authenticated;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MessageThread extends DomainEntity {

	private static final long					serialVersionUID	= 1L;

	@NotBlank
	private String								title;

	@Temporal(TemporalType.TIMESTAMP)
	private Date								moment;

	//Associations

	@NotNull
	@OneToMany
	private Collection<@Valid Message>			messages;

	@NotEmpty
	@ManyToMany
	private Collection<@Valid Authenticated>	users;

}
