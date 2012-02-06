<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<title>
	<decorator:title/>
</title>
	<decorator:head/>			
	<script type="text/javascript">
		$(document).ready(function() {
			//initControls();
		    $(':input:visible:not([readonly]):first').focus();
			$("form").submit(function(e){
				$.post($('form').attr('action'),  
		            $("form").serialize(),  
		            function(data){
	   					$('.tabContent').html(data);
	   				/*$('#refresh').val($('#refreshSe').val());	   				      
		   				if($('#info_type').val()=='success'){			   				
			   	   	   		$("#grid").trigger("reloadGrid");		   				
						}
					  //$('#contentPanel').animate({scrollTop:0}, 'slow');			
					  //$('#content').animate({scrollTop:0}, 'slow'); 
					  //$('body').animate({scrollTop:0}, 'slow'); */
		            });
		        return false;  
		    }); 
			/*if($('#info_msg').val().length!=0){
				$().toastmessage('showToast',{
					text:$('#info_msg').val(),
					type:$('#info_type').val(),
					stayTime:3000,
					inEffectDuration:600
				});
			}*/
			$('#refresh').val($('#refreshSe').val());									
		});

		
	</script>	
</head>
<body>	
	<decorator:body/>	
</body>
</html>