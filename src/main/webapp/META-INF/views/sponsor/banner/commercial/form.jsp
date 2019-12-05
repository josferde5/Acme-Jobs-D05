<%@ page language="java" %>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:form>
	<acme:form-textbox code="sponsor.banner.commercial.form.label.picture" path="picture" />
	<acme:form-textbox code="sponsor.banner.commercial.form.label.slogan" path="slogan" />
	<acme:form-textbox code="sponsor.banner.commercial.form.label.target" path="target" />
	<acme:form-textbox code="sponsor.banner.commercial.form.label.creditCard" path="creditCard" />
	
	<jstl:if test="${command != 'create'}">
		<a href="${requestScope['target']}">
			<img src="<acme:print value="${requestScope['picture']}"/>" align="middle" style="max-width:500px; height:auto"/>
		</a>
		<br><br>
	</jstl:if>
	

	<acme:form-submit test="${command == 'show'}"
		code="sponsor.banner.commercial.form.button.update" 
		action="/sponsor/banner/commercial/update/"/>
	<acme:form-submit test="${command == 'show'}"
		code="sponsor.banner.commercial.form.button.delete" 
		action="/sponsor/banner/commercial/delete/"/>
	<acme:form-submit test="${command == 'create'}"
		code="sponsor.banner.commercial.form.button.create" 
		action="/sponsor/banner/commercial/create/"/>
	<acme:form-submit test="${command == 'update'}"
		code="sponsor.banner.commercial.form.button.update" 
		action="/sponsor/banner/commercial/update/"/>
	<acme:form-submit test="${command == 'delete'}"
		code="sponsor.banner.commercial.form.button.delete" 
		action="/sponsor/banner/commercial/delete/"/>
	<acme:form-return code="sponsor.banner.commercial.form.button.return" />
</acme:form>