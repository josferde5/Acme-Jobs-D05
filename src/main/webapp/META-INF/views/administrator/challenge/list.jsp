<%@ page language="java" %>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:list>
	<acme:list-column code="administrator.challenge.list.label.title" path="title" width="30%" />
	<acme:list-column code="administrator.challenge.list.label.deadline" path="deadline" width="30%" />
</acme:list>
<acme:form>
	<acme:form-submit code="administrator.challenge.list.button.new" action="/administrator/challenge/create/" method="get"/>
</acme:form>