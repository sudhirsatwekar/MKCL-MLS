<%@ include file="/common/taglibs.jsp" %>
<html>
<body>
	<div>
	<div class="commandbar">
		<div class="commandbarContent">
			<a href="member_role/assignroles" id="assign_roles">
				<spring:message code="member_mgmt.assignroles.individually" text="Assign New Roles"/>
			</a> |
			
		</div>
	</div>
	<%@ include file="/common/gridview.jsp" %>
	<input type="hidden" id="grid_id" value="${gridId}">
	</div>
</body>
<head>
	<title><spring:message code="member_mgmt.assignroles.list" text="List of Members"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<script type="text/javascript">
	
	$("#assign_roles").click(function() {
		var row = $("#grid").jqGrid('getGridParam','selrow');
		if(row==null){
			alert("Please select the member first");		
		}else{
			var url = $(this).attr('href')+"/"+row+"/new";
			$('.contentPanel').load(url,function(data){
                var title = $(data).filter('title').text();
				$('#content > .subHeader > div').html(title);
			});			
		}
		return false;
	});	
	</script>
</head>
</html>
