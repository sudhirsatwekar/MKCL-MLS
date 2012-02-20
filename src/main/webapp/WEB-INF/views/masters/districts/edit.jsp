<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
	<title><spring:message code="district.edit.title" text="Edit District"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>

<div class="fields clearfix">
<form:form action="districts" method="PUT"  modelAttribute="district">
	<%@ include file="/common/info.jsp" %>
	<h2><spring:message code="district.edit.heading" text="Enter Details "/>
		[Id:${district.id}]
	</h2>
	<p class="section first">
			<c:if test="${isvalid eq false}">
				<p class="field_error"><spring:message code="generic.error.label"/></p>
			</c:if>
		<form:errors path="version" cssClass="field_error" />		
	</p>
	<p>
	<label class="small"><spring:message code="generic.id" text="Id"/></label>
		<form:input cssClass="field text small" path="id" readonly="true" /> 
	</p>
	<p>
	<label class="small"><spring:message code="district.state" text="State"/>&nbsp;*</label>
		<form:select path="state" items="${states}" itemValue="id" itemLabel="name">
	    </form:select>			
	</p>
	<p>
	<label class="small"><spring:message code="district.name" text="District"/>&nbsp;*</label>
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