<%@ page language="java" %>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:form>
	<jstl:if test="${command != 'create'}">
		<acme:form-textbox code="authenticated.userInThread.form.label.username" path="authenticated.userAccount.username" readonly="true" />
		<acme:form-textbox code="authenticated.userInThread.form.label.name" path="authenticated.identity.name" readonly="true" />
		<acme:form-textbox code="authenticated.userInThread.form.label.surname" path="authenticated.identity.surname" readonly="true" />
		<acme:form-hidden path="authenticated.id"/>
		<acme:form-hidden path="messageThread.id"/>
	</jstl:if>
	<jstl:if test="${command == 'create' }">
		<acme:form-select code="authenticated.userInThread.form.label.user" path="userId">
			<jstl:forEach var="i" begin="0" end="${listLength}">
				<acme:form-option code="${usernames[i]}" value="${userIds[i]}"/>
			</jstl:forEach>
		</acme:form-select>
		<acme:form-hidden path="threadId"/>
	</jstl:if>
	
	
	
	<acme:form-submit test="${command == 'create'}" 
		code="authenticated.userInThread.form.button.add"
		action="/authenticated/user-in-thread/create"/>
		
	<acme:form-submit test="${command == 'show'}" 
		code="authenticated.userInThread.form.button.delete"
		action="/authenticated/user-in-thread/delete"/>
		
	<acme:form-submit test="${command == 'delete'}" 
		code="authenticated.userInThread.form.button.delete"
		action="/authenticated/user-in-thread/delete"/>
		
	
	
	<acme:form-return code="authenticated.userInThread.form.button.return" />
</acme:form>
