<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
	<title>
	<spring:message code="member_information_system" text="Member Information System"/>
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>
<div class="commandbar">
		<div class="commandbarContent">
			<a  href="#" class="mis" id="member_personal_details"><spring:message code="mis.personal.new.link" text="Personal Details"/></a> |
			<a  href="#" class="mis" id="member_contact_details"><spring:message code="mis.contact.new.link" text="Contact Details"/></a> |
			<a  href="#" class="mis" id="member_other_details"><spring:message code="mis.other.new.link" text="Other Details"/></a>
		</div>
</div>
<form:form cssClass="wufoo" action="member_other_details" method="PUT" modelAttribute="memberOtherDetails">

	<div class="info">
		 <h2><spring:message code="member_other_details.edit.heading" text="Edit Other Details"/></h2>		
		<div style="background-color:#C1CDCD; ;padding: 3px"><spring:message code="generic.mandatory.label" text="All fields marked * are mandatory"/></div>
	</div>
	
	<ul>
	
		<li id="${fields.id.position}" class="${fields.id.visible}">
		<label class="desc"><spring:message code="generic.id" text="Id"/><span><spring:message code="${fields.id.hint}" text=""/></span>&nbsp;<c:if test="${fields.id.mandatory=='MANDATORY'}">*</c:if></label>
			<div>
				<form:input cssClass="field text medium" path="id" readonly="true" /> 
			</div>
		</li>
							
		<li id="${fields.locale.position}" class="${fields.locale.visible}">
		<label class="desc"><spring:message code="generic.locale" text="Select Language"/><span><spring:message code="${fields.locale.hint}" text=""/></span>&nbsp;<c:if test="${fields.locale.mandatory=='MANDATORY'}">*</c:if></label>
			<div>
				<form:select cssClass="field select medium" path="locale"> 
					<form:option value="en"><spring:message code="generic.lang.english" text="English"/></form:option>
					<form:option value="hi_IN"><spring:message code="generic.lang.hindi" text="Hindi"/></form:option>
					<form:option value="mr_IN"><spring:message code="generic.lang.marathi" text="Marathi"/></form:option>
				</form:select>
			</div>
		</li>
				
		<li id="${fields.noofTerms.position}" class="${fields.noofTerms.visible}">
		<label class="desc"><spring:message code="member_other_details.noofTerms" text="No. of Terms"/><span><spring:message code="${fields.noOfTerms.hint}" text=""/></span>&nbsp;<c:if test="${fields.noofTerms.mandatory=='MANDATORY'}">*</c:if></label>
			<div>
			<form:input cssClass="integer field text medium" path="noOfTerms"/><form:errors path="noOfTerms" cssClass="field_error" />	
		   </div>
		</li>		
		
		<li id="${fields.id.position}" class="${fields.id.visible}">
		<input id="addBt"  type="button" value="<spring:message code='generic.add' text='Add Position'/>" onclick="addPosition()">
		</li>				
		<c:choose>		
		<c:when test="${!(empty positions)}">		
		<c:set  var="count" value="1"></c:set>
		<c:forEach items="${positions}" var="i">
		<li class="name ${fields.positionHeldPeriod.visible}" id="li${count}">
		<span>
		<label class="desc"><spring:message code="member_other_details.positionHeldPeriod" text="Position Hels(Period)"/><span><spring:message code="${fields.positionHeldPeriod.hint}" text=""/></span>&nbsp;<c:if test="${fields.positionHeldPeriod.mandatory=='MANDATORY'}">*</c:if></label>	
		<input type="hidden" id="positionId${count}" name="positionId${count}" value="${i.id}">
		<input type="text" id="positionPeriod${count}" name="positionPeriod${count}" value="${i.period}" class="field text">	
		</span>	
		<span>
		<label class="desc"><spring:message code="member_other_details.positionHeldDetails" text="Position Held(Details)"/><span><spring:message code="${fields.positionHeldDetails.hint}" text=""/></span>&nbsp;<c:if test="${fields.positionHeldDetails.mandatory=='MANDATORY'}">*</c:if></label>		
		<textarea id="positionDetail${count}" name="positionDetail${count}" class="field textarea">${i.details}</textarea>
		</span>
		<span>
		<input id="deleteBt${count}"  type="button"  value="<spring:message code='generic.delete' text='Delete'/>" onclick="deletePosition(${count})">
		</span>
		</li>	
		<c:set value="${count+1}" var="count"/>
		</c:forEach>
		<div id="newPositionsDiv"></div>
		</c:when>
		<c:otherwise>
		<div id="newPositionsDiv"></div>
		</c:otherwise>
		</c:choose>	
					
		<li id="${fields.socioCulturalActivities.position}" class="${fields.socioCulturalActivities.visible}">
		<label class="desc"><spring:message code="member_other_details.socioCulturalActivities" text="Social and Cultural Activities"/><span><spring:message code="${fields.socioCulturalActivities.hint}" text=""/></span>&nbsp;<c:if test="${fields.socioCulturalActivities.mandatory=='MANDATORY'}">*</c:if></label>
			<div>
				<form:textarea cssClass="field textarea small" path="socioCulturalActivities"/><form:errors path="socioCulturalActivities" cssClass="field_error" />	
			</div>
		</li>
		
		<li id="${fields.literaryArtisticScAccomplishment.position}" class="${fields.literaryArtisticScAccomplishment.visible}">
		<label class="desc"><spring:message code="member_other_details.literaryArtisticScAccomplishments" text="Literary Artistic & Scientific Accompilshment"/><span><spring:message code="${fields.literaryArtisticScAccomplishment.hint}" text=""/></span>&nbsp;<c:if test="${fields.id.mandatory=='MANDATORY'}">*</c:if></label>
			<div>
				<form:textarea cssClass="field textarea small" path="literaryArtisticScAccomplishment"/><form:errors path="literaryArtisticScAccomplishment" cssClass="field_error" />	
			</div>
		</li>
		
		<li id="${fields.booksPublished.position}" class="${fields.booksPublished.visible}">
		<label class="desc"><spring:message code="member_other_details.booksPublished" text="Books Published"/><span><spring:message code="${fields.booksPublished.hint}" text=""/></span>&nbsp;<c:if test="${fields.booksPublished.mandatory=='MANDATORY'}">*</c:if></label>
			<div>
				<form:textarea cssClass="field textarea small" path="booksPublished"/><form:errors path="booksPublished" cssClass="field_error" />	
			</div>
		</li>
		
		<li id="${fields.specialInterests.position}" class="${fields.specialInterests.visible}">
		<label class="desc"><spring:message code="member_other_details.specialInterests" text="Special Interests"/><span><spring:message code="${fields.specialInterests.hint}" text=""/></span>&nbsp;<c:if test="${fields.specialInterests.mandatory=='MANDATORY'}">*</c:if></label>
			<div>
				<form:textarea cssClass="field textarea small" path="specialInterests"/><form:errors path="specialInterests" cssClass="field_error" />	
			</div>
		</li>
		
		<li id="${fields.pastimeRecreation.position}" class="${fields.pastimeRecreation.visible}">
		<label class="desc"><spring:message code="member_other_details.pastimeRecreation" text="Favorite Pastime and Recreation"/><span><spring:message code="${fields.pastimeRecreation.hint}" text=""/></span>&nbsp;<c:if test="${fields.pastimeRecreation.mandatory=='MANDATORY'}">*</c:if></label>
			<div>
				<form:textarea cssClass="field textarea small" path="pastimeRecreation"/><form:errors path="pastimeRecreation" cssClass="field_error" />	
			</div>
		</li>
		
		<li id="${fields.sportsClubs.position}" class="${fields.sportsClubs.visible}">
		<label class="desc"><spring:message code="member_other_details.sportsClubs" text="Sports and Clubs"/><span><spring:message code="${fields.sportsClubs.hint}" text=""/></span>&nbsp;<c:if test="${fields.sportsClubs.mandatory=='MANDATORY'}">*</c:if></label>
			<div>
				<form:textarea cssClass="field textarea small" path="sportsClubs"/><form:errors path="sportsClubs" cssClass="field_error" />	
			</div>
		</li>
		
		<li id="${fields.countriesVisited.position}" class="${fields.countriesVisited.visible}">
		<label class="desc"><spring:message code="member_other_details.countriesVisited" text="Countries Visited"/><span><spring:message code="${fields.countriesVisited.hint}" text=""/></span>&nbsp;<c:if test="${fields.countriesVisited.mandatory=='MANDATORY'}">*</c:if></label>
			<div>
				<form:textarea cssClass="field textarea small" path="countriesVisited"/><form:errors path="countriesVisited" cssClass="field_error" />	
			</div>
		</li>
		
		<li id="${fields.experience.position}" class="${fields.experience.visible}">
		<label class="desc"><spring:message code="member_other_details.experience" text="Experience"/><span><spring:message code="${fields.experience.hint}" text=""/></span>&nbsp;<c:if test="${fields.experience.mandatory=='MANDATORY'}">*</c:if></label>
			<div>
				<form:textarea cssClass="field textarea small" path="experience"/><form:errors path="experience" cssClass="field_error" />	
			</div>
		</li>
		
		<li id="${fields.otherInfo.position}" class="${fields.otherInfo.visible}">
		<label class="desc"><spring:message code="member_other_details.otherInfo" text="Other Information"/><span><spring:message code="${fields.otherInfo.hint}" text=""/></span>&nbsp;<c:if test="${fields.otherInfo.mandatory=='MANDATORY'}">*</c:if></label>
			<div>
				<form:textarea cssClass="field textarea small" path="otherInfo"/><form:errors path="otherInfo" cssClass="field_error" />	
			</div>
		</li>	
			
		<li class="buttons">
			<input id="saveForm" class="btTxt" type="submit" value="<spring:message code='generic.submit' text='Update'/>" ></input>
		</li>
		
	</ul>	
	<form:hidden path="version"></form:hidden>		
	<input type="hidden" id="noOfPositions" name="noOfPositions" value="${noOfPositions}"></input>
	</form:form>
	<div id="info" style="visibility: hidden;">
	<c:choose>
	<c:when test="${(!empty type) && (!empty msg)}">
	<input id="info_type" type="text"  value="${type}">
	<input id="info_msg" type="hidden" value="<spring:message code='${msg}'/>">
	</c:when>
	<c:when test="${(!empty param.type) && (!empty param.msg)}">
	<input id="info_type" type="hidden"  value="${param.type}">
	<input id="info_msg" type="hidden" value="<spring:message code='${param.msg}'/>">
	</c:when>
	<c:otherwise>
	<input id="info_type"  type="text" value="">
	<input id="info_msg" type="hidden" value="">
	</c:otherwise>
	</c:choose>	
	</div>	
	<input type="hidden" id="refreshSe" value="<%=session.getAttribute("refresh")%>">
	<script type="text/javascript">
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

	//function for sorting fields according to their positions
	//important thing is all fields to be sorted must be placed inside a li 
	
	$('li').sortElements(function(a, b){
		if($(a).attr("id")!=undefined&&$(b).attr("id")!=undefined){
			 return parseInt($(a).attr("id")) > parseInt($(b).attr("id")) ? 1 : -1;
		}
	});

	$('.mis').click(function(event){
		var id=$('#id').val();
		if(id!=undefined && id!=""){
			$.get($(this).attr('id')+'/'+id+'/edit', function(data) {
		  		$('#contentPanel').html(data);
			});
		}			
	return false;			
	})	

	initControls();
	
    $(':input:visible:not([readonly]):first').focus();
    		 
	$("form").submit(function(e){	
		//e.preventDefault();			
		var count=0;
		$('.MANDATORY').each(function(){
		if($(this).val()==""){
			$(this).after("<span class='field_error'>Required</span>");					
			count++;
			return false;
		}
	})
	if(count>0){
	
	}
	else{
		$.post($('form').attr('action'),  
	            $("form").serialize(),  
	            function(data){	
   				$('.contentPanel').html(data);	
   				$('#refresh').val($('#refreshSe').val());
   				if($('#info_type').val()=='success'){			   				
	   	   	   		$("#grid").trigger("reloadGrid");		   				
				}						   				  					   						   					
	            }                                         
        );  
	}				
	        return false;  					
	})
	     
	if($('#info_msg').val().length!=0){
		$().toastmessage('showToast',{
			text:$('#info_msg').val(),
			type:$('#info_type').val(),
			stayTime:3000,
			inEffectDuration:600
		});
	}
	</script>
</body>
</html>