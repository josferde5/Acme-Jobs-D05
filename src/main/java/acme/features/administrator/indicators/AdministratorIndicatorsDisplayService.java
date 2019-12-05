
package acme.features.administrator.indicators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
			"averageNumberOfApplicationsPerEmployer", "averageNumberOfApplicationsPerWorker", "ratioOfDraftJobs", "ratioOfPublishedJobs", "ratioOfPendingApplications", "ratioOfAcceptedApplications", "ratioOfRejectedApplications");

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
		return result;
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
