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
<form:form cssClass="wufoo" action="member_contact_details" method="PUT" modelAttribute="memberContactDetails">
	<div class="info">
		 <h2><spring:message code="member_contact_details.edit.heading" text="Edit Contact Details"/></h2>		
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
		<label class="desc"><spring:message code="generic.locale" text="Select language"/><span><spring:message code="${fields.locale.hint}" text=""/></span>&nbsp;<c:if test="${fields.locale.mandatory=='MANDATORY'}">*</c:if></label>
			<div>
				<form:select cssClass="field select medium" path="locale"> 
					<form:option value="en"><spring:message code="generic.lang.english" text="English"/></form:option>
					<form:option value="hi_IN"><spring:message code="generic.lang.hindi" text="Hindi"/></form:option>
					<form:option value="mr_IN"><spring:message code="generic.lang.marathi" text="Marathi"/></form:option>
				</form:select>
			</div>
		</li>
		
		<li id="${fields.email.position}" class="${fields.email.visible}">
		<label class="desc"><spring:message code="member_contact_details.email" text="Email"/><span><spring:message code="${fields.email.hint}" text=""/></span>&nbsp;<c:if test="${fields.email.mandatory=='MANDATORY'}">*</c:if></label>
			<div>
				<form:input cssClass="field text medium" path="email"/><form:errors path="email" cssClass="field_error" />	
			</div>
		</li>
				
		<li class="complex ${fields.presentAddress.visible}" id="${fields.presentAddress.position}" >
		<label class="desc"><spring:message code="member_contact_details.present.address" text="Present Address"/><span><spring:message code="${fields.id.hint}" text=""/></span>&nbsp;<c:if test="${fields.id.mandatory=='MANDATORY'}">*</c:if></label>
		<div>
		<span class="full addr1">
		<label><spring:message code="member_contact_details.address" text="Address"/><span><spring:message code="${fields.presentAddress.hint}" text=""/></span>&nbsp;<c:if test="${fields.presentAddress.mandatory=='MANDATORY'}">*</c:if></label>
		<form:input cssClass="field text addr" path="presentAddress"/><form:errors path="presentAddress" cssClass="field_error" />	
		</span>
		<span class="left city">
		<label><spring:message code="member_contact_details.city" text="City"/><span><spring:message code="${fields.presentCity.hint}" text=""/></span>&nbsp;<c:if test="${fields.presentCity.mandatory=='MANDATORY'}">*</c:if></label>
		<form:input cssClass="field text addr" path="presentCity"/><form:errors path="presentAddress" cssClass="field_error" />	
		</span>
		</div>
		</li>
		
		<li class="leftThird ${fields.presentState.visible}" id="${fields.presentState.position}">
		<label><spring:message code="member_personal_details.state" text="State"/><span><spring:message code="${fields.presentState.hint}" text=""/></span>&nbsp;<c:if test="${fields.presentState.mandatory=='MANDATORY'}">*</c:if></label>
		<div>
		<form:select path="presentState" items="${presentStates}" itemValue="id" itemLabel="name" id="presentStates" cssClass="field select medium">
	    </form:select><form:errors path="presentState" cssClass="field_error" />				
		</div>
		</li>
		
		<li class="middleThird ${fields.presentDistrict.visible}" id="${fields.presentDistrict.position}">
		<div id="presentDistrict">
		<label><spring:message code="member_personal_details.district" text="District"/><span><spring:message code="${fields.presentDistrict.hint}" text=""/></span>&nbsp;<c:if test="${fields.presentDistrict.mandatory=='MANDATORY'}">*</c:if></label>
		<form:select path="presentDistrict" items="${presentDistricts}" itemValue="id" itemLabel="name" id="presentDistricts" cssClass="field select medium">
	    </form:select><form:errors path="presentDistrict" cssClass="field_error" />				
		</div>
		</li>
		
		<li class="rightThird ${fields.presentTehsil.visible}" id="${fields.presentTehsil.position}">
		<div id="presentTehsil">
		<label><spring:message code="member_personal_details.tehsil" text="Tehsil"/><span><spring:message code="${fields.presentTehsil.hint}" text=""/></span>&nbsp;<c:if test="${fields.presentTehsil.mandatory=='MANDATORY'}">*</c:if></label>
		<form:select path="presentTehsil" items="${presentTehsils}" itemValue="id" itemLabel="name" id="presentTehsils" cssClass="field select medium">
	    </form:select><form:errors path="presentTehsil" cssClass="field_error" />							
		</div>		
		</li>
		
		<li class="complex ${fields.presentPinCode.visible}" id="${fields.presentPinCode.position}">
		<div>
		<span class="left pin">
		<label ><spring:message code="member_personal_details.pincode" text="Pincode"/><span><spring:message code="${fields.presentPinCode.hint}" text=""/></span>&nbsp;<c:if test="${fields.presentPinCode.mandatory=='MANDATORY'}">*</c:if></label>
		<form:input cssClass="field text addr" path="presentPinCode" size="6"/><form:errors path="presentPinCode" cssClass="field_error" />	
		</span>	
		<span class="full addr1">
		<label><spring:message code="member_contact_details.telephone" text="Telephone"/><span><spring:message code="${fields.presentTelephone.hint}" text=""/></span>&nbsp;<c:if test="${fields.presentTelephone.mandatory=='MANDATORY'}">*</c:if></label>
		<form:input cssClass="field text addr" path="presentTelephone"/><form:errors path="presentTelephone" cssClass="field_error" />	
		</span>
		<span class="full addr1">
		<label><spring:message code="member_contact_details.fax" text="Fax"/><span><spring:message code="${fields.presentFax.hint}" text=""/></span>&nbsp;<c:if test="${fields.presentFax.mandatory=='MANDATORY'}">*</c:if></label>
		<form:input cssClass="field text addr" path="presentFax"/><form:errors path="presentFax" cssClass="field_error" />	
		</span>
		<span class="full addr1">
		<label><spring:message code="member_contact_details.mobile" text="Mobile"/><span><spring:message code="${fields.presentMobile.hint}" text=""/></span>&nbsp;<c:if test="${fields.presentMobile.mandatory=='MANDATORY'}">*</c:if></label>
		<form:input cssClass="field text addr" path="presentMobile"/><form:errors path="presentMobile" cssClass="field_error" />	
		</span>	
		</div>
		</li>	
		
		<li class="${fields.addressSameAsAbove.visible}" id="${fields.addressSameAsAbove.position}">
		<label><spring:message code="member_personal_details.sameasabove" text="Same as Present Address"/><span><spring:message code="${fields.addressSameAsAbove.hint}" text=""/></span>&nbsp;<c:if test="${fields.addressSameAsAbove.mandatory=='MANDATORY'}">*</c:if></label>
		<form:checkbox path="addressSameAsAbove" id="addressSameAsAbove" cssClass="checkbox" value="true"/>
	    <form:errors path="addressSameAsAbove" cssClass="field_error" />							
	    </li>
			
		<li class="complex ${fields.permanentAddress.visible}" id="${fields.permanentAddress.position}">
		<div class="permanent">
		<label class="desc"><spring:message code="member_contact_details.permanent.address" text="Permanent Address"/><span><spring:message code="${fields.permanentAddress.hint}" text=""/></span>&nbsp;<c:if test="${fields.permanentAddress.mandatory=='MANDATORY'}">*</c:if></label>
		<div>
		<span class="full addr1">
		<label><spring:message code="member_contact_details.address" text="Address"/><span><spring:message code="${fields.permanentAddress.hint}" text=""/></span>&nbsp;<c:if test="${fields.permanentAddress.mandatory=='MANDATORY'}">*</c:if></label>
		<form:input cssClass="field text addr" path="permanentAddress" id="permanentAddress"/><form:errors path="permanentAddress" cssClass="field_error" />	
		</span>
		<span class="left city">
		<label><spring:message code="member_contact_details.city" text="City"/><span><spring:message code="${fields.permanentCity.hint}" text=""/></span>&nbsp;<c:if test="${fields.permanentCity.mandatory=='MANDATORY'}">*</c:if></label>
		<form:input cssClass="field text addr" path="permanentCity" id="permanentCity"/><form:errors path="permanentCity" cssClass="field_error" />	
		</span>
		</div>
		</div>
		</li>
		
		<li class="leftThird ${fields.permanentState.visible}" id="${fields.permanentState.position}">
		<div class="permanent">
		<label><spring:message code="member_personal_details.state" text="State"/><span><spring:message code="${fields.permanentState.hint}" text=""/></span>&nbsp;<c:if test="${fields.permanentState.mandatory=='MANDATORY'}">*</c:if></label>
		<div>
		<form:select path="permanentState" items="${permanentStates}" itemValue="id" itemLabel="name" id="permanentStates" cssClass="field select medium">
	    </form:select><form:errors path="permanentState" cssClass="field_error" />				
		</div>
		</div>
		</li>
		
		<li class="middleThird ${fields.permanentDistrict.visible}" id="${fields.permanentDistrict.position}">
		<div id="permanentDistrict" class="permanent">
		<label><spring:message code="member_personal_details.district" text="District"/><span><spring:message code="${fields.permanentDistrict.hint}" text=""/></span>&nbsp;<c:if test="${fields.permanentDistrict.mandatory=='MANDATORY'}">*</c:if></label>
		<form:select path="permanentDistrict" items="${permanentDistricts}" itemValue="id" itemLabel="name" id="permanentDistricts" cssClass="field select medium">
	    </form:select><form:errors path="permanentDistrict" cssClass="field_error" />				
		</div>
		</li>
		
		<li class="rightThird ${fields.permanentTehsil.visible}" id="${fields.permanentTehsil.position}">
		<div id="permanentTehsil" class="permanent">
		<label><spring:message code="member_personal_details.tehsil" text="Tehsil"/><span><spring:message code="${fields.permanentTehsil.hint}" text=""/></span>&nbsp;<c:if test="${fields.permanentTehsil.mandatory=='MANDATORY'}">*</c:if></label>
		<form:select path="permanentTehsil" items="${permanentTehsils}" itemValue="id" itemLabel="name" id="permanentTehsils" cssClass="field select medium">
	    </form:select><form:errors path="permanentTehsil" cssClass="field_error" />							
		</div>
		</li>
		
		<li class="complex ${fields.permanentPinCode.visible}" id="${fields.permanentPinCode.position}">
		<div class="permanent">
		<span class="left pin">
		<label ><spring:message code="member_personal_details.pincode" text="Pincode"/><span><spring:message code="${fields.permanentPinCode.hint}" text=""/></span>&nbsp;<c:if test="${fields.permanentPinCode.mandatory=='MANDATORY'}">*</c:if></label>
		<form:input cssClass="field text addr" path="permanentPinCode" size="6" id="permanentPinCode"/><form:errors path="permanentPinCode" cssClass="field_error" />	
		</span>	
		<span class="full addr1">
		<label><spring:message code="member_contact_details.telephone" text="Telephone"/><span><spring:message code="${fields.permanentTelephone.hint}" text=""/></span>&nbsp;<c:if test="${fields.permanentTelephone.mandatory=='MANDATORY'}">*</c:if></label>
		<form:input cssClass="field text addr" path="permanentTelephone" id="permanentTelephone"/><form:errors path="permanentTelephone" cssClass="field_error" />	
		</span>
		<span class="full addr1">
		<label><spring:message code="member_contact_details.fax" text="Fax"/><span><spring:message code="${fields.permanentFax.hint}" text=""/></span>&nbsp;<c:if test="${fields.permanentFax.mandatory=='MANDATORY'}">*</c:if></label>
		<form:input cssClass="field text addr" path="permanentFax" id="permanentFax"/><form:errors path="permanentFax" cssClass="field_error" />	
		</span>
		<span class="full addr1">
		<label><spring:message code="member_contact_details.mobile" text="Mobile"/><span><spring:message code="${fields.permanentMobile.hint}" text=""/></span>&nbsp;<c:if test="${fields.permanentMobile.mandatory=='MANDATORY'}">*</c:if></label>
		<form:input cssClass="field text addr" path="permanentMobile" id="permanentMobile"/><form:errors path="permanentMobile" cssClass="field_error" />	
		</span>	
		</div>
		</li>	
		
		<li class="buttons">
			<input id="saveForm" class="btTxt" type="submit" value="<spring:message code='generic.submit' text='Update'/> " />
		</li>	
		
		<form:hidden path="version"/>
	</ul>		
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
	/*
	*Same as above functionality
	*/
		if($('#addressSameAsAbove').attr("checked")=="checked"){  	  			   	
  	  		$('.permanent').hide();    	  		
  		}else
  		{ 
  			$('.permanent').show();    	 	   		
  		}  		
  		$('#addressSameAsAbove').change(function(){
  		if($('#addressSameAsAbove').attr("checked")=="checked"){  	  			   	
  	  		$('#permanentMobile').val("9999999999"); 
  			$('#permanentFax').val("Fax");    
  			$('#permanentTelephone').val("02222222222"); 	
  			$('#permanentPinCode').val("123456"); 	
  			$('#permanentTehsils').val("kalyan"); 	
  			$('#permanentDistricts').val("Mumbai"); 	
  			$('#permanentStates').val("Maharashtra"); 	
  			$('#permanentCity').val("Mumbai"); 	
  			$('#permanentAddress').val("abc"); 
  			$('.permanent').hide();    	  		
  		}else
  		{ 
  			$('#permanentMobile').val(""); 
  			$('#permanentFax').val("");    
  			$('#permanentTelephone').val(""); 	
  			$('#permanentPinCode').val(""); 	
  			$('#permanentTehsils').val(""); 	
  			$('#permanentDistricts').val(""); 	
  			$('#permanentStates').val(""); 	
  			$('#permanentCity').val(""); 	
  			$('#permanentAddress').val(""); 
  			$('.permanent').show();    	 	   		
  		}
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
		function loadDistrictsByStateId(id,type){
			$.ajax({
				url:'ref/'+id+'/districts',
				datatype:'json',
				success:function(data){
					$('#'+type+'Districts option').remove();
					if(data.length>=1){
						for(var i=0;i<data.length;i++){
							$('#'+type+'Districts').append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
						}
						loadTehsilsByDistrictId(data[0].id,type);							
					}else{
						$('#'+type+'tehsils option').remove();
					}					
			}							
			});
		}

		function loadTehsilsByDistrictId(id,type){
			$.ajax({
				url:'ref/'+id+'/tehsils',
				datatype:'json',
				success:function(data){
				$('#'+type+'Tehsils option').remove();
					for(var i=0;i<data.length;i++){
					$('#'+type+'Tehsils').append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
				}
			}							
			});
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