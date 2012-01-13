<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<title>
	<decorator:title/>
</title>
	<decorator:head/>
	<script type="text/javascript">
		$(document).ready(function() {
			initControls();
		    $(':input:visible:not([readonly]):first').focus();
			$("form").submit(function(e){
				$.post($('form').attr('action'),  
		            $("form").serialize(),  
		            function(data){	
	   				$('.contentPanel').html(data);	
	   				$('#refresh').val($('#refreshSe').val());	   					            
		   				if($('#info_type').val()=='success'){			   				
			   		    	 $("#navContent").load('menus/list');				   				
						}		   					   						   					
		            });  
		        return false;  
		    }); 
			if($('#info_msg').val().length!=0){
				$().toastmessage('showToast',{
					text:$('#info_msg').val(),
					type:$('#info_type').val(),
					stayTime:3000,
					inEffectDuration:600
				});
			}	
			$('#refresh').val($('#refreshSe').val());									
					  
		});
	</script>
</head>
<body>	
	<div id="container">
	<decorator:body/>	
	<div id="info" style="visibility: hidden;">
	<c:choose>
	<c:when test="${(!empty type) && (!empty msg)}">
	<input id="info_type" type="text"  value="${type}">
	<input id="info_msg" type="hidden" value="<spring:message code='${msg}'/>">
	</c:when>
	<c:when test="${(!empty param.type) && (!empty param.msg)}">
	<input id="info_type" type="hidden"  value="${param.type}">
	<input id="info_msg" type="hidden" value="<spring:message code='${param.msg}'/>">
	</c:when>
	<c:otherwise>
	<input id="info_type"  type="text" value="">
	<input id="info_msg" type="hidden" value="">
	</c:otherwise>
	</c:choose>	
	</div>	
	<input type="hidden" id="refreshSe" value="<%=session.getAttribute("refresh")%>">
	</div>	
	<img id="bottom" src="./resources/images/wufoo/bottom.png" alt="" />
</body>
</html>