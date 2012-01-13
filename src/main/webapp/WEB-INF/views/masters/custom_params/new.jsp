<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
	<title><spring:message code="customparams.new.title" text="Add Custom Parameter"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>
<form:form cssClass="wufoo" action="custom_params" method="POST" 
	modelAttribute="customParameter">
	<div class="info">
		<h2><spring:message code="customparams.new.heading" text="Enter Details"/></h2>
		<div style="background-color:#C1CDCD; ;padding: 3px"><spring:message code="generic.mandatory.label" text="Note: Fields marked * are mandatory"/></div>

	</div>
	<ul>
		
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

	<form:hidden path="id"/>
		
	<form:hidden path="version"/>
	</ul>		
</form:form>
</body>
</html>