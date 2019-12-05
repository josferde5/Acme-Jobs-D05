<%@ page language="java" %>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:form>
	<acme:form-textbox code="anonymous.bulletin.form.label.author" path="author" />
	<acme:form-textarea code="anonymous.bulletin.form.label.text" path="text" />
	<acme:form-textbox code="anonymous.bulletin.form.label.contact" path="contact" />
	
	<acme:form-submit code="anonymous.bulletin.form.button.create" action="/anonymous/pantoja-bulletin/create" />
	<acme:form-return code="anonymous.bulletin.form.button.return" />
</acme:form>