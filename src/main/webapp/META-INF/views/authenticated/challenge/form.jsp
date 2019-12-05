<%@ page language="java" %>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.challenge.form.label.title" path="title" />
	<acme:form-moment code="authenticated.challenge.form.label.deadline" path="deadline" />
	<acme:form-textarea code="authenticated.challenge.form.label.description" path="description" />
	<acme:form-textbox code="authenticated.challenge.form.label.goal.bronze" path="goalBronze" />
	<acme:form-money code="authenticated.challenge.form.label.reward.bronze" path="rewardBronze" />
	<acme:form-textbox code="authenticated.challenge.form.label.goal.silver" path="goalSilver" />
	<acme:form-money code="authenticated.challenge.form.label.reward.silver" path="rewardSilver" />
	<acme:form-textbox code="authenticated.challenge.form.label.goal.gold" path="goalGold" />
	<acme:form-money code="authenticated.challenge.form.label.reward.gold" path="rewardGold" />
	
	<acme:form-return code="authenticated.challenge.form.button.return" />
</acme:form>
