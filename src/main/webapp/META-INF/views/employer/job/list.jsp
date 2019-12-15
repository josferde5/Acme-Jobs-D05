<%@ page language="java" %>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:list>
	<acme:list-column code="employer.job.list.label.reference" path="reference" width="20%" />
	<acme:list-column code="employer.job.list.label.title" path="title" width="30%" />
	<acme:list-column code="employer.job.list.label.deadline" path="deadline" width="30%" />
</acme:list>
<<acme:form-submit code="employer.job.form.label.create" action="/employer/job/create" method="get"/>
