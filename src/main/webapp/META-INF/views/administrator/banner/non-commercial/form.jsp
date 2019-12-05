<%@ page language="java" %>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:form>
	<acme:form-textbox code="administrator.banner.nonCommercial.form.label.picture" path="picture" />
	<acme:form-textbox code="administrator.banner.nonCommercial.form.label.slogan" path="slogan" />
	<acme:form-textbox code="administrator.banner.nonCommercial.form.label.target" path="target" />
	<acme:form-textbox code="administrator.banner.nonCommercial.form.label.jingle" path="jingle" />
	
	<jstl:if test="${command != 'create'}">
		<a href="${requestScope['target']}">
			<img src="<acme:print value="${requestScope['picture']}"/>" align="middle" style="max-width:500px; height:auto"/>
		</a>
		<br><br>
	</jstl:if>

	<acme:form-submit test="${command == 'show'}"
		code="administrator.banner.nonCommercial.form.button.update" 
		action="/administrator/banner/non-commercial/update/"/>
	<acme:form-submit test="${command == 'show'}"
		code="administrator.banner.nonCommercial.form.button.delete" 
		action="/administrator/banner/non-commercial/delete/"/>
	<acme:form-submit test="${command == 'create'}"
		code="administrator.banner.nonCommercial.form.button.create" 
		action="/administrator/banner/non-commercial/create/"/>
	<acme:form-submit test="${command == 'update'}"
		code="administrator.banner.nonCommercial.form.button.update" 
		action="/administrator/banner/non-commercial/update/"/>
	<acme:form-submit test="${command == 'delete'}"
		code="administrator.banner.nonCommercial.form.button.delete" 
		action="/administrator/banner/non-commercial/delete/"/>
	<acme:form-return code="administrator.banner.nonCommercial.form.button.return" />
</acme:form>
