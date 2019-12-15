<%@ page language="java" %>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:list>
	<acme:list-column code="auditor.auditRecord.list.label.title" path="title" width="50%" />
	<acme:list-column code="auditor.auditRecord.list.label.creationMoment" path="creationMoment" width="25%" />
	<jstl:if test="${command == 'list_mine'}">
		<acme:list-column code="auditor.auditRecord.list.label.jobTitle" path="job.title" width="25%" />
	</jstl:if>
</acme:list>
