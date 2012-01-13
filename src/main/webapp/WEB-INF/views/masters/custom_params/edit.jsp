<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
	<title><spring:message code="customparams.edit.title" text="Edit Custom Parameter"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>
<form:form cssClass="wufoo" action="custom_params" method="PUT" 
	modelAttribute="customParameter">
	<div class="info">
		<h2><spring:message code="customparams.edit.heading" text="Details"/> [Id:${customParameter.id}]</h2>
		<div style="background-color:#C1CDCD; ;padding: 3px"><spring:message code="generic.mandatory.label" text="Note: Fields marked * are mandatory"/></div>

	</div>
	<ul>
		<form:errors path="version" cssClass="field_error"/>
	<li>
	<label class="desc"><spring:message code="generic.id" text="Id"/></label>
		<div>
			<form:input cssClass="field text small" path="id" readonly="true" /> 
		</div>
	</li>
	
	<li>
	<label class="desc"><spring:message code="customparams.name" text="Name"/>&nbsp;*</label>
		<div>
			<form:input cssClass="field text medium" path="name"/>
			<form:errors path="name" cssClass="field_error" />	
	</div>
	</li>
	
	<li>
	<label class="desc"><spring:message code="customparams.value" text="Value"/>&nbsp;*</label>
		<div>
			<form:input cssClass="field text medium" path="value"/>
			<form:errors path="value" cssClass="field_error" />	
		</div>
	</li>
	
	<li>
	<label class="desc"><spring:message code="customparams.updateable" text="Updateable?"/></label>
		<div>
			<form:checkbox path="updateable" id="updateable"/>
			<form:errors path="updateable" cssClass="field_error" />
		</div>
	</li>
	
	<li>
	<label class="desc"><spring:message code="customparams.description" text="Description"/></label>
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