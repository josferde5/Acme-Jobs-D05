<%@ page language="java" %>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:form>
	<acme:form-textbox code="anonymous.raposoBulletin.form.label.author" path="author" />
	<acme:form-textbox code="anonymous.raposoBulletin.form.label.place" path="place" />
	<acme:form-textarea code="anonymous.raposoBulletin.form.label.text" path="text" />
	
	<acme:form-submit code="anonymous.raposoBulletin.form.button.create" action="/anonymous/raposo-bulletin/create" />
	<acme:form-return code="anonymous.raposoBulletin.form.button.return" />
</acme:form>