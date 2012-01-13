<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
	<title><spring:message code="field.title" text="Edit Field"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>
<form:form cssClass="wufoo" action="fields" method="PUT" modelAttribute="field">
	<div class="info">
		<h2><spring:message code="field.edit.heading" text="Details"/>[Id:${field.id}]</h2>
		<div style="background-color:#C1CDCD; ;padding: 3px"><spring:message code="generic.mandatory.label" text="Fields marked * are mandatory"/></div>
	</div>
	<ul>
		<li>
		<label class="desc"><spring:message code="generic.id" text="Id"></spring:message></label>
			<div>
				<form:input cssClass="field text medium" path="id" readonly="true" /> 
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
		<label class="desc"><spring:message code="field.name" text="Name"/>&nbsp;*</label>
			<div>
		   <form:input path="name" cssClass="field text medium"></form:input><form:errors path="name" cssClass="field_error" />			
		    </div>
		</li>	
		
		<li>
		<label class="desc"><spring:message code="field.detail" text="Brief Description"/>&nbsp;*</label>
			<div>
		   <form:input path="detail" cssClass="field text medium"></form:input><form:errors path="detail" cssClass="field_error" />			
		    </div>
		</li>	
			
		<li>
		<label class="desc"><spring:message code="field.mandatory" text="Is Mandatory?"/>&nbsp;*</label>
			<div>
				<form:checkbox cssClass="checkbox" path="mandatory" value="MANDATORY"/><form:errors path="mandatory" cssClass="field_error" />	
			</div>
		</li>
		
		<li>
		<label class="desc"><spring:message code="field.visible" text="Is Visible?"/>&nbsp;*</label>
			<div>
				<form:checkbox cssClass="checkbox" path="visible" value="VISIBLE"/><form:errors path="visible" cssClass="field_error" />	
			</div>
		</li>	
		
		<li>
		<label class="desc"><spring:message code="field.position" text="Position on page"/>&nbsp;*</label>
			<div>
				<form:input cssClass="integer field text medium" path="position" /><form:errors path="position" cssClass="field_error" />	
			</div>
		</li>
		
		<li>
		<label class="desc"><spring:message code="field.hint" text="Hint"/>&nbsp;*</label>
			<div>
				<form:input cssClass="field text medium" path="hint" /><form:errors path="hint" cssClass="field_error" />	
			</div>
		</li>
		
		<li>
		<label class="desc"><spring:message code="field.form" text="Form Name"/>&nbsp;*</label>
			<div>
		   <form:select path="form" cssClass="field select medium">
		   <form:option value="MIS.PERSONAL"></form:option>
		   <form:option value="MIS.OTHER"></form:option>
		   <form:option value="MIS.CONTACT"></form:option>		   
		   </form:select><form:errors path="form" cssClass="field_error" />				
		    </div>
		</li>	
				
				
		<li class="buttons">
			<input id="saveForm" class="btTxt" type="submit" value="<spring:message code='generic.edit.submit' text='Update'/>" />
		</li>
		<form:hidden path="version"/>
	</ul>		
</form:form>
</body>
</html>