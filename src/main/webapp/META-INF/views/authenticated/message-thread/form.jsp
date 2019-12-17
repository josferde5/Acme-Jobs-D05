<%@ page language="java" %>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<jstl:if test="${command != 'create'}">
<%
	String threadId = String.format("%d", request.getAttribute("id"));
	request.setAttribute("threadId", threadId);
%>
</jstl:if>

<acme:form>
	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<acme:form-textbox code="authenticated.messageThread.form.label.title" path="title" />
		</jstl:when>
		<jstl:otherwise>
			<acme:form-textbox code="authenticated.messageThread.form.label.title" path="title" readonly="true"/>
		</jstl:otherwise>
	</jstl:choose>
	<jstl:if test="${command != 'create'}">
		<acme:form-moment code="authenticated.messageThread.form.label.moment" path="moment" readonly="true"/>
	</jstl:if>	
	
	
	<acme:form-submit test="${command == 'create'}" 
		code="authenticated.messageThread.form.button.create"
		action="/authenticated/message-thread/create"/>
	
	<acme:form-return code="authenticated.messageThread.form.button.return" />
</acme:form>

<br />

<jstl:if test="${command != 'create'}">
	<acme:form>
		<acme:form-submit method="get" code="authenticated.messageThread.form.button.listMessages" action='/authenticated/message/list_thread?id=${threadId}' />
		<acme:form-submit method="get" code="authenticated.messageThread.form.button.listUsers" action="/authenticated/user-in-thread/list?threadId=${threadId}" />
		<acme:form-submit method="get" code="authenticated.messageThread.form.button.addUser" action="/authenticated/user-in-thread/create?threadId=${threadId}" />
		<acme:form-submit method="get" code="authenticated.messageThread.form.button.postMessage" action="/authenticated/message/create?threadId=${threadId}" />
	</acme:form>
</jstl:if>
