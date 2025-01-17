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
	<acme:form-textbox code="worker.duty.form.label.duty.title" path="title" />
	<acme:form-textarea code="worker.duty.form.label.duty.description" path="description"/>
	<acme:form-textbox code="worker.duty.form.label.duty.percentTime" path="percentTime" />
	
	
  	<acme:form-return code="worker.job.form.button.return"/>
</acme:form>
