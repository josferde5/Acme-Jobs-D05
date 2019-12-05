<%@ page language="java" %>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:list>
	<acme:list-column code="anonymous.companyrecord.list.label.company" path="company" width="20%" />
	<acme:list-column code="anonymous.companyrecord.list.label.website" path="website" width="40%" />
</acme:list>