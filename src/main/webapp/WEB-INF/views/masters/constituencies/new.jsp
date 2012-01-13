<%@ include file="/common/taglibs.jsp" %>
<html>
<body>
<form:form cssClass="wufoo" action="constituencies" method="POST" modelAttribute="constituency">
	<div class="info">
		<h2><spring:message code="constituency.new.heading" text="Enter Details"/></h2>
	<div style="background-color:#C1CDCD; ;padding: 3px"><spring:message code="generic.mandatory.label" text="Note: Fields marked * are mandatory"/></div>	</div>
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
		<label class="desc"><spring:message code="constituency.state" text="State"/>&nbsp;*</label>
			<div>
				<select name="state" id="states">
				<c:forEach items="${states}" var="i">
				<option value="${i.id}"><c:out value="${i.name}"></c:out></option>
				</c:forEach>
				</select>			
	        </div>
		</li>
		<li>
		<div>
		<label class="desc"><spring:message code="constituency.district" text="District"/>&nbsp;*</label>
				<form:select path="districts" items="${districts}" itemValue="id" itemLabel="name" size="5" multiple="multiple" id="districts">
	            </form:select><form:errors path="districts" cssClass="field_error" />			
	        </div>
		</li>		
		<li>
		<label class="desc"><spring:message code="constituency.name" text="Constituency"/>&nbsp;*</label>
			<div>
				<form:input cssClass="field text medium" path="name" size="50"/><form:errors path="name" cssClass="field_error" />
			</div>
		</li>
		<li>
		<label class="desc"><spring:message code="constituency.number" text="Constituency Number"/>&nbsp;</label>
			<div>
				<form:input cssClass="field text medium" path="number"/><form:errors path="number" cssClass="field_error" />
			</div>
		</li>	
		<li>	
		<label class="desc"><spring:message code="constituency.reserved" text="Reserved?"/>&nbsp;</label>
		<div>
				<form:checkbox cssClass="field text medium" path="reserved" value="true" /><form:errors path="reserved" cssClass="field_error" />
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
	<title><spring:message code="constituency.new.title" text="Add Constituency"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<script type="text/javascript">
	$(document).ready(function(){
		if($('#states').val()!=undefined){
			$('#states').change(function(){
				$.ajax({
					url:'ref/'+$('#states').val()+'/districts',
					datatype:'json',
					success:function(data){				
					$('#districts option').empty();
					var options="";
					for(var i=0;i<data.length;i++){
						options+="<option value='"+data[i].id+"'>"+data[i].name+"</option>";
					}
					$('#districts').html(options);
					$('#districts').sexyselect('destroy');
					$('#districts').sexyselect({width:250,showTitle: false, selectionMode: 'multiple', styleize: true});
				}							
				});
		});	
		} 
			});
   
	</script>
</head>
</html>