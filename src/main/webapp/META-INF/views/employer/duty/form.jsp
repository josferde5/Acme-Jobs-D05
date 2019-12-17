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

<%
String id3= request.getParameter("id");
request.setAttribute("id3", id3);

%>
<acme:form>
	<acme:form-textbox code="employer.duty.form.label.duty.title" path="title" />
	<acme:form-textarea code="employer.duty.form.label.duty.description" path="description"/>
	<acme:form-textbox code="employer.duty.form.label.duty.percentTime" path="percentTime" />
	
	<jstl:if test="${command == 'create'}">
	
		<acme:form-submit code="employer.duty.form.button.create" action="/employer/duty/create?id=${id3}"/>
	
	</jstl:if>
	<jstl:if test="${command == 'show' }">
		<acme:form-submit code="employer.duty.form.button.delete" action="/employer/duty/delete"/>
	</jstl:if>
	<jstl:if test="${command == 'show' }">
		<acme:form-submit code="employer.duty.form.button.update" action="/employer/duty/update"/>
	</jstl:if>
	
	
		
  	<acme:form-return code="employer.job.form.button.return"/>
</acme:form>
