<%@ include file="/common/taglibs.jsp" %>
<div class="header">
        	<div style="color:navy;font-weight:bold;">
         		<spring:message code="welcome" text="Welcome"/> <security:authentication property="principal.firstName"></security:authentication>&nbsp;<security:authentication property="principal.lastName"></security:authentication> | <a id="account" href="acct"><spring:message code="yourAccount" text="Your Account"/></a> | <a id="logout" href="<c:url value="/j_spring_security_logout" />"><spring:message code="logout" text="Logout"/></a> | <a href="#"><spring:message code="help" text="Help"/></a>
        	</div>
        	<span class="title"><spring:message code="project.name" text="e-Legislature"/> <a href="#" class="version"><spring:message code="app.version" text="v 0.0.1"/></a></span><br />
</div>