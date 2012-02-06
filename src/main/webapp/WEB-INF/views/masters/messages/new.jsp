<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
	<title><spring:message code="messageResource.new.title" text="Add Message Resource"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<script type="text/javascript">
		$('document').ready(function(){
			$('#sign').click(function(){
				var foo = crypto.signText("my text to sign", "ask"); 
				console.log(foo);
			})
		});
	</script>
</head>
<body>

<div class="fields clearfix">
<form:form action="messages" method="POST"  modelAttribute="messageResource">
	<%@ include file="/common/info.jsp" %>
	<h2><spring:message code="messageResource.new.heading" text="Enter Details"/>
		[Id:&nbsp;New]
	</h2>
	<p>
		<label class="small"><spring:message code="generic.locale" text="Select language"/>&nbsp;*</label>
			<form:select cssClass="sSelect" path="locale"> 
				<form:option value="en"><spring:message code="generic.lang.english" text="English"/></form:option>
				<form:option value="hi_IN"><spring:message code="generic.lang.hindi" text="Hindi"/></form:option>
				<form:option value="mr_IN"><spring:message code="generic.lang.marathi" text="Marathi"/></form:option>
			</form:select>
	</p>
	
	<c:set var="codeErrors"><form:errors path="code"/></c:set>
	<p <c:if test="${not empty codeErrors}">class="error"</c:if>>
		<label class="small"><spring:message code="messageResource.code" text="Code"/>&nbsp;*</label>
		<form:input cssClass="sText large" path="code"/>
		<span><form:errors path="code" /></span>	
	</p>
	<c:set var="valueErrors"><form:errors path="value"/></c:set>
	<p <c:if test="${not empty valueErrors}">class="error"</c:if>>
		<label class="small"><spring:message code="messageResource.text" text="Text"/>&nbsp;*</label>
		<form:input cssClass="sText large" path="value"/>
		<span><form:errors path="value" /></span>
	</p>
	<div class="fields">
		<h2><spring:message code="messageResource.description" text="Description"/></h2>
		<form:textarea cssClass="wysiwyg sTextarea" path="description" rows="10" cols="78"/>
	</div>
	<div class="fields">
		<h2></h2>
		<p class="tright">
			<input id="submit" type="submit" value="<spring:message code='generic.submit' text='Submit'/>" class="butDef">
			<input id="sign" type="button" value="<spring:message code='generic.sign' text='Sign'/>" class="butDef">
		</p>
	</div>
	<form:hidden path="id"/>	
	<form:hidden path="version"/>
</form:form>
</div>
</body>
</html>