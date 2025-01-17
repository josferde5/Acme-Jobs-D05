<%@ page language="java" %>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:list>
	<acme:list-column code="anonymous.investorRecord.list.label.name" path="name" width="30%" />
	<acme:list-column code="anonymous.investorRecord.list.label.sector" path="sector" width="30%" />
	<acme:list-column code="anonymous.investorRecord.list.label.stars" path="stars" width="10%" />
</acme:list>