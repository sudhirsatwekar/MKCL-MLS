<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
	<title><spring:message code="assemblyroles.list.title" text="List of Roles"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>
	<div>
	<div class="commandbar">
		<div class="commandbarContent">
			<a href="assembly_roles/new" id="new_record"><spring:message code="generic.new" text="New"/></a> |
			<a href="assembly_roles" id="delete_record"><spring:message code="generic.delete" text="Delete"/></a> |
		</div>
	</div>
	<%@ include file="/common/gridview.jsp" %>
	<input type="hidden" id="grid_id" value="${gridId}">
	</div>
</body>
</html>
