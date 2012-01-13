<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
	<title><spring:message code="masters.grids.new.title" text="Add Grid"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>
<form:form cssClass="wufoo" action="grid" method="POST" modelAttribute="grid">
	<div class="info">
		<h2><spring:message code="masters.grids.new.heading" text="Enter Details"/></h2>
		<div style="background-color:#C1CDCD; ;padding: 3px"><spring:message code="generic.mandatory.label" text="Note: Fields marked * are mandatory"/></div>
	</div>
	<ul>
		<li class="section first">
			<c:if test="${isvalid eq false}">
				<p class="field_error"><spring:message code="generic.error.label"/></p>
			</c:if>
		</li>	
		
		<li>
		<label class="desc"><spring:message code="generic.name" text="Name"/>&nbsp;*</label>
			<div>
				<form:input cssClass="field text medium" path="name"/><form:errors path="name" cssClass="field_error" />	
			</div>
		</li>
		<li>
		<label class="desc"><spring:message code="generic.title" text="Title"/>&nbsp;*</label>
			<div>
				<form:input cssClass="field text medium" path="title"/><form:errors path="title" cssClass="field_error" />	
			</div>
		</li>
		<li>
		<label class="desc"><spring:message code="masters.grids.columnnames" text="Column Names"/>&nbsp;*</label>
			<div>
				<form:input cssClass="field text medium" path="colNames"/><form:errors path="colNames" cssClass="field_error" />	
			</div>
		</li>
		<li>
		<label class="desc"><spring:message code="masters.grids.columnmodel" text="Column Model"/>&nbsp;*</label>
			<div>
				<form:input cssClass="field text medium" path="colModel"/><form:errors path="colModel" cssClass="field_error" />	
			</div>
		</li>
		<li>
		<label class="desc"><spring:message code="masters.grids.pagesize" text="Page Size"/>&nbsp;*</label>
			<div>
				<form:input cssClass="integer field text small" path="pageSize"/><form:errors path="pageSize" cssClass="field_error" />
			</div>
		</li>
		<li>
		<label class="desc"><spring:message code="masters.grids.sortfield" text="Sort Field"/>&nbsp;*</label>
			<div>
				<form:input cssClass="field text medium" path="sortField"/><form:errors path="sortField" cssClass="field_error" />	
			</div>
		</li>
		<li>
		<label class="desc"><spring:message code="masters.grids.sortorder" text="Sort Order"/>&nbsp;*</label>
			<div>
				<form:input cssClass="field text medium" path="sortOrder"/><form:errors path="sortOrder" cssClass="field_error" />	
			</div>
		</li>
		<li>
		<label class="desc"><spring:message code="masters.grids.query" text="Query"/>&nbsp;*</label>
			<div>
				<form:input cssClass="field text medium" path="query"/><form:errors path="query" cssClass="field_error" />	
			</div>
		</li>
		<li>
		<label class="desc"><spring:message code="masters.grids.countquery" text="Count Query"/>&nbsp;*</label>
			<div>
				<form:input cssClass="field text medium" path="countQuery"/><form:errors path="countQuery" cssClass="field_error" />	
			</div>
		</li>
		<li>
		<label class="desc"><spring:message code="masters.grids.nativequery" text="Native Query"/>&nbsp;*</label>
			<div>
				<form:checkbox path="nativeQuery" id="nativeQuery"/><form:errors path="nativeQuery" cssClass="field_error" />
			</div>
		</li>
		<li>
		<label class="desc"><spring:message code="generic.width" text="Width"/>&nbsp;*</label>
			<div>
				<form:input cssClass="integer field text small" path="width"/><form:errors path="width" cssClass="field_error" />
			</div>
		</li>
		<li>
		<label class="desc"><spring:message code="generic.height" text="Height"/>&nbsp;*</label>
			<div>
				<form:input cssClass="integer field text small" path="height"/><form:errors path="height" cssClass="field_error" />
			</div>
		</li>
		<li>
		<label class="desc"><spring:message code="masters.grids.detailview" text="Detail View"/>&nbsp;*</label>
			<div>
				<form:input cssClass="field text medium" path="detailView"/><form:errors path="detailView" cssClass="field_error" />	
			</div>
		</li>
		<li>
		<label class="desc"><spring:message code="masters.grids.multiselect" text="Multi Select Grid"/>&nbsp;*</label>
			<div>
				<form:checkbox path="multiSelect" id="multiSelect"/><form:errors path="multiSelect" cssClass="field_error" />
			</div>
		</li>
		<li>
		<label class="desc"><spring:message code="masters.grids.localized" text="Localized"/>&nbsp;*</label>
			<div>
				<form:checkbox path="localized" id="localized"/><form:errors path="localized" cssClass="field_error" />
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
</html>