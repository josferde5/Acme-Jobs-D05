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
	<acme:form-textbox code="auditor.job.form.label.reference" path="reference" readonly="true"/>
	<acme:form-textbox code="auditor.job.form.label.title" path="title" readonly="true"/>
	<acme:form-textbox code="auditor.job.form.label.deadline" path="deadline" readonly="true"/>
	<acme:form-money code="auditor.job.form.label.salary" path="salary" readonly="true"/>
	<acme:form-textbox code="auditor.job.form.label.status" path="status" readonly="true"/>
	<acme:form-textbox code="auditor.job.form.label.descriptor.description" path="descriptor.description" readonly="true"/>
  	<acme:form-url code="auditor.job.form.label.moreInfo" path="moreInfo"/>
  	<acme:form-return code="auditor.job.form.button.return"/>
</acme:form>
<br>
<acme:form>
	<acme:form-submit method="get" code="auditor.job.form.button.list.duties" action="/auditor/duty/list_duties?id=${idDesc}"/>
</acme:form>
<br>
<acme:form>
	<acme:form-submit method="get" code="auditor.job.form.button.list.auditRecords" action="/auditor/audit-record/list?id=${idJob}"/>
</acme:form>


