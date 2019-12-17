<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="administrator.auditorRequest.form.label.username" path="authenticated.userAccount.username"/>
	<acme:form-textbox code="administrator.auditorRequest.form.label.firm" path="firm"/>
	<acme:form-textbox code="administrator.auditorRequest.form.label.responsibilityStatement" path="responsibilityStatement"/>
	
	<acme:form-submit code="administrator.auditorRequest.form.button.accept" action="/administrator/auditor-request/accept"/>
	<acme:form-submit code="administrator.auditorRequest.form.button.reject" action="/administrator/auditor-request/reject"/>
	<acme:form-return code="administrator.auditorRequest.form.button.return"/>
</acme:form>
