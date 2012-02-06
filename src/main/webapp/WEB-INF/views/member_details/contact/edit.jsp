<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
	<title>
		<spring:message code="member_information_system" text="Member Information System"/>	
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<script type="text/javascript">	
	/*
	*Same as above functionality
	*/
	$('document').ready(function(){
		
		$('#addressSameAsAbove').change(function(){
			$('#permanent_details').toggle('slow');
		});
		//Related to state ,ditricts and tehsils	
		if($('#presentStates').val()!=undefined){
			loadDistrictsByStateId($('#presentStates').val(),"present");			
		}
		if($('#permanentStates').val()!=undefined){
			loadDistrictsByStateId($('#permanentStates').val(),"permanent");			
		}
		$('#presentStates').change(function(){
			loadDistrictsByStateId($('#presentStates').val(),"present");				
		});	
		$('#presentDistricts').change(function(){
			loadTehsilsByDistrictId($('#presentDistricts').val(),"present");				
		});	
		$('#permanentStates').change(function(){
			loadDistrictsByStateId($('#permanentStates').val(),"permanent");				
		});	
		$('#permanentDistricts').change(function(){
			loadTehsilsByDistrictId($('#permanentDistricts').val(),"permanent");				
		});
		

		initControls();
	    $(':input:visible:not([readonly]):first').focus();
	});   		 
	</script>	
</head>
<body>	
<div class="fields clearfix">
<form:form  action="member_contact_details" method="PUT" modelAttribute="memberContactDetails">
		<%@ include file="/common/info.jsp" %>	
		<h2><spring:message code="memberPersonalDetails.new.heading" text="Contact Details"/>
			[Id:&nbsp;${memberContactDetails.id}]
		</h2>
		<c:set var="emailErrors"><form:errors path="email"/></c:set>
		<p <c:if test="${not empty emailErrors}">class="error"</c:if>>
			<label class="small"><spring:message code="member_contact_details.email" text="Email"/></label>
			<form:input cssClass="sText large" path="email"/>
			<span><form:errors path="email" /></span>	
		</p>
		<c:set var="telephoneErrors"><form:errors path="permanentTelephone"/></c:set>
		<p <c:if test="${not empty telephoneErrors}">class="error"</c:if>>
			<label class="small"><spring:message code="member_contact_details.telephone" text="Telephone"/></label>
			<form:input cssClass="sText" path="permanentTelephone"/>
			<span><form:errors path="permanentTelephone" /></span>	
		</p>
		<c:set var="faxErrors"><form:errors path="permanentFax"/></c:set>
		<p <c:if test="${not empty faxErrors}">class="error"</c:if>>
			<label class="small"><spring:message code="member_contact_details.fax" text="Fax"/></label>
			<form:input cssClass="sText" path="permanentFax"/>
			<span><form:errors path="permanentFax" /></span>	
		</p>
		<c:set var="mobileErrors"><form:errors path="permanentMobile"/></c:set>
		<p <c:if test="${not empty mobileErrors}">class="error"</c:if>>
			<label class="small"><spring:message code="member_contact_details.mobile" text="mobile"/></label>
			<form:input cssClass="sText" path="permanentMobile"/>
			<span><form:errors path="permanentMobile" /></span>	
		</p>
		<div class="fields">
			<h2><spring:message code="member_contact_details.present.address" text="Present Address Details"/></h2>
		</div>
		<c:set var="presentAddressErrors"><form:errors path="presentAddress"/></c:set>
		<p <c:if test="${not empty presentAddressErrors}">class="error"</c:if>>
			<label class="small"><spring:message code="member_contact_details.address" text="Address"/></label>
			<form:input cssClass="sText large" path="presentAddress"/>
			<span><form:errors path="presentAddress" /></span>	
		</p>
		<c:set var="presentCityErrors"><form:errors path="presentCity"/></c:set>
		<p <c:if test="${not empty presentCitycityErrors}">class="error"</c:if>>
			<label class="small"><spring:message code="member_contact_details.city" text="City"/></label>
			<form:input cssClass="sText large" path="presentCity"/>
			<span><form:errors path="presentCity" /></span>	
		</p>
		<c:set var="presentStateErrors"><form:errors path="presentState"/></c:set>
		<p <c:if test="${not empty presentStateErrors}">class="error"</c:if>>
			<label class="small"><spring:message code="member_contact_details.state" text="State"/></label>
			<form:select path="presentState" items="${presentStates}" itemValue="id" itemLabel="name" id="presentStates" cssClass="sSelect"/>
			<span><form:errors path="presentState" /></span>	
		</p>
		<c:set var="presentDistrictErrors"><form:errors path="presentDistrict"/></c:set>
		<p <c:if test="${not empty presentDistrictErrors}">class="error"</c:if>>
			<label class="small"><spring:message code="member_contact_details.district" text="District"/></label>
			<form:select path="presentDistrict" items="${presentDistricts}" itemValue="id" itemLabel="name" id="presentDistricts" cssClass="sSelect"/>
			<span><form:errors path="presentDistrict" /></span>
		</p>
		<c:set var="presentTehsilErrors"><form:errors path="presentTehsil"/></c:set>
		<p <c:if test="${not empty presentTehsilErrors}">class="error"</c:if>>
			<label class="small"><spring:message code="member_contact_details.tehsil" text="Tehsil"/></label>
			<form:select path="presentTehsil" items="${presentTehsils}" itemValue="id" itemLabel="name" id="presentTehsils" cssClass="sSelect"/>
			<span><form:errors path="presentTehsil" /></span>
		</p>
		<c:set var="pinErrors"><form:errors path="presentPinCode"/></c:set>
		<p <c:if test="${not empty pinErrors}">class="error"</c:if>>
			<label class="small"><spring:message code="member_contact_details.pincode" text="Pincode"/></label>
			<form:input cssClass="sText" path="presentPinCode" size="6"/>
			<span><form:errors path="presentPinCode" /></span>	
		</p>
		<c:set var="telephoneErrors"><form:errors path="presentTelephone"/></c:set>
		<p <c:if test="${not empty telephoneErrors}">class="error"</c:if>>
			<label class="small"><spring:message code="member_contact_details.telephone" text="Telephone"/></label>
			<form:input cssClass="sText" path="presentTelephone"/>
			<span><form:errors path="presentTelephone" /></span>	
		</p>
		<c:set var="faxErrors"><form:errors path="presentFax"/></c:set>
		<p <c:if test="${not empty faxErrors}">class="error"</c:if>>
			<label class="small"><spring:message code="member_contact_details.fax" text="Fax"/></label>
			<form:input cssClass="sText" path="presentFax"/>
			<span><form:errors path="presentFax" /></span>	
		</p>
		<c:set var="mobileErrors"><form:errors path="presentMobile"/></c:set>
		<p <c:if test="${not empty mobileErrors}">class="error"</c:if>>
			<label class="small"><spring:message code="member_contact_details.mobile" text="mobile"/></label>
			<form:input cssClass="sText" path="presentMobile"/>
			<span><form:errors path="presentMobile" /></span>	
		</p>
		
		
		<!-- Permanent Address Details -->
		<div class="fields">
			<h2><spring:message code="member_contact_details.permanent.address" text="Permanent Address Details"/></h2>
		</div>
		<p>
			<label class="small"><spring:message code="member_personal_details.sameasabove" text="Same as Present Address"/></label>
			<form:checkbox path="addressSameAsAbove" id="addressSameAsAbove" cssClass="sCheck" value="true"/>
		</p>
		<c:choose>
			  <c:when test="${memberContactDetails.addressSameAsAbove==true}">
			  	<div id="permanent_details" style="display:visible">
			  </c:when>
			  <c:otherwise>
			  	<div id="permanent_details" style="display:none">
			  </c:otherwise>
			</c:choose>
			<c:set var="permanentAddressErrors"><form:errors path="permanentAddress"/></c:set>
			<p <c:if test="${not empty permanentAddressErrors}">class="error"</c:if>>
				<label class="small"><spring:message code="member_contact_details.address" text="permanent Address"/></label>
				<form:input cssClass="sText large" path="permanentAddress"/>
				<span><form:errors path="permanentAddress" /></span>	
			</p>
			<c:set var="permanentcityErrors"><form:errors path="permanentCity"/></c:set>
			<p <c:if test="${not empty permanentcityErrors}">class="error"</c:if>>
				<label class="small"><spring:message code="member_contact_details.city" text="City"/></label>
				<form:input cssClass="sText large" path="permanentCity"/>
				<span><form:errors path="permanentCity" /></span>	
			</p>
			<c:set var="permanentStateErrors"><form:errors path="permanentState"/></c:set>
			<p <c:if test="${not empty permanentStateErrors}">class="error"</c:if>>
				<label class="small"><spring:message code="member_contact_details.state" text="State"/></label>
				<form:select path="permanentState" items="${permanentStates}" itemValue="id" itemLabel="name" id="permanentStates" cssClass="sSelect"/>
				<span><form:errors path="permanentState" /></span>	
			</p>
			<c:set var="permanentDistrictErrors"><form:errors path="permanentDistrict"/></c:set>
			<p <c:if test="${not empty permanentDistrictErrors}">class="error"</c:if>>
				<label class="small"><spring:message code="member_contact_details.district" text="District"/></label>
				<form:select path="permanentDistrict" items="${permanentDistricts}" itemValue="id" itemLabel="name" id="permanentDistricts" cssClass="sSelect"/>
				<span><form:errors path="permanentDistrict" /></span>
			</p>
			<c:set var="permanentTehsilErrors"><form:errors path="permanentTehsil"/></c:set>
			<p <c:if test="${not empty permanentTehsilErrors}">class="error"</c:if>>
				<label class="small"><spring:message code="member_contact_details.tehsil" text="Tehsil"/></label>
				<form:select path="permanentTehsil" items="${permanentTehsils}" itemValue="id" itemLabel="name" id="permanentTehsils" cssClass="sSelect"/>
				<span><form:errors path="permanentTehsil" /></span>
			</p>
			<c:set var="pinErrors"><form:errors path="permanentPinCode"/></c:set>
			<p <c:if test="${not empty pinErrors}">class="error"</c:if>>
				<label class="small"><spring:message code="member_contact_details.pincode" text="Pincode"/></label>
				<form:input cssClass="sText" path="permanentPinCode" size="6"/>
				<span><form:errors path="permanentPinCode" /></span>	
			</p>
		</div>
		<div class="fields">
			<h2></h2>
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