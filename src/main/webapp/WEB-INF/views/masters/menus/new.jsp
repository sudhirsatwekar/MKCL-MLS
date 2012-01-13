<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>
<form:form cssClass="wufoo" action="menus" method="POST" modelAttribute="menuItem">
	<div class="info">
		<h2><spring:message code="masters.menus.new.heading" text="New Menu"/></h2>
		<div style="background-color:#C1CDCD; ;padding: 3px"><spring:message code="generic.mandatory.label" text="All fields marked * are mandatory"/></div>
	</div>
	<ul>
		<li class="section first">
			<c:if test="${isvalid eq false}">
				<p class="field_error"><spring:message code="generic.error.label"/></p>
			</c:if>		
		</li>	
		<li>
		<label class="desc"><spring:message code="generic.locale" text="Locale"/>&nbsp;*</label>
			<div>
				<form:select cssClass="field select addr" path="locale"> 
					<form:option value="en"><spring:message code="generic.lang.english" text="English"/></form:option>
					<form:option value="hi_IN"><spring:message code="generic.lang.hindi" text="Hindi"/></form:option>
					<form:option value="mr_IN"><spring:message code="generic.lang.marathi" text="Marathi"/></form:option>
				</form:select>
			</div>
		</li>		
		<li>
		<label class="parent"><spring:message code="masters.menus.parent" text="Parent Menu"/></label>
			<div>
				<form:input cssClass="field text medium" path="parent.text" readonly="true" /><form:errors path="parent.text" cssClass="field_error" />
				<form:hidden path="parent.id"/>	
				<form:hidden path="parent.version"/>
			</div>
		</li>		
		<li>
		<label class="desc"><spring:message code="masters.menus.textkey" text="Text Key"/>&nbsp;*</label>
			<div>
				<form:input cssClass="field text medium" path="textKey"/><form:errors path="textKey" cssClass="field_error" />	
			</div>
		</li>
		<li>
		<label class="desc"><spring:message code="masters.menus.text" text="Text"/>&nbsp;*</label>
			<div>
				<form:input cssClass="field text medium" path="text"/><form:errors path="text" cssClass="field_error" />
			</div>
		</li>
		<li>
			<label class="position"><spring:message code="masters.menus.position" text="Position"/>&nbsp;*</label>
			<div>
				<form:input cssClass="integer field text medium" path="position"/><form:errors path="position" cssClass="field_error" />	
			</div>
		</li>
		<li>
			<label class="desc"><spring:message code="masters.menus.url" text="Url"/>&nbsp;*</label>
			<div>
				<form:input cssClass="field text medium" path="url"/><form:errors path="url" cssClass="field_error" />	
			</div>
		</li>
		
		<li class="buttons">
			<input id="saveForm" class="btTxt" type="submit" value="Submit" />
		</li>
		<form:hidden path="version"/>
		<form:hidden path="id"/>
	</ul>		
</form:form>
</body>
</html>