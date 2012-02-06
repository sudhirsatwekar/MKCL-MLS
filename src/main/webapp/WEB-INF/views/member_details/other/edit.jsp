<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
	<title>
		<spring:message code="member_information_system" text="Member Information System"/>
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<script type="text/javascript">
	$('document').ready(function(){
	
			var initialNoOfPositions=$('#noOfPositions').val();
			if(initialNoOfPositions==""){
				$('#noOfPositions').val(0);
			}
			function deletePosition(position){
				var id=$('#positionId'+position).val();
				if(id!=undefined){
					if(id!=""){
						$.ajax({
		   				    type: "DELETE",
		   				    url: 'member_position_details/'+id+'/delete',
		   				    success: function(json) {
		   				        alert("Record deleted successfully");
		   				    }
		   				});
					}
				}
				$('#li'+position).detach();
				
			}
			function addPosition(){
					var totalPositions=parseInt($('#noOfPositions').val());
					var newPosition=totalPositions+1;
					var text="<li class='name' id='li"+newPosition+"'>"+
					"<span>"+
					"<label class='desc'><spring:message code='member_other_details.positionHeldPeriod' text='Position Held(Period)'/>&nbsp;*</label>"+	
					"<input type='text' id='positionPeriod"+newPosition+"' name='positionPeriod"+newPosition+"' class='field text'>"+	
					"</span>"+	
					"<span>"+
					"<label class='desc'><spring:message code='member_other_details.positionHeldDetails' text='Position Held(Details)'/>&nbsp;*</label>"+		
					"<textarea id='positionDetail"+newPosition+"' name='positionDetail"+newPosition+"' class='field textarea'>\</textarea>"+
					"</span>"+
					"<span>"+
					"<input id='deleteBt"+newPosition+"' type='button' onclick='deletePosition("+newPosition+")' value='<spring:message code='generic.delete' text='Delete'/>'/> "+
					"</span>"+
					"</li>";
					$('#newPositionsDiv').append(text);
					$('#noOfPositions').val(newPosition);		
			}		
		
			initControls();
		    $(':input:visible:not([readonly]):first').focus();
    		 
	});     
	</script>
</head>
<body>
<div class="fields clearfix">
<form:form action="member_other_details" method="PUT" modelAttribute="memberOtherDetails">

	<%@ include file="/common/info.jsp" %>	
	<h2><spring:message code="member_other_details.edit.heading" text="Other Details"/>
		[Id:&nbsp;${memberOtherDetails.id}]
	</h2>
	<c:set var="noofTermsErrors"><form:errors path="noOfTerms"/></c:set>
	<p <c:if test="${not empty noofTermsErrors}">class="error"</c:if>>
		<label class="small"><spring:message code="member_other_details.noofTerms" text="No. of Terms"/></label>
		<form:input cssClass="sText tiny" path="noOfTerms"/>
		<span><form:errors path="noOfTerms" /></span>	
	</p>	
	<div class="fields">
		<h2><spring:message code="member_other_details.positions" text="Position Details"/></h2>
		<form:textarea cssClass="wysiwyg sTextarea" path="positionDetails" rows="5" cols="78"/>
	</div>
	<div class="fields">
		<h2><spring:message code="member_other_details.socioCulturalActivities" text="Social and Cultural Activities"/></h2>
		<form:textarea cssClass="wysiwyg sTextarea" path="socioCulturalActivities" rows="5" cols="78"/>
	</div>
	<div class="fields">
		<h2><spring:message code="member_other_details.literaryArtisticScAccomplishments" text="Literary Artistic & Scientific Accompilshment"/></h2>
		<form:textarea cssClass="wysiwyg sTextarea" path="literaryArtisticScAccomplishment" rows="5" cols="78"/>
	</div>
	<div class="fields">
		<h2><spring:message code="member_other_details.booksPublished" text="Books Published"/></h2>
		<form:textarea cssClass="wysiwyg sTextarea" path="booksPublished" rows="5" cols="78"/>
	</div>
	<div class="fields">
		<h2><spring:message code="member_other_details.specialInterests" text="Special Interests"/></h2>
		<form:textarea cssClass="wysiwyg sTextarea" path="specialInterests" rows="5" cols="78"/>
	</div>
	<div class="fields">
		<h2><spring:message code="member_other_details.pastimeRecreation" text="Favorite Pastime and Recreation"/></h2>
		<form:textarea cssClass="wysiwyg sTextarea" path="pastimeRecreation" rows="5" cols="78"/>
	</div>
	<div class="fields">
		<h2><spring:message code="member_other_details.sportsClubs" text="Sports and Clubs"/></h2>
		<form:textarea cssClass="wysiwyg sTextarea" path="sportsClubs" rows="5" cols="78"/>
	</div>
	<div class="fields">
		<h2><spring:message code="member_other_details.countriesVisited" text="Countries Visited"/></h2>
		<form:textarea cssClass="wysiwyg sTextarea" path="countriesVisited" rows="5" cols="78"/>
	</div>
	<div class="fields">
		<h2><spring:message code="member_other_details.experience" text="Experience"/></h2>
		<form:textarea cssClass="wysiwyg sTextarea" path="experience" rows="5" cols="78"/>
	</div>
	<div class="fields">
		<h2><spring:message code="member_other_details.otherInfo" text="Other Information"/></h2>
		<form:textarea cssClass="wysiwyg sTextarea" path="otherInfo" rows="5" cols="78"/>
	</div>
	<h2></h2>
	<p class="tright">
		<input id="submit" type="submit" value="<spring:message code='generic.submit' text='Submit'/>" class="butDef">
	</p>
	<form:hidden path="id"></form:hidden>
	<form:hidden path="locale"></form:hidden>	
	<form:hidden path="version"></form:hidden>
	<input type="hidden" id="noOfPositions" name="noOfPositions" value="${noOfPositions}"></input>
</form:form>
</div>
</body>
</html>