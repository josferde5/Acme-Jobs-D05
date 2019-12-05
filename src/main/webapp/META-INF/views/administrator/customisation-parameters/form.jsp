<%@ page language="java"%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textarea code="administrator.customParameters.form.label.spamWordsEn" path="spamWordsEn" />
	<acme:form-textarea code="administrator.customParameters.form.label.spamWordsSp" path="spamWordsSp" />
	<acme:form-double code="administrator.customParameters.form.label.threshold" path="threshold" />
	<acme:form-submit test="${command == 'update'}" code="administrator.customParameters.form.button.update"
		action="/administrator/customisation-parameters/update" />
	<acme:form-submit test="${command == 'display'}" code="administrator.customParameters.form.button.update"
		action="/administrator/customisation-parameters/update" />
	<acme:form-return code="administrator.customParameters.form.button.return" />
</acme:form>
