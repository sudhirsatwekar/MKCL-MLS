<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
	<title>Change Password</title>
</head>
<body>
<c:if test="${!type}">
<form:form cssClass="wufoo" action="acct/changepwd" method="POST" modelAttribute="password">
	<div class="info">
		<h2>Change Password</h2>
		<div style="background-color:#C1CDCD; ;padding: 3px">Note: Fields marked * are mandatory</div>
	</div>
	<ul>
		<li class="section first">
			<c:if test="${isvalid eq false}">
				<p class="field_error">Please correct the following errors</p>
			</c:if>
		</li>
	
		<li>
		<label class="desc">Old Password&nbsp;*</label>
			<div>
				<form:password cssClass="field text small" path="oldPassword" /><form:errors path="oldPassword" cssClass="field_error" /> 
			</div>
		</li>
		<li>
		<label class="desc">New Password&nbsp;*</label>
			<div>
				<form:password cssClass="field text medium" path="newPassword"/><form:errors path="newPassword" cssClass="field_error" />	
			</div>
		</li>
		<li>
		<label class="desc">Re-enter New Password&nbsp;*</label>
			<div>
				<form:password cssClass="field text medium" path="confirmPassword"/><form:errors path="confirmPassword" cssClass="field_error" />	
			</div>
		</li>
		
		<li class="buttons">
			<input id="saveForm" class="btTxt" type="submit" value="Submit" />
		</li>
	</ul>		
</form:form>
</c:if>
<c:if test="${type}">
	<div class="info">
		<h2>You have successfully updated the password. Please logout and login again to confirm the changes.<br> Proceed to <a id="logout" href="<c:url value="/j_spring_security_logout" />">Logout</a></h2>
	</div>
</c:if>
</body>
</html>