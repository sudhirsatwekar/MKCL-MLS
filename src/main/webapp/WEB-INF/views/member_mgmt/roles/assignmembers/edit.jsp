<%@ include file="/common/taglibs.jsp" %>
<html>
<body>
<form class="wufoo" action="member_role/assignmembers/unassignMemberRoles" method="post"">
<div class="info">
			<h2><spring:message code="mms.assignroles.edit.heading" text="Details"/></h2>
			<div style="background-color:#C1CDCD; ;padding: 3px"><spring:message code="generic.mandatory.label" text="Note: Fields marked * are mandatory"/></div>
</div>
<ul>
		<li>
		<label class="desc"><spring:message code="mms.assignmembers.roleid" text="Role Id"/></label>
			<div>
			<input type="text" value="${role.id}" name="roleId" id="roleId" readonly="readonly">
			</div>
		</li>
		<li>
		<label class="desc"><spring:message code="mms.assignmembers.rolename" text="Role Name"/></label>
			<div>
			<input type="text" value="${role.name}" name="roleName" id="roleName" readonly="readonly">
			</div>
		</li>
		
</ul>
<div id="grid_container">
		<table id="memberRoleGrid"></table> 
		<div id="memberrolegrid_pager"></div>
</div>
<div class="commandbar">
		<div class="commandbarContent">
			<a href="#" id="unassign_roles">
				<spring:message code="member_mgmt.assignroles.unassignroles" text="Unassign Member Roles"/>
			</a> |
			<a href="#" id="edit_assigned_roles">
				<spring:message code="member_mgmt.assignroles.editroles" text="Edit Member Role"/>
			</a> |
			<a href="#" id="delte_assigned_roles">
				<spring:message code="member_mgmt.assignroles.deleteroles" text="Delete Member Role"/>
			</a> |
			
		</div>
</div>
<ul>
<li class="buttons">
		<input type="hidden" name="memberRolesToUnassign" id="memberRolesToUnassign" >
		<input type="hidden" name="memberRolesToDelete" id="memberRolesToDelete" >
</li>
</ul>
</form>
</body>
<head>
	<title><spring:message code="mms.assignroles.edit.title" text="Edit Member Roles"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>	
	<link rel="stylesheet" media="screen" href="./resources/css/tables.css" />	
	<script type="text/javascript">
	function loadMemberGrid(gridId, baseFilter) {
		var c_grid = null;
		var unselectedRow=null;
		$.ajax({async:false,url:'grid/' + gridId + '/meta.json', success:function(grid) {
				c_grid = $('#memberRoleGrid').jqGrid({
				scroll:1,
				altRows:true,
				autowidth:true,
				shrinkToFit:true,
				ajaxGridOptions:{async:false},
				url:'member_role/assignmembers/assigned/'+$('#roleId').val()+'.json',
				datatype: 'json',
				mtype: 'GET',
				colNames:eval(grid.colNames),
				colModel :eval(grid.colModel),
				pager: '#memberrolegrid_pager',
				rowNum:grid.pageSize,
				sortname: 'm.id',
				sortorder:grid.sortOrder,
				viewrecords: true,
				jsonReader: { repeatitems : false},
				gridview:true,
				multiselect:eval(grid.multiSelect),
				postData: {
					"baseFilters": baseFilter
				},	
				loadComplete:function(data,obj){
					//$('.cbox').attr("checked","checked");
					//$('#cb_memberRoleGrid').removeAttr("checked");
														
				},		
				onSelectRow:function(rowId,status) {
						//if(status){			
							//$('input[type="checkbox"][id$="'+rowId+'"]').removeAttr("checked");	
						//}else{
							//$('input[type="checkbox"][id$="'+rowId+'"]').attr("checked","checked");	
						//}										
				}
			});
			$("#memberRoleGrid").jqGrid('navGrid','#memberrolegrid_pager',{edit:false,add:false,del:false, search:true},{},{},{},{multipleSearch:true});
			$("#memberRoleGrid").jqGrid('bindKeys');			
		}});
		return c_grid;
	};
	$(document).ready(function(){
		loadMemberGrid(23);			
		
		$('#unassign_roles').click(function(){
			var row = $("#memberRoleGrid").jqGrid('getGridParam','selarrrow');
			if(row==""){
				alert("Please select atleast one role to unassign");
			}else{
				$('#memberRolesToUnassign').val(row);
				$.post($('form').attr('action'),  
			            $("form").serialize(),  
			            function(data){	
		   				$('.contentPanel').html(data);	
		   				$('#refresh').val($('#refreshSe').val());	   				      
			   				if($('#info_type').val()=='success'){			   				
				   	   	   		$("#memberRoleGrid").trigger("reloadGrid");		   				
							}		   					   						   					
			     }); 	
			}	
			return false;			
		});

		$('#edit_assigned_roles').click(function(){
			var row = $("#memberRoleGrid").jqGrid('getGridParam','selarrrow');			
			if(row==""){
				alert("Please select atmost one role to edit");
			}else if(row.length>1){
				alert("Maximum one row can be edited at a time");				
				$('#memberRoleGrid').resetSelection();					
			
			}else{
				$.get('member_role/assignmembers/memberrole/'+row+'/edit',			             
			            function(data){	
		   				$('.contentPanel').html(data);	
						}  						   					
			     ); 	
			}
			return false;				
		});

		$('#delte_assigned_roles').click(function(){
			var row = $("#memberRoleGrid").jqGrid('getGridParam','selarrrow');
			if(row==""){
				alert("Please select atleast one role to delete");
			}else{
				$('#memberRolesToDelete').val(row);
				$.post('member_role/assignmembers/deleteMemberRoles',  
			            $("form").serialize(),  
			            function(data){	
		   				$('.contentPanel').html(data);	
		   				$('#refresh').val($('#refreshSe').val());	   				      
			   				if($('#info_type').val()=='success'){			   				
				   	   	   		$("#memberRoleGrid").trigger("reloadGrid");		   				
							}		   					   						   					
			     }); 	
			}	
			return false;			
		});
	});
	</script>
</head>
</html>