<%-- <%@ include file="/common/taglibs.jsp" %>
<html>
<head>
	<title><spring:message code="district.edit.title" text="Edit District"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>
<form:form cssClass="wufoo" action="districts" method="PUT" modelAttribute="district">
	<div class="info">
		<h2><spring:message code="district.edit.heading" text="Details"/>[Id:${district.id}]</h2>
		<div style="background-color:#C1CDCD; ;padding: 3px">
		<spring:message code="generic.mandatory.label" text="All fields marked * are mandatory"/></div>
	</div>
	<ul>	
		<li class="section first">
			<c:if test="${isvalid eq false}">
				<p class="field_error"><spring:message code="generic.error.label"/></p>
			</c:if>
		<form:errors path="version" cssClass="field_error" />		
		</li>
		<li>
		<label class="desc"><spring:message code="generic.id" text="Id"/></label>
			<div>
				<form:input cssClass="field text small" path="id" readonly="true" /> 
			</div>
		</li>	
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
		<label class="desc"><spring:message code="district.state" text="State"/>&nbsp;*</label>
			<div>
				<form:select path="state" items="${states}" itemValue="id" itemLabel="name">
	            </form:select>			
	        </div>
		</li>		
		<li>
		<label class="desc"><spring:message code="district.name" text="District"/>&nbsp;*</label>
			<div>
				<form:input cssClass="field text medium" path="name" size="50"/><form:errors path="name" cssClass="field_error" />	
			</div>
		</li>		
		<li class="buttons">
			<input id="saveForm" class="btTxt" type="submit" value="<spring:message code='generic.submit' text='Submit'/>" />
		</li>
		<form:hidden path="version"/>
	</ul>		
</form:form>
</body>
</html> --%>

<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
	<title><spring:message code="district.edit.title" text="Edit District"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>

<div class="fields clearfix">
<form:form action="districts" method="PUT"  modelAttribute="district">
	<%@ include file="/common/info.jsp" %>
	<h2><spring:message code="district.edit.heading" text="Enter Details "/>
		[Id:${district.id}]
	</h2>
	<p class="section first">
			<c:if test="${isvalid eq false}">
				<p class="field_error"><spring:message code="generic.error.label"/></p>
			</c:if>
		<form:errors path="version" cssClass="field_error" />		
	</p>
	<p>
	<label class="small"><spring:message code="generic.id" text="Id"/></label>
		<form:input cssClass="field text small" path="id" readonly="true" /> 
	</p>
	<%-- <p>
		<label class="small"><spring:message code="generic.locale" text="Select language"/>&nbsp;*</label>
			<form:select cssClass="sSelect" path="locale"> 
				<form:option value="en"><spring:message code="generic.lang.english" text="English"/></form:option>
				<form:option value="hi_IN"><spring:message code="generic.lang.hindi" text="Hindi"/></form:option>
				<form:option value="mr_IN"><spring:message code="generic.lang.marathi" text="Marathi"/></form:option>
			</form:select>
	</p> --%>
	<p>
	<label class="small"><spring:message code="district.state" text="State"/>&nbsp;*</label>
		<form:select path="state" items="${states}" itemValue="id" itemLabel="name">
	    </form:select>			
	</p>
	<p>
	<label class="small"><spring:message code="district.name" text="District"/>&nbsp;*</label>
		<form:input cssClass="field text medium" path="name" size="50"/><form:errors path="name" cssClass="field_error" />	
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