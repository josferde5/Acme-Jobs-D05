<%@ page language="java" %>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:list>
	<acme:list-column code="provider.request.list.label.deadline" path="deadline" width="20%" />
	<acme:list-column code="provider.request.list.label.title" path="title" width="40%" />
</acme:list>
<acme:form>
	<acme:form-submit code="provider.request.list.button.new" action="/provider/request/create/" method="get"/>
</acme:form>