<%-- <%@ include file="/common/taglibs.jsp" %>
<html>
<head>
	<title><spring:message code="state.new.title" text="Add State"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>
<form:form cssClass="wufoo" action="states" method="POST" modelAttribute="state">
	<div class="info">
		<h2><spring:message code="state.new.heading" text="Enter Details"/></h2>
		<div style="background-color:#C1CDCD; ;padding: 3px"><spring:message code="generic.mandatory.label" text="Note: Fields marked * are mandatory"/></div>

	</div>
	<ul>	
		
		<li>
		<label class="desc"><spring:message code="generic.locale" text="Select language"/>&nbsp;*</label>
			<div>
				<form:select cssClass="field select medium" path="locale"> 
				<form:option value="en"><spring:message code="generic.lang.english" text="English"/></form:option>
					<form:option value="hi_IN"><spring:message code="generic.lang.hindi" text="Hindi"/></form:option>
					<form:option value="mr_IN"><spring:message code="generic.lang.marathi" text="Marathi"/></form:option>
				</form:select>
			</div>
		</li>

		<li>
		<label class="desc"><spring:message code="state.name" text="Name"/>&nbsp;*</label>
			<div>
				<form:input cssClass="field text medium" path="name" size="50"/><form:errors path="name" cssClass="field_error" />	
			</div>
		</li>
		
		<li class="buttons">
			<input id="saveForm" class="btTxt" type="submit" value="<spring:message code='generic.submit' text='Submit'/>" />
		</li>

		<form:hidden path="id"/>
		<form:hidden path="version"/>
	</ul>		
</form:form>
</body>
</html> --%>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
	<title><spring:message code="state.new.title" text="Add State"/></title>
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
<form:form action="states" method="POST"  modelAttribute="state">
	<%@ include file="/common/info.jsp" %>
	<h2><spring:message code="state.new.heading" text="Enter Details"/>
	</h2>
	<%-- <p>
		<label class="small"><spring:message code="generic.locale" text="Select language"/>&nbsp;*</label>
			<form:select cssClass="sSelect" path="locale"> 
				<form:option value="en"><spring:message code="generic.lang.english" text="English"/></form:option>
				<form:option value="hi_IN"><spring:message code="generic.lang.hindi" text="Hindi"/></form:option>
				<form:option value="mr_IN"><spring:message code="generic.lang.marathi" text="Marathi"/></form:option>
			</form:select>
	</p> --%>
	<p>
	<label class="small"><spring:message code="state.name" text="Name"/>&nbsp;*</label>
		<form:input cssClass="field text medium" path="name" size="50"/><form:errors path="name" cssClass="field_error" />	
	</p>
	<div class="fields">
		<p class="tright">
			<input id="submit" type="submit" value="<spring:message code='generic.submit' text='Submit'/>" class="butDef">
			<input id="sign" type="button" value="<spring:message code='generic.sign' text='Sign'/>" class="butDef">
		</p>
	</div>
	<form:hidden path="locale" />
	<form:hidden path="id"/>	
	<form:hidden path="version"/>
</form:form>
</div>
</body>
</html>