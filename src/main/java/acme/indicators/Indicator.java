
package acme.indicators;

import java.io.Serializable;
import java.util.Map;

import acme.framework.datatypes.Money;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Indicator implements Serializable {

	//Serialization indentifier

	private static final long	serialVersionUID	= 1L;

	//Attributes

	Integer						totalNumberOfAnnouncements;
	Integer						totalNumberOfCompanyRecords;
	Integer						totalNumberOfInvestorRecords;
	Money						minimumRewardsOfActiveRequests;
	Money						maximumRewardsOfActiveRequests;
	Money						averageRewardsOfActiveRequests;
	Money						standardDeviationOfActiveRequests;
	Money						minimumRewardsOfActiveOffers;
	Money						maximumRewardsOfActiveOffers;
	Money						averageRewardsOfActiveOffers;
	Money						standardDeviationOfActiveOffers;
	Map<String, Integer>		companiesBySector;
	Map<String, Integer>		investorsBySector;

	//Attributes (D04)

	Double						averageNumberOfJobsPerEmployer;
	Double						averageNumberOfApplicationsPerEmployer;
	Double						averageNumberOfApplicationsPerWorker;
	Double						ratioOfDraftJobs;
	Double						ratioOfPublishedJobs;
	Double						ratioOfPendingApplications;
	Double						ratioOfAcceptedApplications;
	Double						ratioOfRejectedApplications;

}
