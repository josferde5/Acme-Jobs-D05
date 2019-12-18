
package acme.features.administrator.indicators;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.companyrecords.CompanyRecord;
import acme.entities.investorRecords.InvestorRecord;
import acme.entities.offers.Offer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.datatypes.Money;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;
import acme.indicators.Indicator;

@Service
public class AdministratorIndicatorsDisplayService implements AbstractShowService<Administrator, Indicator> {

	@Autowired
	AdministratorIndicatorsRepository repository;


	@Override
	public boolean authorise(final Request<Indicator> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Indicator> request, final Indicator entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "totalNumberOfAnnouncements", "totalNumberOfCompanyRecords", "totalNumberOfInvestorRecords", "minimumRewardsOfActiveRequests", "maximumRewardsOfActiveRequests", "averageRewardsOfActiveRequests",
			"standardDeviationOfActiveRequests", "minimumRewardsOfActiveOffers", "maximumRewardsOfActiveOffers", "averageRewardsOfActiveOffers", "standardDeviationOfActiveOffers", "companiesBySector", "investorsBySector", "averageNumberOfJobsPerEmployer",
			"averageNumberOfApplicationsPerEmployer", "averageNumberOfApplicationsPerWorker", "ratioOfDraftJobs", "ratioOfPublishedJobs", "ratioOfPendingApplications", "ratioOfAcceptedApplications", "ratioOfRejectedApplications",
			"pendingApplicationsPerDayLastFourWeeks", "acceptedApplicationsPerDayLastFourWeeks", "rejectedApplicationsPerDayLastFourWeeks");

	}

	@Override
	public Indicator findOne(final Request<Indicator> request) {
		assert request != null;

		Indicator result = new Indicator();

		result.setTotalNumberOfAnnouncements(this.repository.totalNumberOfAnnouncements());
		result.setTotalNumberOfCompanyRecords(this.repository.totalNumberOfCompanyRecords());
		result.setTotalNumberOfInvestorRecords(this.repository.totalNumberOfInvestorRecords());

		result.setMinimumRewardsOfActiveRequests(this.repository.activeRequests().stream().map(x -> x.getReward()).min((x, y) -> x.getAmount().compareTo(y.getAmount())).get());
		result.setMaximumRewardsOfActiveRequests(this.repository.activeRequests().stream().map(x -> x.getReward()).max((x, y) -> x.getAmount().compareTo(y.getAmount())).get());
		Money avgRewReq = new Money();
		avgRewReq.setAmount(this.repository.activeRequests().stream().mapToDouble(x -> x.getReward().getAmount()).average().getAsDouble());
		avgRewReq.setCurrency(this.repository.activeRequests().stream().findFirst().get().getReward().getCurrency());
		result.setAverageRewardsOfActiveRequests(avgRewReq);
		Money sdRewReq = new Money();
		sdRewReq.setAmount(AdministratorIndicatorsDisplayService.calculateSd(this.repository.activeRequests().stream().map(x -> x.getReward().getAmount()).collect(Collectors.toList())));
		sdRewReq.setCurrency(this.repository.activeRequests().stream().findFirst().get().getReward().getCurrency());
		result.setStandardDeviationOfActiveRequests(sdRewReq);

		result.setMinimumRewardsOfActiveOffers(this.repository.activeOffers().stream().map(x -> x.getMinMoney()).min((x, y) -> x.getAmount().compareTo(y.getAmount())).get());
		result.setMaximumRewardsOfActiveOffers(this.repository.activeOffers().stream().map(x -> x.getMaxMoney()).max((x, y) -> x.getAmount().compareTo(y.getAmount())).get());

		Money avgRewOff = new Money();
		avgRewOff.setAmount(AdministratorIndicatorsDisplayService.calculateAverage(this.repository.activeOffers()));
		avgRewOff.setCurrency(this.repository.activeOffers().stream().findFirst().get().getMaxMoney().getCurrency());
		result.setAverageRewardsOfActiveOffers(avgRewOff);

		Money sdRewOff = new Money();
		sdRewOff.setAmount(AdministratorIndicatorsDisplayService.calculateSd(AdministratorIndicatorsDisplayService.calculateAvgInter(this.repository.activeOffers())));
		sdRewOff.setCurrency(this.repository.activeOffers().stream().findFirst().get().getMaxMoney().getCurrency());
		result.setStandardDeviationOfActiveOffers(sdRewOff);

		Map<String, Integer> companiesBySec = this.createCompaniesBySec(this.repository.companyRecords());
		Map<String, Integer> investorsBySec = this.createInvestorsBySec(this.repository.investorRecords());
		result.setCompaniesBySector(companiesBySec);
		result.setInvestorsBySector(investorsBySec);

		//D04

		result.setAverageNumberOfApplicationsPerEmployer(this.repository.averageNumberOfApplicationsPerEmployer());
		result.setAverageNumberOfApplicationsPerWorker(this.repository.averageNumberOfApplicationsPerWorker());
		result.setAverageNumberOfJobsPerEmployer(this.repository.averageNumberOfJobsPerEmployer());
		result.setRatioOfDraftJobs(this.repository.ratioOfDraftJobs());
		result.setRatioOfPublishedJobs(this.repository.ratioOfPublishedJobs());
		result.setRatioOfPendingApplications(this.repository.ratioOfPendingApplications());
		result.setRatioOfAcceptedApplications(this.repository.ratioOfAcceptedApplications());
		result.setRatioOfRejectedApplications(this.repository.ratioOfRejectedApplications());

		//D05
		Date d = new Date(System.currentTimeMillis() - 2419200000L); //The date 4 weeks ago

		Map<String, Integer> pendingAppsLastFourWeeksMap;
		Collection<Object[]> pendingAppsLastFourWeeks;
		pendingAppsLastFourWeeks = this.repository.pendingApplicationsPerDayLastFourWeeks(d);
		pendingAppsLastFourWeeksMap = this.appsByDate(pendingAppsLastFourWeeks);
		result.setPendingApplicationsPerDayLastFourWeeks(pendingAppsLastFourWeeksMap);

		Map<String, Integer> acceptedAppsLastFourWeeksMap;
		Collection<Object[]> acceptedAppsLastFourWeeks;
		acceptedAppsLastFourWeeks = this.repository.acceptedApplicationsPerDayLastFourWeeks(d);
		acceptedAppsLastFourWeeksMap = this.appsByDate(acceptedAppsLastFourWeeks);
		result.setAcceptedApplicationsPerDayLastFourWeeks(acceptedAppsLastFourWeeksMap);

		Map<String, Integer> rejectedAppsLastFourWeeksMap;
		Collection<Object[]> rejectedAppsLastFourWeeks;
		rejectedAppsLastFourWeeks = this.repository.rejectedApplicationsPerDayLastFourWeeks(d);
		rejectedAppsLastFourWeeksMap = this.appsByDate(rejectedAppsLastFourWeeks);
		result.setRejectedApplicationsPerDayLastFourWeeks(rejectedAppsLastFourWeeksMap);
		return result;
	}

	private Map<String, Integer> appsByDate(final Collection<Object[]> appsLastFourWeeks) {
		Comparator<String> cmpS = Comparator.naturalOrder();
		Map<String, Integer> res = new TreeMap<>(cmpS);
		Comparator<Date> cmpDate = Comparator.naturalOrder();
		SortedSet<Date> ss = new TreeSet<>(cmpDate);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		for (Object[] o : appsLastFourWeeks) {
			Date d = (Date) o[1];
			ss.add(d);
			String dateParsed = formatter.format(d);
			Integer i = ((Long) o[0]).intValue();
			res.put(dateParsed, i);
		}

		Iterator<Date> it = ss.iterator();

		Date dateComparing = new Date(System.currentTimeMillis() - 2419200000L);
		Calendar cDateComparing = Calendar.getInstance();
		cDateComparing.setTime(dateComparing);

		boolean first = true;
		Calendar c = Calendar.getInstance();
		int i;

		//From 28 days ago to the last date

		while (it.hasNext()) {
			Date d = it.next();
			c.setTime(d);
			i = c.get(Calendar.DAY_OF_YEAR) - cDateComparing.get(Calendar.DAY_OF_YEAR);
			i = i > 28 ? cDateComparing.get(Calendar.YEAR) % 4 == 0 ? 366 - i : 365 - i : i;

			if (first) {
				first = false;
			}
			long millis = cDateComparing.getTimeInMillis();
			for (int j = 0; j < i; j++) {
				Date toAdd = new Date(millis);
				String dateParsed = formatter.format(toAdd);
				res.put(dateParsed, 0);
				millis += 86400000L;
			}
			millis = c.getTimeInMillis() + 86400000L;
			cDateComparing.setTimeInMillis(millis);
			formatter.setCalendar(cDateComparing);
		}

		//From the last date to today

		Date today = new Date(System.currentTimeMillis() - 1);
		c.setTime(today);
		i = c.get(Calendar.DAY_OF_YEAR) - cDateComparing.get(Calendar.DAY_OF_YEAR);
		i = i > 28 ? cDateComparing.get(Calendar.YEAR) % 4 == 0 ? 366 - i + 1 : 365 - i + 1 : i + 1;

		long millis = cDateComparing.getTimeInMillis();
		for (int j = 0; j < i; j++) {
			Date toAdd = new Date(millis);
			String dateParsed = formatter.format(toAdd);
			res.put(dateParsed, 0);
			millis += 86400000L;
		}

		return res;
	}

	private Map<String, Integer> createCompaniesBySec(final Collection<CompanyRecord> companyRecords) {
		Map<String, Integer> res = new HashMap<>();
		for (CompanyRecord c : companyRecords) {
			if (res.containsKey(c.getSector())) {
				res.put(c.getSector(), res.get(c.getSector()) + 1);
			} else {
				res.put(c.getSector(), 1);
			}
		}
		return res;
	}

	private Map<String, Integer> createInvestorsBySec(final Collection<InvestorRecord> investorRecords) {
		Map<String, Integer> res = new HashMap<>();
		for (InvestorRecord c : investorRecords) {
			if (res.containsKey(c.getSector())) {
				res.put(c.getSector(), res.get(c.getSector()) + 1);
			} else {
				res.put(c.getSector(), 1);
			}
		}
		return res;
	}

	private static double calculateSd(final List<Double> numList) {
		double sum = 0.0, standardDeviation = 0.0;
		int length = numList.size();
		for (double num : numList) {
			sum += num;
		}
		double mean = sum / length;
		for (double num : numList) {
			standardDeviation += Math.pow(num - mean, 2);
		}
		return Math.sqrt(standardDeviation / length);
	}

	private static List<Double> calculateAvgInter(final Collection<Offer> offers) {
		List<Double> avg = new ArrayList<>();
		for (Offer o : offers) {
			avg.add((o.getMaxMoney().getAmount() + o.getMinMoney().getAmount()) / 2);
		}
		return avg;
	}

	private static Double calculateAverage(final Collection<Offer> offers) {
		Double res = 0.;
		res = AdministratorIndicatorsDisplayService.calculateAvgInter(offers).stream().mapToDouble(x -> x).average().getAsDouble();
		return res;
	}
}
