<%@ page language="java" %>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:form>
	<acme:form-textbox code="anonymous.bulletin.form.label.company" path="company" />
	<acme:form-textbox code="anonymous.bulletin.form.label.contact" path="contact" />
	<acme:form-textarea code="anonymous.bulletin.form.label.description" path="description" />
	<acme:form-moment code="anonymous.bulletin.form.label.deadline" path="deadline"/>
	
	<acme:form-submit code="anonymous.bulletin.form.button.create" action="/anonymous/fernandez-bulletin/create" />
	<acme:form-return code="anonymous.bulletin.form.button.return" />
</acme:form>