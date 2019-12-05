<%@ page language="java" %>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.request.form.label.ticker" path="ticker" />
	<acme:form-textbox code="authenticated.request.form.label.title" path="title" />
	<acme:form-moment code="authenticated.request.form.label.moment" path="creationMoment" />
	<acme:form-moment code="authenticated.request.form.label.deadline" path="deadline" />
	<acme:form-textarea code="authenticated.request.form.label.description" path="description" />
	<acme:form-money code="authenticated.request.form.label.reward" path="reward"/>
	
	<acme:form-return code="authenticated.announcement.form.button.return" />
</acme:form>
