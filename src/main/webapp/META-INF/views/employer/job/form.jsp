<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<%
	String idDesc= String.format("%d", request.getAttribute("descriptorId"));
	request.setAttribute("idDesc", idDesc);
	String idJob= String.format("%d", request.getAttribute("jobId"));
	request.setAttribute("idJob", idJob);
%>

<acme:form>
	<acme:form-textbox code="employer.job.form.label.reference" path="reference" />
	<acme:form-textbox code="employer.job.form.label.title" path="title" />
	<acme:form-moment code="employer.job.form.label.deadline" path="deadline" />
	<acme:form-money code="employer.job.form.label.salary" path="salary" />
	<jstl:if test="${command != 'create' }">
		<acme:form-textbox code="employer.job.form.label.status" path="status" />
	</jstl:if>
	
	<acme:form-url code="employer.job.form.label.moreInfo" path="moreInfo"/>
	<acme:form-textarea code="employer.job.form.label.descriptor.description" path="descriptor.description" />
	<acme:form-checkbox code="employer.job.form.label.finalMode" path="finalMode"/>

	
	<jstl:if test="${command == 'show' }">
		<acme:form-submit code="employer.job.form.label.delete" action="/employer/job/delete"/>
	</jstl:if>
	<jstl:if test="${command == 'show' and status != 'published' }">
		<acme:form-submit code="employer.job.form.label.update" action="/employer/job/update"/>
	</jstl:if>
	<jstl:if test="${command == 'delete' }">
		<acme:form-submit code="employer.job.form.label.delete" action="/employer/job/delete"/>
	</jstl:if>
	<jstl:if test="${command == 'update' }">
		<acme:form-submit code="employer.job.form.label.update" action="/employer/job/update"/>
	</jstl:if>
	<jstl:if test="${command == 'create' }">
		<acme:form-submit code="employer.job.form.label.create" action="/employer/job/create"/>
	</jstl:if>
	
  	<acme:form-return code="employer.job.form.button.return"/>
</acme:form>
<br>
<jstl:if test="${command == 'show' and status != 'published' }">
<acme:form>
<acme:form-submit code="employer.duty.form.button.create" action="/employer/duty/create?id=${idDesc}" method= "get"/>
</acme:form>
</jstl:if>

<jstl:if test="${command == 'show' }">
<br>
	<acme:form>
	<acme:form-submit method="get" code="employer.job.form.button.list.duties" action="/employer/duty/list_duties?id=${idDesc}"/>
</acme:form>
<br>
<acme:form>
	<acme:form-submit method="get" code="employer.job.form.button.list.auditRecords" action="/employer/audit-record/list?id=${idJob}"/>
</acme:form>
<br>
<acme:form>
	<acme:form-submit method="get" code="employer.job.form.button.list.applications" action="/employer/application/list?id=${idJob}"/>
</acme:form>
<br>
<acme:form>
	<acme:form-submit method="get" code="employer.job.form.button.list_creation.applications" action="/employer/application/list_group_creation?id=${idJob}"/>
</acme:form>
<br>
<acme:form>
	<acme:form-submit method="get" code="employer.job.form.button.list_reference.applications" action="/employer/application/list_group_reference?id=${idJob}"/>
</acme:form>
<br>
<acme:form>
	<acme:form-submit method="get" code="employer.job.form.button.list_status.applications" action="/employer/application/list_group_status?id=${idJob}"/>
</acme:form>
</jstl:if>

