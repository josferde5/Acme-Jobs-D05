<%@ page language="java" %>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:list>
	<acme:list-column code="auditor.duty.form.label.duty.title" path="title" width="20%" />
	<acme:list-column code="auditor.duty.form.label.duty.percentTime" path="percentTime" width="30%" />
</acme:list>
