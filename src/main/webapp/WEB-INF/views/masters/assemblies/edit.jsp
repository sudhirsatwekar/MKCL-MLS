<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
	<title><spring:message code="assembly.edit.title" text="Edit Assembly"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	</head>
<body>
<form:form cssClass="wufoo" action="assemblies" method="PUT" modelAttribute="assembly">
	<div class="info">
		<h2><spring:message code="assembly.edit.heading" text="Details"/>[Id:${assembly.id}]</h2>
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
		<label class="desc"><spring:message code="assembly.assemblystructure" text="Structure"/>&nbsp;*</label>
			<div>
				<form:select path="assemblyStructure" items="${assemblyStructures}" itemValue="id" itemLabel="name" >
	            </form:select>
		   </div>
		</li>
		<li>
		<label class="desc"><spring:message code="assembly.assembly" text="Assembly"/>&nbsp;*</label>
			<div>
				<form:input path="assembly" cssClass="field text medium"></form:input>
	           <form:errors path="assembly" cssClass="field_error"></form:errors>			
	        </div>
		</li>
		<li>
		<label class="desc"><spring:message code="assembly.assemblyNumber" text="Assembly Number"/>&nbsp;*</label>
			<div>
				<form:input path="assemblyNumber" cssClass="field text medium"></form:input>
	           <form:errors path="assemblyNumber" cssClass="field_error"></form:errors>			
	        </div>
		</li>			
		<li>
		<label class="desc"><spring:message code="assembly.strength" text="Strength"/>&nbsp;*</label>
			<div>
				<form:input cssClass="integer field text medium" path="strength"/><form:errors path="strength" cssClass="field_error" />	
			</div>
		</li>	
		<li>
		<label class="desc"><spring:message code="assembly.term" text="Term"/>&nbsp;*</label>
			<div>
				<form:input cssClass="field text medium" path="term"/><form:errors path="term" cssClass="field_error" />	
			</div>
		</li>	
		<li>	
		<label class="desc"><spring:message code="assembly.budgetSession" text="Budget Session?"/>&nbsp;*</label>
		<div>
				<form:checkbox cssClass="checkbox" path="budgetSession" value="true" /><form:errors path="budgetSession" cssClass="field_error" />
		</div>	
		</li>	
		<li>	
		<label class="desc"><spring:message code="assembly.monsoonSession" text="Monsoon Session?"/>&nbsp;*</label>
		<div>
				<form:checkbox cssClass="checkbox" path="monsoonSession" value="true" /><form:errors path="monsoonSession" cssClass="field_error" />
		</div>	
		</li>	
		<li>	
		<label class="desc"><spring:message code="assembly.winterSession" text="Winter Session?"/>&nbsp;*</label>
		<div>
				<form:checkbox cssClass="checkbox" path="winterSession" value="true" /><form:errors path="winterSession" cssClass="field_error" />
		</div>	
		</li>
		
		<li>	
		<label class="desc"><spring:message code="assembly.current" text="Is Current Assembly?"/>&nbsp;*</label>
		<div>
				<form:checkbox cssClass="checkbox" path="currentAssembly" value="true" /><form:errors path="currentAssembly" cssClass="field_error" />
		</div>	
		</li>	
		
		<li>
		<label class="desc"><spring:message code="assembly.startDate" text="Start Date"/>&nbsp;*</label>
			<div>
				<form:input cssClass="date field text medium" path="assemblyStartDate"/><form:errors path="assemblyStartDate" cssClass="field_error" />	
			</div>
		</li>	
		
		<li>
		<label class="desc"><spring:message code="assembly.endDate" text="End Date"/></label>
			<div>
				<form:input cssClass="date field text medium" path="assemblyEndDate"/><form:errors path="assemblyEndDate" cssClass="field_error" />	
			</div>
		</li>	
		
		<li>
		<label class="desc"><spring:message code="assembly.dissolvedOn" text="Dissolved On"/></label>
			<div>
				<form:input cssClass="date field text medium" path="assemblyDissolvedOn"/><form:errors path="assemblyDissolvedOn" cssClass="field_error" />	
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
