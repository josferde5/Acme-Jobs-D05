
package acme.features.administrator.indicators;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.companyrecords.CompanyRecord;
import acme.entities.investorRecords.InvestorRecord;
import acme.entities.offers.Offer;
import acme.entities.requests.Request;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorIndicatorsRepository extends AbstractRepository {

	@Query("select count(a) from Announcement a")
	Integer totalNumberOfAnnouncements();

	@Query("select count(c) from CompanyRecord c")
	Integer totalNumberOfCompanyRecords();

	@Query("select count(i) from InvestorRecord i")
	Integer totalNumberOfInvestorRecords();

	@Query("select r from Request r where r.deadline > curdate()")
	Collection<Request> activeRequests();

	@Query("select o from Offer o where o.deadline > curdate()")
	Collection<Offer> activeOffers();

	@Query("select a from CompanyRecord a")
	Collection<CompanyRecord> companyRecords();

	@Query("select a from InvestorRecord a")
	Collection<InvestorRecord> investorRecords();

	//D04

	@Query("select avg(select count(j) from Job j where j.employer.id = e.id) from Employer e")
	Double averageNumberOfJobsPerEmployer();

	@Query("select avg(select count(a) from Application a where a.job.employer.id = e.id) from Employer e")
	Double averageNumberOfApplicationsPerEmployer();

	@Query("select avg(select count(a) from Application a where a.worker.id = w.id) from Worker w")
	Double averageNumberOfApplicationsPerWorker();

	@Query("select 1.0 * count(j) / (select count(b) from Job b) from Job j where j.finalMode = 0")
	Double ratioOfDraftJobs();

	@Query("select 1.0 * count(j) / (select count(b) from Job b) from Job j where j.finalMode = 1")
	Double ratioOfPublishedJobs();

	@Query("select 1.0 * count(a) / (select count(b) from Application b) from Application a where a.status = acme.entities.applications.Status.PENDING")
	Double ratioOfPendingApplications();

	@Query("select 1.0 * count(a) / (select count(b) from Application b) from Application a where a.status = acme.entities.applications.Status.ACEPTED")
	Double ratioOfAcceptedApplications();

	@Query("select 1.0 * count(a) / (select count(b) from Application b) from Application a where a.status = acme.entities.applications.Status.REJECTED")
	Double ratioOfRejectedApplications();

	@Query("select count(a), DATE(a.creationMoment) as dateApp from Application a where a.status = acme.entities.applications.Status.PENDING and DATE(a.creationMoment) > ?1 group by DATE(a.creationMoment) order by dateApp asc")
	Collection<Object[]> pendingApplicationsPerDayLastFourMonths(Date moment);

	@Query("select count(a), DATE(a.creationMoment) as dateApp from Application a where a.status = acme.entities.applications.Status.ACEPTED and DATE(a.creationMoment) > ?1 group by DATE(a.creationMoment) order by dateApp asc")
	Collection<Object[]> acceptedApplicationsPerDayLastFourMonths(Date moment);

	@Query("select count(a), DATE(a.creationMoment) as dateApp from Application a where a.status = acme.entities.applications.Status.REJECTED and DATE(a.creationMoment) > ?1 group by DATE(a.creationMoment) order by dateApp asc")
	Collection<Object[]> rejectedApplicationsPerDayLastFourMonths(Date moment);

}
