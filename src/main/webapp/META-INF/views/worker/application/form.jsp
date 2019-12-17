<%@ page language="java"%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%

%>
<acme:form>
	<jstl:if test="${command != 'create'}">
		<acme:form-textbox code="worker.application.form.label.referenceNumber" path="referenceNumber" />
		<acme:form-textbox code="worker.application.form.label.status" path="status" />
		<acme:form-textbox code="worker.application.form.label.creationMoment" path="creationMoment" />
	</jstl:if>
	
	<acme:form-textbox code="worker.application.form.label.statement" path="statement" />
	<acme:form-textarea readonly="true" code="worker.application.form.label.skills" path="skills" />
	<acme:form-textarea readonly="true" code="worker.application.form.label.qualifications" path="qualifications" />
		
	<jstl:if test="${status == 'REJECTED'}">
		<acme:form-textarea readonly="true" code="worker.application.form.label.justification" path="qualificatijustificationons" />
	</jstl:if>
	<jstl:if test="${command == 'create'}">
		<acme:form-hidden path="idJob" />
	</jstl:if>

	<acme:form-submit test="${command == 'create'}" method="post" code="worker.application.form.button.create"
		action="/worker/application/create" />
	<acme:form-return code="worker.application.form.button.return" />
</acme:form>