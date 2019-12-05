<%@ page language="java" %>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:list>
	<acme:list-column code="administrator.banner.list.label.slogan" path="slogan" width="60%" />
	<acme:list-column code="administrator.banner.list.label.type" path="type" width="20%" />
</acme:list>
<acme:form>
	<acme:form-submit code="administrator.banner.list.button.commercial" action="/administrator/banner/commercial/create/" method="get"/>
	<acme:form-submit code="administrator.banner.list.button.nonCommercial" action="/administrator/banner/non-commercial/create/" method="get"/>
</acme:form>