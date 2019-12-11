<%@ page language="java"%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%
	String idJob = (String) session.getAttribute("idJob");
	request.setAttribute("idJob", idJob);
	session.setAttribute("idJob", null);
%>
<acme:form>
	<jstl:if test="${command != 'create'}">
		<acme:form-textbox code="worker.application.form.label.referenceNumber" path="referenceNumber" />
		<acme:form-textbox code="worker.application.form.label.status" path="status" />
		<acme:form-textarea code="worker.application.form.label.skills" path="skills" />
		<acme:form-textarea code="worker.application.form.label.qualifications" path="qualifications" />
		<acme:form-textbox code="worker.application.form.label.creationMoment" path="creationMoment" />
	</jstl:if>
	<acme:form-textbox code="worker.application.form.label.statement" path="statement" />

	<jstl:if test="${command == 'create'}">
		<acme:form-hidden path="idJob" />
	</jstl:if>

	<acme:form-submit test="${command == 'create'}" code="worker.application.form.button.create"
		action="/worker/application/create?idJob=${idJob}" />
	<acme:form-return code="worker.application.form.button.return" />
</acme:form>