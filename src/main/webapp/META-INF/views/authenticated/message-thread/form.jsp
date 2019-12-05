<%@ page language="java" %>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<%
	String threadId = String.format("%d", request.getAttribute("id"));
	request.setAttribute("threadId", threadId);
%>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.messageThread.form.label.title" path="title" />
	<acme:form-moment code="authenticated.messageThread.form.label.moment" path="moment" />
	
	<acme:form-return code="authenticated.messageThread.form.button.return" />
</acme:form>

<br />

<acme:form>
	<acme:form-submit method="get" code="authenticated.messageThread.form.button.listMessages" action='/authenticated/message/list_thread?id=${threadId}'/>
</acme:form>
