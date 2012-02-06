<%@ include file="/common/taglibs.jsp" %>
<!-- Header  --> 
<div id="pannelDash" class="clearfix">
	 <!-- Tabs--> 
	<div class="menu">
		 <ul>
			<li class="selected">
				<a href="#" onclick="showOnly('tabDashboard','dashWidget')"><img src="./resources/images/template/icons/home.png" alt="" />Welcome to ELS</a>
			</li>
		</ul>
		<div class="info">
			<div><a id="logout" class="icOff" href="<c:url value="/j_spring_security_logout" />"><spring:message code="logout" text="Logout"/></a></div>
			<div class="user">
				<img width="27" height="27" src="./resources/images/template/user_icon.png" alt="User name" />
				<span ><security:authentication property="principal.firstName"></security:authentication>&nbsp;<security:authentication property="principal.lastName"></security:authentication></span>
				<span class="detail">Last login : 25 Ian 2009</span>
			</div>
		</div>
	</div>
</div>