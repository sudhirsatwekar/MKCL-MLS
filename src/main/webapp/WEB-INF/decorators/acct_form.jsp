<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<title>
	<decorator:title/>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script type="text/javascript">
		$(document).ready(function() {
			$(':input:visible:not([readonly]):first').focus();
			$("form").submit(function(e){
				$.post($('form').attr('action'),  
		            $("form").serialize(),  
		            function(data){  
		            	$('#accountContent').html(data);
		            });  
		        return false;  
		    }); 
			if($('#info_msg').val().length!=0){
				$().toastmessage('showToast', {
				    text     : $('#info_msg').val(),
				    type     : $('#info_type').val(),
				    stayTime : 3000,
				    inEffectDuration:  600
				});
			}
		});
	</script>
	<decorator:head/>
</head>
<body>
	<div id="container">
		<decorator:body/>
		<div id="info" style="visibility: hidden;">
			<input id="info_type" type="hidden" value="${param.type}"></input>
			<input id="info_msg" type="hidden" value='<spring:message code="${param.msg}" text="${param.msg}"/>'/>
		</div>
	</div>
</body>
</html>