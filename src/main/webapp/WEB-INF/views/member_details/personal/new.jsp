<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
	<title>
	<spring:message code="member_information_system" text="Member Information System"/>
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>	
	<script type="text/javascript">
	$('document').ready(function(){	
		initControls();
		$('#key').val('');
		$("#constituency").autocomplete({
			source: function( request, response ) {
				$.ajax({
					url: "ref/constituencies.json",
					dataType: "json",
					data: {
						featureClass: "P",
						style: "full",
						maxRows: 12,
						term: request.term
					},
					success: function( data ) {
						response( $.map(data.results, function( item ) {
							return {
								label: item.name,
								value: item.id
							};
						}));
					}
				});
			},
			minLength: 2,
			close: function( event, ui ) {
				$("#constituency").val($(this).val());
				if($(this).val()!=''){
					$.getJSON('ref/data/'+this.value+'/districts', function(data) {
						 $.each(data, function(key, item) {
							 $('#constituency_details').html('[' + item.name + ':' + item.state.name + ']');
						 });
					});
				}
				else {
					$('#constituency_details').html('');
				}
			}
		});
		
		$('#maritalStatus').change(function(){
			$('#marital_details').toggle('slow');
		});
		
	});		
</script>
</head>
<body>
<div class="fields clearfix">
<form:form  action="member_personal_details" method="POST" modelAttribute="memberPersonalDetails">
	<%@ include file="/common/info.jsp" %>
	<h2><spring:message code="memberPersonalDetails.new.heading" text="Enter Details"/>
		[Id:&nbsp;New]
	</h2>
		 <p>
		 	 <label class="small"><spring:message code="member_personal_details.photo.label" text="Upload Photo"/></label>
			 <jsp:include page="/common/file_upload.jsp">
			 	<jsp:param name="fileid" value="photo" />
			 </jsp:include>
			 <form:errors path="photo" cssClass="field_error" />
		 </p>
		<c:set var="titleErrors"><form:errors path="title"/></c:set>
		<p <c:if test="${not empty titleErrors}">class="error"</c:if>>
			<label class="small"><spring:message code="member_personal_details.title" text="Title"/></label>
			<form:select cssClass="sSelect" path="title" items="${titles}" itemValue="id" itemLabel="name" />
			<span><form:errors path="title" /></span>	
		</p>
		<c:set var="firstNameErrors"><form:errors path="firstName"/></c:set>
		<p <c:if test="${not empty firstNameErrors}">class="error"</c:if>>
			<label class="small"><spring:message code="member_personal_details.firstName" text="First Name"/></label>
			<form:input cssClass="sText large" path="firstName"/>
			<span><form:errors path="firstName" /></span>	
		</p>
		<c:set var="middleNameErrors"><form:errors path="middleName"/></c:set>
		<p <c:if test="${not empty middleNameErrors}">class="error"</c:if>>
			<label class="small"><spring:message code="member_personal_details.middleName" text="Middle Name"/></label>
			<form:input cssClass="sText large" path="middleName"/>
			<span><form:errors path="middleName" /></span>	
		</p>
		<c:set var="lastNameErrors"><form:errors path="lastName"/></c:set>
		<p <c:if test="${not empty lastNameErrors}">class="error"</c:if>>
			<label class="small"><spring:message code="member_personal_details.lastName" text="Last Name"/></label>
			<form:input cssClass="sText large" path="lastName"/>
			<span><form:errors path="lastName" /></span>	
		</p>
		<c:set var="aliasErrors"><form:errors path="alias"/></c:set>
		<p <c:if test="${not empty aliasErrors}">class="error"</c:if>>
			<label class="small"><spring:message code="member_personal_details.alias" text="Member Alias"/></label>
			<form:input cssClass="sText large" path="alias"/>
			<label class="small"><spring:message code="member_personal_details.enableAliasing" text="Alias Enabled"/></label>
			<form:checkbox cssClass="sCheck" path="enableAliasing" value="true" id="enableAliasing"/>
			<span><form:errors path="alias" /><form:errors path="enableAliasing" /></span>	
		</p>
		<c:set var="genderErrors"><form:errors path="gender"/></c:set>
		<p <c:if test="${not empty genderErrors}">class="error"</c:if>>
			<label class="small"><spring:message code="member_personal_details.gender" text="Gender"/></label>
			<form:select path="gender" cssClass="sSelect">
				<form:option value="male">Male</form:option>
				<form:option value="male">Female</form:option>				
	        </form:select>
			<span><form:errors path="gender" /></span>	
		</p>
		<c:set var="birthDateErrors"><form:errors path="birthDate"/></c:set>
		<p <c:if test="${not empty birthDateErrors}">class="error"</c:if>>
			<label class="small"><spring:message code="member_personal_details.birthDate" text="Birth Date"/></label>
			<form:input cssClass="date sText" path="birthDate"/>
			<span><form:errors path="birthDate" /></span>	
		</p>
		<c:set var="birthPlaceErrors"><form:errors path="placeOfBirth"/></c:set>
		<p <c:if test="${not empty birthPlaceErrors}">class="error"</c:if>>
			<label class="small"><spring:message code="member_personal_details.placeOfBirth" text="Birth Place"/></label>
			<form:input cssClass="sText" path="placeOfBirth"/>
			<span><form:errors path="placeOfBirth" /></span>	
		</p>
		<c:set var="constituencyErrors"><form:errors path="constituency"/></c:set>
		<p <c:if test="${not empty constituencyErrors}">class="error"</c:if>>
			<label class="small"><spring:message code="member_personal_details.constituency" text="Constituency"/></label>
			<form:input class="sText" path="constituency" />
			<span style="display:inline" id="constituency_details"></span>
			<span><form:errors path="constituency" /></span>	
		</p>
		
		<c:set var="partyNameErrors"><form:errors path="partyName"/></c:set>
		<p <c:if test="${not empty partyNameErrors}">class="error"</c:if>>
			<label class="small"><spring:message code="member_personal_details.partyName" text="Party Name"/></label>
			<form:select path="partyName" items="${parties}" itemValue="id" itemLabel="name" cssClass="sSelect"/>
			<span><form:errors path="partyName" /></span>	
		</p>
		<p class="sep"></p>
		<h2>Family Details</h2>
			<c:set var="fatherNameErrors"><form:errors path="fatherName"/></c:set>
			<p <c:if test="${not empty fatherNameErrors}">class="error"</c:if>>
				<label class="small"><spring:message code="member_personal_details.fatherName" text="Father Name"/></label>
				<form:input cssClass="sText large" path="fatherName"/>
				<span><form:errors path="fatherName" /></span>	
			</p>
			<c:set var="motherNameErrors"><form:errors path="motherName"/></c:set>
			<p <c:if test="${not empty motherNameErrors}">class="error"</c:if>>
				<label class="small"><spring:message code="member_personal_details.motherName" text="Mother Name"/></label>
				<form:input cssClass="sText large" path="motherName"/>
				<span><form:errors path="motherName" /></span>	
			</p>
			<c:set var="maritalStatusErrors"><form:errors path="maritalStatus"/></c:set>
			<p <c:if test="${not empty maritalStatusErrors}">class="error"</c:if>>
				<label class="small"><spring:message code="member_personal_details.maritalStatus" text="Marital Status"/></label>
				<form:checkbox cssClass="sCheck" path="maritalStatus" value="true" id="maritalStatus" />
				<span><form:errors path="maritalStatus" /></span>	
			</p>
			<c:choose>
			  <c:when test="${_maritalStatus=='on'}">
			  	<div id="marital_details" style="display:visible">
			  </c:when>
			  <c:otherwise>
			  	<div id="marital_details" style="display:none">
			  </c:otherwise>
			</c:choose>
				<c:set var="marriageDateErrors"><form:errors path="marriageDate"/></c:set>
				<p <c:if test="${not empty marriageDateErrors}">class="error"</c:if>>
					<label class="small"><spring:message code="member_personal_details.marriageDate" text="Marital Date"/></label>
					<form:input cssClass="sText small" path="marriageDate"/>
					<span><form:errors path="marriageDate" /></span>	
				</p>
				<c:set var="spouseNameErrors"><form:errors path="spouseName"/></c:set>
				<p <c:if test="${not empty spouseNameErrors}">class="error"</c:if>>
					<label class="small"><spring:message code="member_personal_details.spouseName" text="Spouse Name"/></label>
					<form:input cssClass="sText large" path="spouseName"/>
					<span><form:errors path="spouseName" /></span>	
				</p>
				<c:set var="noOfSonsErrors"><form:errors path="noOfSons"/></c:set>
				<p <c:if test="${not empty noOfSonsErrors}">class="error"</c:if>>
					<label class="small"><spring:message code="member_personal_details.noOfSons" text="No. of Sons"/></label>
					<form:input cssClass="sText tiny" path="noOfSons"/>
					<span><form:errors path="noOfSons" /></span>	
				</p>
				<c:set var="noOfDaughterErrors"><form:errors path="noOfDaughter"/></c:set>
				<p <c:if test="${not empty noOfDaughterErrors}">class="error"</c:if>>
					<label class="small"><spring:message code="member_personal_details.noOfDaughter" text="No. of Daughter"/></label>
					<form:input cssClass="sText tiny" path="noOfDaughter"/>
					<span><form:errors path="noOfDaughter" /></span>	
				</p>
			</div>
		<div class="fields">
			<h2><spring:message code="member_personal_details.educationalQualification" text="Educational Qualification"/></h2>
			<form:textarea cssClass="wysiwyg sTextarea" path="educationalQualification" rows="3" cols="78"/>
		</div>
		<div class="fields">
			<h2><spring:message code="member_personal_details.profession" text="Educational Qualification"/></h2>
			<form:textarea cssClass="wysiwyg sTextarea" path="profession" rows="3" cols="78"/>
		</div>
		<div class="fields">
			<h2></h2>
			<p class="tright">
				<input id="submit" type="submit" value="<spring:message code='generic.submit' text='Submit'/>" class="butDef">
			</p>
		</div>
	<form:hidden path="locale" />
	<form:hidden path="version" />
	<form:hidden path="id"/>	
</form:form>
</div>
<input type="hidden" id="refreshSe" value="<%=session.getAttribute("refresh")%>">		
<input type="hidden" id="const_name" value="${constituency.name}">
<input type="hidden" id="const_id" value="${constituency.id}">	
<input type="hidden" id="photo_size" value="${photoSize}">	
<input type="hidden" id="photo_ext" value="${photoExt}">
<input type="hidden" id="positionList" value="${positionList}">			
<input type="hidden" id="dateformat" value="${dateformat}">			
</body>
</html>