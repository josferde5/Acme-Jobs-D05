<%@ page language="java" %>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:form>
	<acme:form-textbox code="consumer.offer.form.label.title" path="title" />
	<jstl:if test="${command != 'create'}">
	<acme:form-moment code="consumer.offer.form.label.creationMoment" path="creationMoment" />
	</jstl:if>
	<acme:form-moment code="consumer.offer.form.label.deadline" path="deadline" />
	<acme:form-textarea code="consumer.offer.form.label.text" path="text" />
	<jstl:if test="${command == 'create'}">
	<acme:form-money code="consumer.offer.form.label.minMoney" path="minMoney"/>
	<acme:form-money code="consumer.offer.form.label.maxMoney" path="maxMoney"/>
	</jstl:if>
	<jstl:if test="${command != 'create'}">
	<acme:form-textbox code="consumer.offer.form.label.moneyRange" path="moneyRange" />
	</jstl:if>
	
	<acme:form-textbox code="consumer.offer.form.label.ticker" path="ticker" />
	<jstl:if test="${command == 'create'}">
	<acme:form-checkbox code="consumer.offer.label.confirm" path="confirm"/>
   	<acme:form-submit code="consumer.offer.form.button.create" action="/consumer/offer/create"/>
   	</jstl:if>
	<acme:form-return code="consumer.offer.form.button.return" />
</acme:form>