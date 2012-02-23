<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
	<title><spring:message code="titles.edit.title" text="Edit Title"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>

<div class="fields clearfix">
<form:form action="titles" method="PUT"  modelAttribute="title">
	<%@ include file="/common/info.jsp" %>
	<h2><spring:message code="title.edit.heading" text="Enter Details "/>
		[Id:${title.id}]
	</h2>
	<p>
	<label class="small"><spring:message code="generic.id" text="Id"/></label>
		<form:input cssClass="field text small" path="id" readonly="true" /> 
	</p>
	<p>
	<label class="small"><spring:message code="title.name" text="Name"/>&nbsp;*</label>
		<form:input cssClass="field text medium" path="name" size="50"/><form:errors path="name" cssClass="field_error" />	
		
	</p>
	<div class="fields">
		<p class="tright">
			<input id="submit" type="submit" value="<spring:message code='generic.submit' text='Submit'/>" class="butDef">
		</p>
	</div>
	<form:hidden path="id"/>
	<form:hidden path="locale" />	
	<form:hidden path="version"/>
</form:form>
</div>
</body>
</html>