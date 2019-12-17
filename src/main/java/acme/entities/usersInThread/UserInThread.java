
package acme.entities.usersInThread;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import acme.entities.messageThreads.MessageThread;
import acme.framework.entities.Authenticated;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserInThread extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@ManyToOne
	@NotNull
	@Valid
	private Authenticated		authenticated;

	@ManyToOne
	@NotNull
	@Valid
	private MessageThread		messageThread;
}
