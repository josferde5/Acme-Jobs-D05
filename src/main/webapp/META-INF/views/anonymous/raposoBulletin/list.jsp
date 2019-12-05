<%@ page language="java" %>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:list readonly="true">
	<acme:list-column code="anonymous.raposoBulletin.list.label.moment" path="moment" width="20%" />
	<acme:list-column code="anonymous.raposoBulletin.list.label.author" path="author" width="20%" />
	<acme:list-column code="anonymous.raposoBulletin.list.label.place" path="place" width="20%" />
	<acme:list-column code="anonymous.raposoBulletin.list.label.text" path="text" width="60%" />
</acme:list>