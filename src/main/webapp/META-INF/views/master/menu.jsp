<%--
- menu.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper,acme.entities.roles.Provider,acme.entities.roles.Consumer,acme.entities.roles.Employer"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.favourite-link1" action="http://www.google.com/"/>
        	<acme:menu-suboption code="master.menu.anonymous.favourite-link" action="https://reminderpro.appspot.com/"/>
        	<acme:menu-suboption code="master.menu.anonymous.favourite-link3" action="https://www.github.com/"/>
        	<acme:menu-suboption code="master.menu.anonymous.favourite-link2" action="http://soymotor.com/"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.provider" access="hasRole('Provider')">
			<acme:menu-suboption code="master.menu.provider.favourite-link" action="http://www.example.com/"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.consumer" access="hasRole('Consumer')">
			<acme:menu-suboption code="master.menu.consumer.favourite-link" action="http://www.example.com/"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.anonymous.functionalities" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.announcement.list" action="/anonymous/announcement/list"/>
			<acme:menu-suboption code="master.menu.anonymous.companyRecords.list" action="/anonymous/company-record/list"/>
			<acme:menu-suboption code="master.menu.anonymous.companyRecords.listTop" action="/anonymous/company-record/list-top"/>
			<acme:menu-suboption code="master.menu.anonymous.investorRecords.list" action="/anonymous/investor-record/list"/>
			<acme:menu-suboption code="master.menu.anonymous.investorRecords.listTop" action="/anonymous/investor-record/list-top"/>
    	</acme:menu-option>

		<acme:menu-option code="master.menu.authenticated.functionalities" access="isAuthenticated()&&!hasRole('Administrator')&&!hasRole('Consumer')&&!hasRole('Provider')&&!hasRole('Employer')&&!hasRole('Auditor')&&!hasRole('Worker')&&!hasRole('Sponsor')">
			<acme:menu-suboption code="master.menu.authenticated.announcement.list" action="/authenticated/announcement/list"/>
			<acme:menu-suboption code="master.menu.authenticated.companyRecords.list" action="/authenticated/company-record/list"/>
			<acme:menu-suboption code="master.menu.authenticated.investorRecords.list" action="/authenticated/investor-record/list"/>
			<acme:menu-suboption code="master.menu.authenticated.requests.list" action="/authenticated/request/list"/>
			<acme:menu-suboption code="master.menu.authenticated.offers.list" action="/authenticated/offer/list"/>
			<acme:menu-suboption code="master.menu.authenticated.challenges.list" action="/authenticated/challenge/list"/>
			<acme:menu-suboption code="master.menu.authenticated.jobs.list" action="/authenticated/job/list"/>
			<acme:menu-suboption code="master.menu.authenticated.messageThread.listMine" action="/authenticated/message-thread/list_mine"/>
   			<acme:menu-suboption code="master.menu.authenticated.auditorRequest.create" action="/authenticated/auditor-request/create"/>
    	</acme:menu-option>
    	
    	<acme:menu-option code="master.menu.provider.functionalities" access="hasRole('Provider')">
			<acme:menu-suboption code="master.menu.provider.announcement.list" action="/authenticated/announcement/list"/>
			<acme:menu-suboption code="master.menu.provider.companyRecords.list" action="/authenticated/company-record/list"/>
			<acme:menu-suboption code="master.menu.provider.investorRecords.list" action="/authenticated/investor-record/list"/>
			<acme:menu-suboption code="master.menu.provider.requests.list" action="/provider/request/list"/>
			<acme:menu-suboption code="master.menu.provider.offers.list" action="/authenticated/offer/list"/>
			<acme:menu-suboption code="master.menu.provider.challenges.list" action="/authenticated/challenge/list"/>
    	</acme:menu-option>
    	
    	<acme:menu-option code="master.menu.consumer.functionalities" access="hasRole('Consumer')">
			<acme:menu-suboption code="master.menu.consumer.announcement.list" action="/authenticated/announcement/list"/>
			<acme:menu-suboption code="master.menu.consumer.companyRecords.list" action="/authenticated/company-record/list"/>
			<acme:menu-suboption code="master.menu.consumer.investorRecords.list" action="/authenticated/investor-record/list"/>
			<acme:menu-suboption code="master.menu.consumer.requests.list" action="/authenticated/request/list"/>
			<acme:menu-suboption code="master.menu.consumer.offers.list" action="/consumer/offer/list"/>
			<acme:menu-suboption code="master.menu.consumer.challenges.list" action="/authenticated/challenge/list"/>
    	</acme:menu-option>
    	
    	<acme:menu-option code="master.menu.employer.functionalities" access="hasRole('Employer')">
    		<acme:menu-suboption code="master.menu.employer.job.list" action="/employer/job/list_mine"/>
    	</acme:menu-option>
    	
    	<acme:menu-option code="master.menu.auditor.functionalities" access="hasRole('Auditor')">
    		<acme:menu-suboption code="master.menu.auditor.job.listMine" action="/auditor/job/list_mine"/>
    		<acme:menu-suboption code="master.menu.auditor.job.listNotMine" action="/auditor/job/list_not_mine"/>
    	</acme:menu-option>
    	
    	<acme:menu-option code="master.menu.worker.functionalities" access="hasRole('Worker')">
    		<acme:menu-suboption code="master.menu.worker.application.listMine" action="/worker/application/list_mine"/>
    	</acme:menu-option>
    	
    	<acme:menu-option code="master.menu.sponsor.functionalities" access="hasRole('Sponsor')">
    		<acme:menu-suboption code="master.menu.sponsor.banner.listMine" action="/sponsor/banner/list_mine"/>
    	</acme:menu-option>
    	
    	<acme:menu-option code="master.menu.administrator.functionalities" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.announcement.list" action="/administrator/announcement/list"/>
			<acme:menu-suboption code="master.menu.administrator.challenge.list" action="/administrator/challenge/list"/>
    		<acme:menu-suboption code="master.menu.administrator.investorRecord.list" action="/administrator/investor-record/list"/>
			<acme:menu-suboption code="master.menu.administrator.companyRecord.list" action="/administrator/company-record/list"/>
			<acme:menu-suboption code="master.menu.administrator.banner.list" action="/administrator/banner/list"/>
			<acme:menu-suboption code="master.menu.administrator.auditorRequest.list" action="/administrator/auditor-request/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.customisationParameters.display" action="/administrator/customisation-parameters/display"/>
			<acme:menu-suboption code="master.menu.administrator.indicators.display" action="/administrator/indicator/display"/>
    	</acme:menu-option>
	
		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shutdown" action="/master/shutdown"/>
		</acme:menu-option>
	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			<acme:menu-suboption code="master.menu.user-account.become-provider" action="/authenticated/provider/create" access="!hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.provider" action="/authenticated/provider/update" access="hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.become-consumer" action="/authenticated/consumer/create" access="!hasRole('Consumer')"/>
			<acme:menu-suboption code="master.menu.user-account.consumer" action="/authenticated/consumer/update" access="hasRole('Consumer')"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>

