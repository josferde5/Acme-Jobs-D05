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

<acme:form>
	
	<jstl:if test="${command == 'show' && (auditorId != principalId || status!='draft')}">
		<acme:form-textbox code="auditor.auditRecord.form.label.title" path="title" readonly="true"/>
		<acme:form-moment code="auditor.auditRecord.form.label.creationMoment" path="creationMoment" readonly="true"/>
		<acme:form-textarea code="auditor.auditRecord.form.label.body" path="body" readonly="true"/>
		<acme:form-textbox code="auditor.auditRecord.form.label.status" path="status" readonly="true"/>
	</jstl:if>
		
	<jstl:if test="${command == 'show' && auditorId == principalId && status=='draft'}">
		<acme:form-textbox code="auditor.auditRecord.form.label.title" path="title"/>
		<acme:form-moment code="auditor.auditRecord.form.label.creationMoment" path="creationMoment" readonly="true"/>
		<acme:form-textarea code="auditor.auditRecord.form.label.body" path="body"/>
		<acme:form-checkbox code="auditor.auditRecord.form.label.finalMode" path="finalMode"/>
	</jstl:if>
	
	<jstl:if test="${command == 'create'}">
		<acme:form-textbox code="auditor.auditRecord.form.label.title" path="title"/>
		<acme:form-textarea code="auditor.auditRecord.form.label.body" path="body"/>
		<acme:form-checkbox code="auditor.auditRecord.form.label.finalMode" path="finalMode"/>
		<acme:form-hidden path="idJob" />
	</jstl:if>
	
	<acme:form-submit test="${command == 'create'}" method="post"
		code="auditor.auditRecord.form.button.create" 
		action="/auditor/audit-record/create/"/>
	<acme:form-submit test="${command == 'show' && auditorId == principalId && status=='draft'}" method="post"
		code="auditor.auditRecord.form.button.update" 
		action="/auditor/audit-record/update/"/>
		
  	<acme:form-return code="auditor.auditRecord.form.button.return"/>
</acme:form>