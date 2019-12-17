<%@ page language="java" %>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:list>
	<acme:list-column code="authenticated.userInThread.list.label.username" path="authenticated.userAccount.username" width="20%" />
	<acme:list-column code="authenticated.userInThread.list.label.name" path="authenticated.identity.name" width="20%" />
	<acme:list-column code="authenticated.userInThread.list.label.surname" path="authenticated.identity.surname" width="40%" />
</acme:list>