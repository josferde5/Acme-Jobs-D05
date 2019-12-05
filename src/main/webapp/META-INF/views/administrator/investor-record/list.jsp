<%@ page language="java" %>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:list>
	<acme:list-column code="administrator.investorRecord.list.label.name" path="name" width="30%" />
	<acme:list-column code="administrator.investorRecord.list.label.sector" path="sector" width="30%" />
	<acme:list-column code="administrator.investorRecord.list.label.stars" path="stars" width="10%" />
</acme:list>
<acme:form>
	<acme:form-submit code="administrator.investorRecord.list.button.new" action="/administrator/investor-record/create/" method="get"/>
</acme:form>