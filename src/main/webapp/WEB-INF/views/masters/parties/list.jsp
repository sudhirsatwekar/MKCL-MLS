<%-- <%@ include file="/common/taglibs.jsp" %>
<html>
<head>
	<title><spring:message code="party.list.title" text="List of Parties"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>
	<div>
	<div class="commandbar">
		<div class="commandbarContent">
			<a href="party/new" id="new_record">
				<spring:message code="generic.new" text="New"/>
			</a> |
			<a href="party" id="delete_record">
				<spring:message code="generic.delete" text="Delete"/>
			</a>  |
		</div>
	</div>
	<%@ include file="/common/gridview.jsp" %>
	<input type="hidden" id="grid_id" value="${gridId}">
	</div>
</body>
</html>
 --%>
 <%@ include file="/common/taglibs.jsp" %>
<html>
<head>
	<title><spring:message code="party.list.title" text="List of Parties"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<script type="text/javascript">
		$(document).ready(function(){
			$('#new_record').click(function(){
				newRecord();
			});
			$('#edit_record').click(function(){
				editRecord();
			});
			$("#delete_record").click(function() {
				deleteRecord();
			});
		});
	</script>
</head>
<body>
	
<div style="font-size:11px;">
	<div class="commandbar">
		<div class="commandbarContent">
			<a href="#" id="new_record" class="butSim"><spring:message code="generic.new" text="New"/></a>  |
			<a href="#" id="edit_record" class="butSim"><spring:message code="generic.edit" text="Edit"/></a> |
			<a href="#" id="delete_record" class="butSim"><spring:message code="generic.delete" text="Delete"/></a>
		</div>
		<p>&nbsp;</p>
	</div>
	<%@ include file="/common/gridview.jsp" %>
	<input type="hidden" id="grid_id" value="${gridId}">
</div>
			
</body>
</html>
 