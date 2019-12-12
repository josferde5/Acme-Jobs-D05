<%@ page language="java" %>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:list>
	<acme:list-column code="administrator.auditorRequest.list.label.username" path="authenticated.userAccount.username" width="30%" />
	<acme:list-column code="administrator.auditorRequest.list.label.firm" path="firm" width="30%" />
</acme:list>