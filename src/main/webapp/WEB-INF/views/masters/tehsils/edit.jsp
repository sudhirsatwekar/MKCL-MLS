<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
	<title><spring:message code="tehsil.edit.title" text="Edit Tehsil"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title><spring:message code="tehsil.edit.title" text="Edit Tehsil"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<script type="text/javascript">
	if($('#state').val()!=undefined){
		$('#state').change(function(){
			$.ajax({
				url:'ref/'+$('#state').val()+'/districts',
				datatype:'json',
				success:function(data){
				$('#districts option').remove();
				for(var i=0;i<data.length;i++){
					$('#districts').append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
				}
			}							
			});
	});	
	}    
	</script>
</head>
<body>

<div class="fields clearfix">
<form:form action="tehsils" method="PUT"  modelAttribute="tehsil">
	<%@ include file="/common/info.jsp" %>
	<h2><spring:message code="tehsil.edit.heading" text="Enter Details "/>
		[Id:${tehsil.id}]
	</h2>
	<p>
	<label class="small"><spring:message code="generic.id" text="Id"/></label>
		<form:input cssClass="field text small" path="id" readonly="true" /> 
	</p>
	<p>
	<label class="small"><spring:message code="tehsil.state" text="State"/>&nbsp;*</label>
		<form:select path="state" items="${states}" itemValue="id" itemLabel="name">
		</form:select>	
	</p>
	<p>
	<label class="small"><spring:message code="tehsil.district" text="District"/>&nbsp;*</label>
			<form:select path="district" items="${districts}" itemValue="id" itemLabel="name" id="districts">
	        </form:select>			
	</p>
	<c:set var="nameErrors"><form:errors path="name"/></c:set>
	<p <c:if test="${not empty nameErrors}">class="error"</c:if>>
	<label class="small"><spring:message code="tehsil.name" text="Name"/>&nbsp;*</label>
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