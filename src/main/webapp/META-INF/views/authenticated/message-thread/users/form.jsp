<%@ page language="java" %>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.messageThread.users.form.label.username" path="userAccount.username" />
	<acme:form-textbox code="authenticated.messageThread.users.form.label.name" path="identity.name" />
	<acme:form-textbox code="authenticated.messageThread.users.form.label.surname" path="identity.surname" />
	<acme:form-hidden path="threadId"/>	
	
	
	<acme:form-submit test="${add == 'true'}" 
		code="authenticated.messageThread.users.form.button.add"
		action="/authenticated/authenticated/add_user_thread"/>
		
	<acme:form-submit test="${add == 'false'}" 
		code="authenticated.messageThread.users.form.button.delete"
		action="/authenticated/authenticated/delete_user_thread"/>
		
	
	
	<acme:form-return code="authenticated.messageThread.users.form.button.return" />
</acme:form>
