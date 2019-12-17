<%@ page language="java"%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.sponsor.sponsor.form.label.organisationName" path="organisationName" />
	<acme:form-textbox code="authenticated.sponsor.sponsor.form.label.creditCard" path="creditCard" />

	<acme:form-submit test="${command == 'create'}" code="authenticated.sponsor.sponsor.form.button.create"
		action="/authenticated/sponsor/create" />
	<acme:form-submit test="${command == 'update'}" code="authenticated.sponsor.sponsor.form.button.update"
		action="/authenticated/sponsor/update" />
	<acme:form-return code="authenticated.sponsor.sponsor.form.button.return" />
</acme:form>
