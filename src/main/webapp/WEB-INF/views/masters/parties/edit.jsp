<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
	<title><spring:message code="party.edit.title" text="Edit Party"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>

<div class="fields clearfix">
<form:form action="party" method="PUT"  modelAttribute="party">
	<%@ include file="/common/info.jsp" %>
	<h2><spring:message code="party.edit.heading" text="Enter Details "/>
		[Id:${party.id}]
	</h2>
	<p>
	<label class="small"><spring:message code="generic.id" text="Id"/></label>
		<form:input cssClass="field text small" path="id" readonly="true" /> 
	</p>
	<p>
	<label class="small"><spring:message code="party.name" text="Party Name"/>&nbsp;*</label>
		<form:input cssClass="field text medium" path="name"/>
		<form:errors path="name" cssClass="field_error" />	
	</p>
	<p>
	<label class="small"><spring:message code="party.abbreviation" text="Abbreviation"/>&nbsp;*</label>
		<form:input cssClass="field text medium" path="abbreviation"/>
		<form:errors path="abbreviation" cssClass="field_error" />	
	</p>
	<p>
	 	 <label class="small"><spring:message code="member_personal_details.photo.label" text="Upload Photo"/></label>
	 	 <c:choose>
	 	 	<c:when test="${empty party.photo}">
		 	 	<jsp:include page="/common/file_upload.jsp">
		 			<jsp:param name="fileid" value="photo" />
		 	  	</jsp:include>
	 	 	</c:when>
	 	 	<c:otherwise>
	 	 		<jsp:include page="/common/file_download.jsp">
	 	 			<jsp:param name="fileid" value="photo" />
		 			<jsp:param name="filetag" value="${party.photo}" />
		 	  	</jsp:include>
	 	 	</c:otherwise>
	 	 </c:choose>
		 <form:errors path="photo" cssClass="field_error" />
	</p>
	<div class="fields">
		<p class="tright">
			<input id="submit" type="submit" value="<spring:message code='generic.submit' text='Submit'/>" class="butDef">
		</p>
	</div>
	<form:hidden path="id"/>	
	<form:hidden path="version"/>
	<form:hidden path="locale" />
</form:form>
</div>
</body>
</html>