<%@ page language="java" %>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:list readonly="true">
	<acme:list-column code="anonymous.bulletin.list.label.moment" path="moment" width="10%" />
	<acme:list-column code="anonymous.bulletin.list.label.company" path="company" width="15%" />
	<acme:list-column code="anonymous.bulletin.list.label.deadline" path="deadline" width="15%" />
	<acme:list-column code="anonymous.bulletin.list.label.description" path="description" width="50%" />
	<acme:list-column code="anonymous.bulletin.list.label.contact" path="contact" width="10%" />
</acme:list>