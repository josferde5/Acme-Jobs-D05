<%@ page language="java" %>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:form>
	<acme:form-textbox code="employer.application.form.label.referenceNumber" path="referenceNumber"/>
	<acme:form-textbox code="employer.application.form.label.creationMoment" path="creationMoment" />
	<acme:form-textbox code="employer.application.form.label.statement" path="statement" />
	<acme:form-textbox code="employer.application.form.label.status" path="status"  />
	<acme:form-textarea code="employer.application.form.label.skills" path="skills" />
	<acme:form-textarea code="employer.application.form.label.qualifications" path="qualifications"  />	
	<acme:form-textarea code="employer.application.formt.label.justification" path="justification"/>
	<jstl:if test="${command == 'show' }">
		<acme:form-submit code="employer.application.form.button.update" action="/employer/application/update"/>
	</jstl:if>
	<jstl:if test="${command == 'update' }">
		<acme:form-submit code="employer.application.form.button.update" action="/employer/application/update"/>
	</jstl:if>
	<acme:form-return code="employer.application.form.button.return" />
</acme:form>