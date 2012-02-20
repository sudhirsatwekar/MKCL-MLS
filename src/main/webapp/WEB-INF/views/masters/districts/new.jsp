<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
	<title><spring:message code="district.new.title" text="Add District"/></title>
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
<form:form action="districts" method="POST"  modelAttribute="district">
	<%@ include file="/common/info.jsp" %>
	<h2><spring:message code="district.new.heading" text="Enter Details"/>
		
	</h2>
	<p>
	<label class="small"><spring:message code="district.state" text="State"/>&nbsp;*</label>
			<form:select path="state" items="${states}" itemValue="id" itemLabel="name">
	            </form:select>			
	</p>
	<p>
	<p>
	<label class="small"><spring:message code="district.name" text="District"/>&nbsp;*</label>
		<form:input cssClass="field text medium" path="name" size="50"/><form:errors path="name"  cssClass="field_error" />	
	</p>
	<div class="fields">
		<h2></h2>
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