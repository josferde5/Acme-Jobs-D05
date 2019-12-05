<%@ page language="java" %>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:list>
	<acme:list-column code="sponsor.banner.list.label.slogan" path="slogan" width="60%" />
	<acme:list-column code="sponsor.banner.list.label.type" path="type" width="20%" />
</acme:list>
<acme:form>
	<acme:form-submit code="sponsor.banner.list.button.commercial" action="/sponsor/banner/commercial/create/" method="get"/>
	<acme:form-submit code="sponsor.banner.list.button.nonCommercial" action="/sponsor/banner/non-commercial/create/" method="get"/>
</acme:form>