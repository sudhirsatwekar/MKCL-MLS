<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
	<title><spring:message code="party.new.title" text="Add Party"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<script type="text/javascript">
		$(document).ready(function(){
			initControls();
		});
	</script>
</head>
<body>

<div class="fields clearfix">
<form:form action="parties" method="POST"  modelAttribute="party">
	<%@ include file="/common/info.jsp" %>
	<h2><spring:message code="party.new.heading" text="Enter Details"/>
		[Id:&nbsp;New]
	</h2>
	<p>
	<label class="small"><spring:message code="party.name" text="Name"/>&nbsp;*</label>
			<form:input cssClass="field text medium" path="name"/>
			<form:errors path="name" cssClass="field_error" />	
	</p>
	<p>
	<label class="small"><spring:message code="party.abbreviation" text="Abbreviation"/>&nbsp;*</label>
			<form:input cssClass="field text medium" path="abbreviation"/>
			<form:errors path="abbreviation" cssClass="field_error" />	
	</p>
	<p>
		 	 <label class="small"><spring:message code="party.photo.label" text="Party Symbol"/></label>
			 <jsp:include page="/common/file_upload.jsp">
			 	<jsp:param name="fileid" value="photo" />
			 </jsp:include>
			 <form:errors path="photo" cssClass="field_error" />
	</p>
	<div class="fields">
		<p class="tright">
			<input id="submit" type="submit" value="<spring:message code='generic.submit' text='Submit'/>" class="butDef">
		</p>
	</div>
	<form:hidden path="locale" />
	<form:hidden path="id"/>	
	<form:hidden path="version"/>
</form:form>
</div>
</body>
</html>