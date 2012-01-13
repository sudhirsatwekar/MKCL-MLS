<%@ include file="/common/taglibs.jsp" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
	<title>प्रस्ताव सादर करणे</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>
	<div>
	<div class="commandbar">
		<div class="commandbarContent">
			<a href="motion_aproval/Process" id="process_record">Process
			</a> |
			<a href="motion_aproval/print" id="print_record"> Print
			</a> |
			<a href="motion_aproval/Citations" id="citations_record">
				Citations
			</a>  
		</div>
	</div>
	<%@ include file="/common/gridview.jsp" %>
	<input type="hidden" id="grid_id" value="${gridId}">
	</div>
</body>
</html>
