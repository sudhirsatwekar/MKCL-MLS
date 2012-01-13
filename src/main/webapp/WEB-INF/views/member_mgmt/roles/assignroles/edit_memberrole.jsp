<%@ include file="/common/taglibs.jsp" %>
<html>
<body>
<form:form cssClass="wufoo" action="member_role/assignroles/updateMemberRole" method="POST" 
	modelAttribute="memberRole">
	<div class="info">
			<h2><spring:message code="mms.assignroles.edit.heading" text="Details"/></h2>
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
		<label class="desc"><spring:message code="mms.assignroles.member" text="Member Name"/>&nbsp;*</label>
			<div>
				<input type="text" value="${memberRole.member.firstName} ${memberRole.member.middleName} ${memberRole.member.lastName}" readonly="readonly" class="field text medium" >
				<input value="${memberRole.member.id}" id="member" name="member" type="hidden">				
			</div>
	</li>	
	
	<li>
	<label class="desc"><spring:message code="mms.assignroles.assembly" text="Assembly"/>&nbsp;*</label>
		<div>
			<input class="field text medium" value="${memberRole.assembly.assembly}" readonly="readonly" id="assemblyName" name="assemblyName">
			<input value="${memberRole.assembly.id}" id="assembly" name="assembly" type="hidden">			
		</div>
	</li>
	<li>
	<label class="desc"><spring:message code="mms.assignroles.roles" text="Role"/>&nbsp;*</label>
		<div>
			<input class="field text medium" value="${memberRole.role.name}" readonly="readonly" id="roleName" name="roleName">
			<input value="${memberRole.role.id}" id="role" name="role" type="hidden">			
			
		</div>
	</li>
	<li>
	<label class="desc"><spring:message code="mms.assignroles.status" text="Current Status"/>&nbsp;*</label>
		<div>
		<form:select path="status" items="${allStatus}" cssClass="field select medium"></form:select>			
		</div>
	</li>
	<li>	
		<label class="desc"><spring:message code="mms.assignroles.fromdate" text="From"/>&nbsp;*</label>
			<div>
				<form:input cssClass="date field text medium" path="fromDate"/><form:errors path="fromDate" cssClass="field_error" />	
			</div>
		</li>	
		
	<li>
		<label class="desc"><spring:message code="mms.assignroles.todate" text="To"/></label>
			<div>
				<form:input cssClass="date field text medium" path="toDate"/><form:errors path="toDate" cssClass="field_error" />	
			</div>
	</li>	
	<li>
		<label class="desc"><spring:message code="mms.assignroles.remarks" text="Remarks"/></label>
			<div>
				<form:textarea cssClass="field textarea small" path="remarks"/><form:errors path="remarks" cssClass="field_error" />	
			</div>
		</li>
	<li class="buttons">
		<input type="hidden" name="assignmentDate" value="${assignmentDate}" id="assignmentDate" >
		<input id="saveForm" class="btTxt" type="submit" 
			value="<spring:message code='generic.submit' text='Submit'/>" />
	</li>
    </ul>
	<form:hidden path="version"/>
</form:form>
</body>
<head>
	<title><spring:message code="mms.assignroles.new.title" text="Edit Member Role"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<script type="text/javascript">
	$(document).ready(function(){	
		$('#status').change(function(){
			if($('#status').val()=="Not Applicable"){
				$('#toDate').val($('#assignmentDate').val());
			}
		});		
	});
	</script>
</head>
</html>