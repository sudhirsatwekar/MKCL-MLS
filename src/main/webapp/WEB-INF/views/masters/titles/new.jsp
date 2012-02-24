<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
	<title><spring:message code="titles.new.title" text="Add Title"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>

<div class="fields clearfix">
<form:form action="titles" method="POST"  modelAttribute="title">
	<%@ include file="/common/info.jsp" %>
	<h2><spring:message code="title.new.heading" text="Enter Details"/>
		[Id:&nbsp;New]
	</h2>
	<c:set var="nameErrors"><form:errors path="name"/></c:set>
	<p <c:if test="${not empty nameErrors}">class="error"</c:if>>
	<label class="small"><spring:message code="title.name" text="Name"/>&nbsp;*</label>
			<form:input cssClass="field text medium " path="name"/><form:errors path="name" cssClass="field_error" />	
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