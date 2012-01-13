<%@ include file="/common/taglibs.jsp" %>
<html>
<body>	
<form:form cssClass="wufoo" action="member_role/assignmembers/createMemberRoles" method="POST" 
	modelAttribute="memberRole">
	<div class="info">
			<h2><spring:message code="mms.assignmembers.new.heading" text="Enter Details"/></h2>
			<div style="background-color:#C1CDCD; ;padding: 3px"><spring:message code="generic.mandatory.label" text="Note: Fields marked * are mandatory"/></div>
	</div>
	<ul>
	<li class="section first">
			<c:if test="${isvalid eq false}">
				<p class="field_error"><spring:message code="generic.error.label"/></p>
			</c:if>
			<form:errors path="assembly" cssClass="field_error" />
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
	<label class="desc"><spring:message code="mms.assignroles.assembly" text="Assembly"/>&nbsp;*</label>
		<div>
				<form:select path="assembly" items="${assemblies}" itemValue="id" itemLabel="assembly" id="assemblies" cssClass="field select medium">
	            </form:select>
	            	
		</div>
	</li>
	<li>
	<label class="desc"><spring:message code="mms.assignroles.roles" text="Role"/>&nbsp;*</label>
		<div>
				<input id="role" name="role" type="hidden" value="${memberRole.role.id}">
				<input id="roleName" name="roleName" value="${memberRole.role.name}" type="text" class="field text medium" readonly="readonly">
				<form:errors path="role" cssClass="field_error" />		                             
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
	</ul>
	<ul>
	<li>
	<label class="desc"><spring:message code="mms.assignroles.membergrid" text="Members"/></label>
	</li>
	</ul>	
	<div id="grid_container">
		<table id="memberGrid"></table> 
		<div id="membergrid_pager"></div>
	</div>
	<form:errors path="member" cssClass="field_error" />	
	<ul>
	<li>
		<label class="desc"><spring:message code="mms.assignroles.remarks" text="Remarks"/></label>
			<div>
				<form:textarea cssClass="field textarea small" path="remarks"/><form:errors path="remarks" cssClass="field_error" />	
			</div>
	</li>	
	<li class="buttons">
		<input type="hidden" name="membersToAssign" id="membersToAssign" value="${membersToAssign}">
		<input type="hidden" name="assignmentDate" value="${assignmentDate}" id="assignmentDate">	
		<input id="saveForm" class="btTxt" type="button" value="<spring:message code='generic.submit' text='Submit'/>" />
	</li>
	<form:hidden path="id"/>		
	<form:hidden path="version"/>
	<form:hidden path="status"/>
	</ul>		
</form:form>
</body>
<head>
	<title><spring:message code="mms.assignmembers.new.title" text="Add Member Roles"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<script type="text/javascript">
	function loadMemberGrid(gridId, baseFilter) {
		var c_grid = null;
		var unselectedRow=null;
		$.ajax({async:false,url:'grid/' + gridId + '/meta.json', success:function(grid) {
				c_grid = $('#memberGrid').jqGrid({
				scroll:1,
				altRows:true,
				autowidth:true,
				shrinkToFit:true,
				ajaxGridOptions:{async:false},
				url:'member_role/assignmembers/unassigned/'+$('#role').val()+'.json',
				datatype: 'json',
				mtype: 'GET',
				colNames:eval(grid.colNames),
				colModel :eval(grid.colModel),
				pager: '#membergrid_pager',
				rowNum:grid.pageSize,
				sortname: 'id',
				sortorder:grid.sortOrder,
				viewrecords: true,
				jsonReader: { repeatitems : false},
				gridview:true,
				multiselect:eval(grid.multiSelect),
				postData: {
					"baseFilters": baseFilter
				},	
				loadComplete:function(data,obj){
				},		
				onSelectRow:function(rowId,status) {														
				}
			});
			$("#memberGrid").jqGrid('navGrid','#membergrid_pager',{edit:false,add:false,del:false, search:true},{},{},{},{multipleSearch:true});
			//$("#memberGrid").jqGrid('bindKeys');			
		}});
		return c_grid;
	};
	$(document).ready(function(){
		loadMemberGrid(24);
		var membersToAssign= $('#membersToAssign').val();
		if(membersToAssign!=""){
			var membersSelected=membersToAssign.split(",");
			for(var i=0;i<membersSelected.length;i++){
				if(membersSelected[i]!=undefined){
					$('#memberGrid').setSelection(membersSelected[i],false);
					}	
			}
		}		
		$('#saveForm').click(function(){
			var row=$("#memberGrid").jqGrid('getGridParam','selarrrow');
			if(row==""){
				alert("Please select atleast one member");
			}else{
				$('#membersToAssign').val(row);			
				$.post($('form').attr('action'),  
			            $("form").serialize(),  
			            function(data){	
		   				$('.contentPanel').html(data);	
		   				$('#refresh').val($('#refreshSe').val());	   				      
			   				if($('#info_type').val()=='success'){			   				
				   	   	   		$("#grid").trigger("reloadGrid");		   				
							}		   					   						   					
			            }); 
			}
			return false;								
		});			
	});
	</script>
	
</head>
</html>