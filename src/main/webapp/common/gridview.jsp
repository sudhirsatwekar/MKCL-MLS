<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<script type="text/javascript">
		$(document).ready(function(){
			var gridId = $('#grid_id').val();
		    var grid = loadGrid(gridId);
		});
	</script>
</head>
<body>
<input type="hidden" id="refresh" value="<%=session.getAttribute("refresh") %>">
<div id="grid_container">
	<table id="grid"></table> 
	<div id="grid_pager"></div>
</div>
</body>
</html>
