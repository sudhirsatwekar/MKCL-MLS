<%@ include file="/common/taglibs.jsp" %>
<html>
<body>
<form:form cssClass="wufoo" action="tehsils" method="POST" modelAttribute="tehsil">
	<div class="info">
		<h2><spring:message code="tehsil.new.heading" text="Enter Details"/></h2>
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
		<label class="desc"><spring:message code="tehsil.state" text="State"/>&nbsp;*</label>
			<div>
				<select name="state" id="states">
				<c:forEach items="${states}" var="i">
				<option value="${i.id}"><c:out value="${i.name}"></c:out></option>
				</c:forEach>
				</select>
		   </div>
		</li>
		<li>
		<label class="desc"><spring:message code="tehsil.district" text="District"/>&nbsp;*</label>
			<div>
				<form:select path="district" items="${districts}" itemValue="id" itemLabel="name" id="districts">
	            </form:select>			
	        </div>
		</li>		
		<li>
		<label class="desc"><spring:message code="tehsil.name" text="Name"/>&nbsp;*</label>
			<div>
				<form:input cssClass="field text medium" path="name" size="50"/><form:errors path="name" cssClass="field_error" />	
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
<head>
	<title><spring:message code="tehsil.new.title" text="Add Tehsil"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<script type="text/javascript">
	if($('#states').val()!=undefined){
		$('#states').change(function(){
			$.ajax({
				url:'ref/'+$('#states').val()+'/districts',
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
</html>