<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<link rel="stylesheet" media="screen" href="./resources/css/insync.css" />
<script type="text/javascript" src="./resources/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript"> 
	$(document).ready(function(){
	    $("#j_username").focus();
	    $("#lang").change(function(){
		   location.search = "?lang="+$('#lang').val();
	    });
	    
	});
</script> 
</head>
<body class="login">

<p>
<input type="hidden" value="${lang}" id="language">
<label for="lang"><spring:message code="lang" text="Change Language" /></label>	
			<select id="lang" name="language" >
				<c:forEach items="${locales}" var="i">
				<option value='${i.split("#")[0]}'>${i.split("#")[1]}</option>
				</c:forEach>					
			</select>
</p>

    <div class="login-box main-content">
       	<section>
    		<c:if test="${not empty param['error']}"> 
    			<div class="message error">${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</div>
			</c:if> 
			<c:if test="${empty param['error']}"> 
    			<div class="message info"></div>
			</c:if> 
    		<form id="form" action="<c:url value='/j_spring_security_check'/>" method="post" class="clearfix">
			<p>
				<label for="j_username"><spring:message code="user_lbl_fname" text="Username" /></label>
				<input type="text" id="j_username"  class="full" value="" name="j_username"/>
			</p>
			<p>
				<label for="password"><spring:message code="user_lbl_password" text="Password" /></label>
				<input type="password" id="j_password" class="full" value="" name="j_password"/>
			</p>
			<p>
			</p>
			<p class="clearfix">
				<span class="fl">
					<input type="checkbox" id="remember" class="" value="1" name="remember"/>
					<label class="choice" for="remember"><spring:message code="user_lbl_remember" text="Remember Password" /></label>
				</span>
				<input id="saveForm" class="button button-gray fr" type="submit" value="<spring:message code='user_lbl_login' text='Login'/>" />			
			</p>
		</form>
    	</div>
    </section>
</body>
</html>