<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
	<title><spring:message code="state.edit.title" text="Edit State"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>
<div class="fields clearfix">
<form:form action="states" method="PUT"  modelAttribute="state">
	<%@ include file="/common/info.jsp" %>
	<h2><spring:message code="state.edit.heading" text="Enter Details "/>
		[Id:${state.id}]
	</h2>
	<p>
	<label class="small"><spring:message code="generic.id" text="Id"/></label>
		<form:input cssClass="field text small" path="id" readonly="true" /> 
	</p>
	<c:set var="nameErrors"><form:errors path="name"/></c:set>
	<p <c:if test="${not empty nameErrors}">class="error"</c:if>>
	<label class="small"><spring:message code="state.name" text="Name"/>&nbsp;*</label>
		<form:input cssClass="field text medium" path="name" size="50"/><form:errors path="name" cssClass="field_error" />	
	</p>
	<div class="fields">
		<p class="tright">
			<input id="submit" type="submit" value="<spring:message code='generic.submit' text='Submit'/>" class="butDef">
		</p>
	</div>
	<form:hidden path="locale" />
	<form:hidden path="id"/>	
	<form:hidden path="version"/>
</form:form>
</div>
</body>
</html>