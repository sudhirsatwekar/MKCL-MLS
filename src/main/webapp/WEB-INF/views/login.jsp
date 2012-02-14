<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
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
<style type="text/css">
body, div, dl, dt, dd, ul, ol, li, h1, h2, h3, h4, h5, h6, pre, form, fieldset, input, textarea, p, blockquote, th, td {
    margin: 0;
    padding: 0;
}

body {
    background: none repeat scroll 0 0 #EEEEEE;
    color: #333333;
    font-family: "Trebuchet MS",Arial,Helvetica,sans-serif;
    font-size: 14.5px;
    line-height: 16px;
    text-shadow: 0 1px 0 #FFFFFF;
}
a {
    color: #333333;
    text-decoration: underline;
}
form {
    background: -moz-linear-gradient(90deg, #CCCCCC, #FFFFFF) repeat scroll 0 0 transparent;
    border: 1px solid #AAAAAA;
    border-radius: 10px 10px 10px 10px;
    box-shadow: 0 0 15px #AAAAAA;
    margin: 60px auto 0;
    padding: 20px;
    width: 440px;
}
h1 {
    border-bottom: 1px solid #CCCCCC;
    font-size: 15px;
    font-weight: bold;
    letter-spacing: 2px;
    margin-bottom: 25px;
    padding-bottom:5px;
}
form p {
    margin-bottom: 15px;
}
form p:last-child {
    margin-bottom: 0;
}
label {
    cursor: pointer;
    display: block;
    float: left;
    font-size: 13px;
    font-weight: bold;
    line-height: 28px;
    margin-bottom: 5px;
    width: 120px;
}
form p:hover label {
    color: #0459B7;
}
form p:hover label:after {
    content: " »";
}
input[type="text"], input[type="password"] {
    background: -moz-linear-gradient(90deg, #FFFFFF, #EEEEEE) repeat scroll 0 0 transparent;
    border: 1px solid #AAAAAA;
    border-radius: 3px 3px 3px 3px;
    box-shadow: 0 0 3px #AAAAAA;
    padding: 5px;
    width:200px;
}
input[type="text"]:focus, input[type="password"]:focus {
    border-color: #093C75;
    box-shadow: 0 0 3px #0459B7;
    outline: medium none;
}
select {
    box-shadow: 0 0 3px #AAAAAA;
    cursor: pointer;
    padding: 3px;
}
select:active, select:focus {
    border: 1px solid #093C75;
    box-shadow: 0 0 3px #0459B7;
    outline: medium none;
}
input[type="submit"], a.submit {
    background: -moz-linear-gradient(90deg, #0459B7, #08ADFF) repeat scroll 0 0 transparent;
    border: 1px solid #093C75;
    border-radius: 3px 3px 3px 3px;
    box-shadow: 0 1px 0 #FFFFFF;
    color: #FFFFFF;
    cursor: pointer;
    font-family: Arial,sans-serif;
    font-size: 12px;
    font-weight: bold;
    margin-left: 120px;
    padding: 5px 10px;
    text-decoration: none;
    text-shadow: 0 1px 1px #333333;
    text-transform: uppercase;
}
input[type="submit"]:hover, a.submit:hover {
    background: -moz-linear-gradient(90deg, #067CD3, #0BCDFF) repeat scroll 0 0 transparent;
    border-color: #093C75;
    text-decoration: none;
}
input[type="submit"]:active, input[type="submit"]:focus, a.submit:active, a.submit:focus {
    background: -moz-linear-gradient(90deg, #0BCDFF, #067CD3) repeat scroll 0 0 transparent;
    border-color: #093C75;
    outline: medium none;
}
.error {
	font-weight: bold;
	color: red;
}
</style>
<body>
		
  		<form id="form" action="<c:url value='/j_spring_security_check'/>" method="post">
		<img alt="" src="./resources/images/header.jpg" >
		<p></p>
		<c:if test="${not empty param['error']}"> 
			<p class="error">${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</p>
			<c:remove var = "SPRING_SECURITY_LAST_EXCEPTION" scope = "session" />
		</c:if> 
		<c:if test="${empty param['error']}"> 
	  		<p class="info"></p>
		</c:if> 
  		<h1>Log On to E-Legislature</h1>
  	<p>
	  	<input type="hidden" value="${lang}" id="language">
		<label for="lang"><spring:message code="lang" text="Change Language" /></label>	
		<select id="lang" name="language" >
			<c:forEach items="${locales}" var="i">
			<option value='${i.split("#")[0]}'>${i.split("#")[1]}</option>
			</c:forEach>					
		</select>
  	</p>
	<p>
		<label for="j_username"><spring:message code="user_lbl_username" text="Username" /></label>
		<input type="text" id="j_username"   value="" name="j_username"/>
	</p>
	<p>
		<label for="password"><spring:message code="user_lbl_password" text="Password" /></label>
		<input type="password" id="j_password"  value="" name="j_password"/>
	</p>
	<p>
		<span class="fl">
			<a href="#">I Forgot My Password!</a>
		</span>
		<input id="saveForm" class="button button-gray fr" type="submit" value="<spring:message code='user_lbl_login' text='Login'/>" />			
	</p>
		</form>
</body>
</html>