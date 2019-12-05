<%@ page language="java" %>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:list>
	<acme:list-column code="administrator.companyrecord.list.label.company" path="company" width="20%" />
	<acme:list-column code="administrator.companyrecord.list.label.website" path="website" width="40%" />
</acme:list>
<acme:form>
	<acme:form-submit code="administrator.companyrecord.list.button.new" action="/administrator/company-record/create/" method="get"/>
</acme:form>