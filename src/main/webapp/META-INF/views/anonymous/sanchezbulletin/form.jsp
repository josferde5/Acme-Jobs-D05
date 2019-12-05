<%@ page language="java" %>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:form>
	<acme:form-textbox code="anonymous.sanchezbulletin.form.label.author" path="author" />
	<acme:form-textarea code="anonymous.sanchezbulletin.form.label.text" path="text" />
	
	<acme:form-submit code="anonymous.sanchezbulletin.form.button.create" action="/anonymous/sanchez-bulletin/create" />
	<acme:form-return code="anonymous.sanchezbulletin.form.button.return" />

</acme:form>