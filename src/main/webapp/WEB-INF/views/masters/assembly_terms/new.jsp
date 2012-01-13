<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
	<title><spring:message code="assembly_term.new.title" text="Add Assembly Term"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>
<form:form cssClass="wufoo" action="assembly_terms" method="POST" 
	modelAttribute="assemblyTerm">
	<div class="info">
		<h2><spring:message code="assembly_term.new.heading" text="Enter Details"/></h2>
		<div style="background-color:#C1CDCD; ;padding: 3px"><spring:message code="generic.mandatory.label" text="Note: Fields marked * are mandatory"/></div>
	</div>
	<ul>
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
	<label class="desc"><spring:message code="assembly_term.term" text="Term"/>&nbsp;*</label>
		<div>
			<form:input cssClass="integer field text medium " path="term"/>
			<form:errors path="term" cssClass="field_error" />	
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