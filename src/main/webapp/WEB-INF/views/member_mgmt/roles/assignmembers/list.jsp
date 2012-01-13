<%@ include file="/common/taglibs.jsp" %>
<html>
<body>
	<div>
	<div class="commandbar">
		<div class="commandbarContent">
			<a href="member_role/assignmembers" id="assign_members">
				<spring:message code="member_mgmt.assignmembers" text="Assign New Members"/>
			</a> |			
		</div>
	</div>
	<%@ include file="/common/gridview.jsp" %>
	<input type="hidden" id="grid_id" value="${gridId}">
	</div>
</body>
<head>
	<title><spring:message code="member_mgmt.assignmembers.list" text="List of Roles"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<script type="text/javascript">
	$("#assign_members").click(function() {
		var row = $("#grid").jqGrid('getGridParam','selrow');
		if(row==null){
			alert("Please select the role first");		
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
