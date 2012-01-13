<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
	<title><spring:message code="messageResource.edit.title" text="Edit Message Resource"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>
<form:form cssClass="wufoo" action="messages" method="PUT" 
	modelAttribute="messageResource">
	<div class="info">
		<h2><spring:message code="messageResource.edit.heading" text="Details"/> [Id:${messageResource.id}]</h2>
		<div style="background-color:#C1CDCD; ;padding: 3px"><spring:message code="generic.mandatory.label" text="Note: Fields marked * are mandatory"/></div>
	</div>
	<ul>			
	<li>
	<label class="desc"><spring:message code="generic.id" text="Id"/></label>
		<div>
			<form:input cssClass="field text small" path="id" readonly="true" /> 
		</div>
	</li>
	
<li>
		<label class="desc"><spring:message code="generic.locale" text="Select language"/>&nbsp;*</label>
			<div>
				<form:select cssClass="field select medium" path="locale"> 
				<form:option value="en"><spring:message code="generic.lang.english" text="English"/></form:option>
					<form:option value="hi_IN"><spring:message code="generic.lang.hindi" text="Hindi"/></form:option>
					<form:option value="mr_IN"><spring:message code="generic.lang.marathi" text="Marathi"/></form:option>
				</form:select>
			</div>
		</li>

	
	<li>
	<label class="desc"><spring:message code="messageResource.code" text="Code"/>&nbsp;*</label>
		<div>
			<form:input cssClass="field text medium" path="code"/>
			<form:errors path="code" cssClass="field_error" />	
		</div>
	</li>
	
	<li>
	<label class="desc"><spring:message code="messageResource.text" text="Text"/>&nbsp;*</label>
		<div>
			<form:input cssClass="field text medium" path="value"/>
			<form:errors path="value" cssClass="field_error" />
		</div>
	</li>
	
	<li>
	<label class="desc"><spring:message code="messageResource.description" text="Description"/></label>
		<div>
			<form:textarea cssClass="field textarea small" path="description" cols="28" rows="3"/>
		</div>
	</li>
	
	<li class="buttons">
			<input id="saveForm" class="btTxt" type="submit" value="<spring:message code='generic.submit' text='Submit'/>" />
	</li>				
	<form:hidden path="version"/>
	</ul>
</form:form>
</body>
</html>