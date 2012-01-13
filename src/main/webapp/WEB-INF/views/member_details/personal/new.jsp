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
			<a  href="#" class="mis" id="member_personal_details"><spring:message code="mis.personal.new.link" text="Personal Details" /></a> |
			<a  href="#" class="mis" id="member_contact_details"><spring:message code="mis.contact.new.link" text="Contact Details"/></a> |
			<a  href="#" class="mis" id="member_other_details"><spring:message code="mis.other.new.link" text="Other Details"/></a>
		</div>
</div>	
<form:form  action="member_personal_details" method="POST" modelAttribute="memberPersonalDetails">
	<div class="info">
		 <h2><spring:message code="member_personal_details.new.heading" text="Personal Details"/></h2>		
		<div style="background-color:#C1CDCD; ;padding: 3px"><spring:message code="generic.mandatory.label" text="All fields marked * are mandatory"/></div>
	</div>
	<div id="positionContentDiv">
	<ul>	
					
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
			
		 <li id="${fields.photoField.position}" class="${fields.photoField.visible}" >
		 <div>
		 	 <label><spring:message code="member_personal_details.photo.label" text="Upload Photo"/><span><spring:message code="${fields.photoField.hint}"  text=""/></span>&nbsp;<c:if test="${fields.photoField.mandatory=='MANDATORY'}">*</c:if></label>	
		 	 <div class="hideDiv" id="photoDiv">
		     <img width="80" height="90" id="photoDisplay"/>
		     </div>
		     <div >
			 <input id="photo" readonly="readonly" type="text" value="${photoOriginalName}">
			 <button id="photoRemove" class="btTxt" type="button"><spring:message code="generic.remove" text="Remove Photo"/></button>
			 </div>
			 <form:hidden path="photo" id="photoField" cssClass="${fields.photoField.mandatory}"></form:hidden>
			 <form:errors path="photo" cssClass="field_error" />
		 </div>	
		 </li>
		 	
		<li  class="name ${fields.title.visible}" id="${fields.title.position}">
		<label class="desc"><spring:message code="member_personal_details.name" text="Name"/><span><spring:message code="${fields.title.hint}"  text=""/></span>&nbsp;<c:if test="${fields.title.mandatory=='MANDATORY'}">*</c:if></label>		
		<span>
		<label><spring:message code="member_personal_details.title" text="Title"/><span><spring:message code="${fields.title.hint}" text=""/></span>&nbsp;<c:if test="${fields.title.mandatory=='MANDATORY'}">*</c:if></label>
		<form:select cssClass="field select ${fields.title.mandatory}" path="title" items="${titles}" itemValue="id" itemLabel="name" /><form:errors path="title" cssClass="field_error" />	
		</span>
		<span>
		<label><spring:message code="member_personal_details.firstName" text="First Name"/><span><spring:message code="${fields.firstName.hint}" text=""/></span>&nbsp;<c:if test="${fields.firstName.mandatory=='MANDATORY'}">*</c:if></label>
		<form:input cssClass="field text ${fields.firstName.mandatory}" path="firstName"/><form:errors path="firstName" cssClass="field_error" id="firstNameError" /><span id="firstNameError"></span>
		</span>
		<span>
		<label><spring:message code="member_personal_details.middleName" text="Middle Name"/><span><spring:message code="${fields.middleName.hint}" text=""/></span>&nbsp;<c:if test="${fields.middleName.mandatory=='MANDATORY'}">*</c:if></label>
		<form:input cssClass="field text ${fields.middleName.mandatory}" path="middleName"/><form:errors path="middleName" cssClass="field_error" /><span id="middleNameError"></span>	
		</span>
		<span>
		<label><spring:message code="member_personal_details.lastName" text="Last Name"/><span><spring:message code="${fields.lastName.hint}" text=""/></span>&nbsp;<c:if test="${fields.lastName.mandatory=='MANDATORY'}">*</c:if></label>
		<form:input cssClass="field text ${fields.lastName.mandatory}" path="lastName"/><form:errors path="lastName" cssClass="field_error" /><span id="lastNameError"></span>	
		</span>
		</li>
		
		<li  class="name ${fields.alias.visible}" id="${fields.alias.position}">
		<label class="desc"><spring:message code="member_personal_details.alias" text="Member's Alias"/><span><spring:message code="${fields.alias.hint}" text=""/></span>&nbsp;<c:if test="${fields.title.mandatory=='MANDATORY'}">*</c:if></label>		
		<span>
		<label><spring:message code="member_personal_details.alias" text="Alias"/><span><spring:message code="${fields.alias.hint}" text=""/></span>&nbsp;<c:if test="${fields.alias.mandatory=='MANDATORY'}">*</c:if></label>
		<form:input cssClass="field text ${fields.alias.mandatory}" path="alias" size="25"/><form:errors path="alias" cssClass="field_error" /><span id="aliasError"></span>	
		</span>
		<span>
		<label><spring:message code="member_personal_details.aliasenabled" text="Alias Enabled?"/><span><spring:message code="${fields.enableAliasing.hint}" text=""/></span>&nbsp;<c:if test="${fields.enableAliasing.mandatory=='MANDATORY'}">*</c:if></label>
		<form:checkbox cssClass="field text ${fields.enableAliasing.mandatory}" path="enableAliasing" value="true" id="enableAliasing"/><form:errors path="enableAliasing" cssClass="field_error" /><span id="enableAliasingError"></span>
		</span>
		</li>
		
		<li id="${fields.gender.position}" class="${fields.gender.visible}">
		<label class="desc"><spring:message code="member_personal_details.gender" text="Gender"/><span><spring:message code="${fields.gender.hint}" text=""/></span>&nbsp;<c:if test="${fields.gender.mandatory=='MANDATORY'}">*</c:if></label>
			<div>
				<form:select path="gender" cssClass="field select medium ${fields.partyName.mandatory}">
				<form:option value="male">Male</form:option>
				<form:option value="male">Female</form:option>				
	            </form:select><form:errors path="gender" cssClass="field_error"/>		
		   </div>
		</li>
			
		<li id="${fields.constituencies.position}" class="${fields.constituencies.visible}">
		<label class="desc"><spring:message code="member_personal_details.constituency" text="Constituency"/><span><spring:message code="${fields.constituencies.hint}" text=""/></span>&nbsp;<c:if test="${fields.constituencies.mandatory=='MANDATORY'}">*</c:if></label>
		<div  id="constituencies" url="ref/constituencies"></div>
		</li>	
			
		<li id="${fields.district.position}" class="${fields.district.visible}"  >
		<div>
		<span class="left districts">
		<label class="desc"><spring:message code="member_personal_details.district" text="District"/><span><spring:message code="${fields.district.hint}" text=""/></span>&nbsp;<c:if test="${fields.district.mandatory=='MANDATORY'}">*</c:if></label>
		<input name="district" id="district" class="field text medium ${fields.district.mandatory}" type="text" value="${district}">
		</span>	
		<span class="right states">
		<label class="desc"><spring:message code="member_personal_details.state" text="State"/><span><spring:message code="${fields.state.hint}" text=""/></span>&nbsp;<c:if test="${fields.state.mandatory=='MANDATORY'}">*</c:if></label>
		<input type="text" name="state" id="state" class="field text medium ${fields.district.mandatory}" value="${state}">
		</span>						
		</div>
		</li>
		
		<li id="${fields.partyName.position}" class="${fields.partyName.visible}">
		<label class="desc"><spring:message code="member_personal_details.party" text="Party Name"/><span><spring:message code="${fields.partyName.hint}" text=""/></span>&nbsp;<c:if test="${fields.partyName.mandatory=='MANDATORY'}">*</c:if></label>
			<div>
				<form:select path="partyName" items="${parties}" itemValue="id" itemLabel="name" cssClass="field select medium ${fields.partyName.mandatory}">
	            </form:select><form:errors path="partyName" cssClass="field_error" />		
		   </div>
		</li>
		
		<li class="complex ${fields.fatherName.visible}" id="${fields.fatherName.position}">
		<div>
		<span class="left">
		<label class="desc"><spring:message code="member_personal_details.fatherName" text="Father's Name"/><span><spring:message code="${fields.fatherName.hint}" text=""/></span>&nbsp;<c:if test="${fields.fatherName.mandatory=='MANDATORY'}">*</c:if></label>
		<form:input cssClass="field text ${fields.fatherName.mandatory}" path="fatherName"/><form:errors path="fatherName" cssClass="field_error" />	
		</span>
		<span class="right">
		<label class="desc"><spring:message code="member_personal_details.motherName" text="Mother's Name"/><span><spring:message code="${fields.motherName.hint}" text=""/></span>&nbsp;<c:if test="${fields.motherName.mandatory=='MANDATORY'}">*</c:if></label>
		<form:input cssClass="field text ${fields.motherName.mandatory}" path="motherName"/><form:errors path="motherName" cssClass="field_error" />	
		</span>
		</div>
		</li>
		
		<li id="${fields.birthDate.position}" class="${fields.birthDate.visible}">	
		<label class="desc"><spring:message code="member_personal_details.birthDate" text="Date of Birth"/><span><spring:message code="${fields.birthDate.hint}" text=""/></span>&nbsp;<c:if test="${fields.placeOfBirth.mandatory=='MANDATORY'}">*</c:if></label>
			<div>
				<form:input cssClass="date ${fields.birthDate.mandatory}" path="birthDate"/><form:errors path="birthDate" cssClass="field_error" />	
			</div>
		</li>
		
		<li id="${fields.placeOfBirth.position}" class="${fields.placeOfBirth.visible}">	
		<label class="desc"><spring:message code="member_personal_details.placeOfBirth" text="Place of Birth"/><span><spring:message code="${fields.placeOfBirth.hint}" text=""/></span>&nbsp;<c:if test="${fields.placeOfBirth.mandatory=='MANDATORY'}">*</c:if></label>
			<div>
				<form:input cssClass="date ${fields.placeOfBirth.mandatory}" path="placeOfBirth"/><form:errors path="placeOfBirth" cssClass="field_error" />	
			</div>
		</li>
		
		<li id="${fields.maritalStatus.position}" class="${fields.maritalStatus.visible}">
		<label class="desc"><spring:message code="member_personal_details.maritalStatus" text="Marital Status"/><span><spring:message code="${fields.maritalStatus.hint}" text=""/></span>&nbsp;<c:if test="${fields.maritalStatus.mandatory=='MANDATORY'}">*</c:if></label>
			<div>
				<form:checkbox cssClass="checkbox ${fields.maritalStatus.mandatory}" path="maritalStatus" value="true" id="maritalStatus"/><form:errors path="maritalStatus" cssClass="field_error" />	
			</div>
		</li>
		
		
		<li class="date ${fields.marriageDate.visible}" id="${fields.marriageDate.position}">
		<div class="marriage">				
		<label class="desc"><spring:message code="member_personal_details.marriageDate" text="Date of Marriage"/><span><spring:message code="${fields.marriageDate.hint}" text=""/></span>&nbsp;<c:if test="${fields.marriageDate.mandatory=='MANDATORY'}">*</c:if></label>
			<div>
				<form:input cssClass="date ${fields.marriageDate.mandatory}" path="marriageDate"/><form:errors path="marriageDate" cssClass="field_error" />	
			</div>
		</div>
		</li>
		
		<li id="${fields.spouseName.position}" class="${fields.spouseName.visible}">
		<div class="marriage">				
		<label class="desc"><spring:message code="member_personal_details.spouseName" text="Spouse's Name"/><span><spring:message code="${fields.spouseName.hint}" text=""/></span>&nbsp;<c:if test="${fields.spouseName.mandatory=='MANDATORY'}">*</c:if></label>
			<div>
				<form:input cssClass="field text medium ${fields.spouseName.mandatory}" path="spouseName" size="200"/><form:errors path="spouseName" cssClass="field_error" />	
			</div>
		</div>
		</li>
		
		<li id="${fields.noOfSons.position}" class="${fields.noOfSons.visible}">
		<div class="marriage">		
		<label class="desc"><spring:message code="member_personal_details.noOfSons" text="No. of Sons"/><span><spring:message code="${fields.noOfSons.hint}" text=""/></span>&nbsp;<c:if test="${fields.noOfSons.mandatory=='MANDATORY'}">*</c:if></label>
			<div>
				<form:input cssClass="integer field text medium ${fields.noOfSons.mandatory}" path="noOfSons" size="200"/><form:errors path="noOfSons" cssClass="field_error" />	
			</div>
		</div>		
		</li>
		
		<li id="${fields.noOfDaughter.position}" class="${fields.noOfDaughter.visible}">
		<div class="marriage">				
		<label class="desc"><spring:message code="member_personal_details.noOfDaughter" text="No. of Daughter"/><span><spring:message code="${fields.noOfDaughter.hint}" text=""/></span>&nbsp;<c:if test="${fields.noOfDaughter.mandatory=='MANDATORY'}">*</c:if></label>
			<div>
				<form:input cssClass="integer field text medium ${fields.noOfDaughter.mandatory}" path="noOfDaughter" size="200"/><form:errors path="noOfDaughter" cssClass="field_error" />	
			</div>	
		</div>						
		</li>			
	
		<li id="${fields.educationalQualification.position}" class="${fields.educationalQualification.visible}">
		<label class="desc"><spring:message code="member_personal_details.educationalQualification" text="Educational Qualification"/><span><spring:message code="${fields.educationalQualification.hint}" text=""/></span>&nbsp;<c:if test="${fields.educationalQualification.mandatory=='MANDATORY'}">*</c:if></label>
			<div>
				<form:textarea cssClass="field textarea medium ${fields.educationalQualification.mandatory}" path="educationalQualification" rows="5" cols="50"/><form:errors path="educationalQualification" cssClass="field_error" />	
			</div>
		</li>
		
		<li id="${fields.profession.position}" class="${fields.profession.visible}">
		<label class="desc"><spring:message code="member_personal_details.profession" text="Profession"/><span><spring:message code="${fields.profession.hint}" text=""/></span>&nbsp;<c:if test="${fields.profession.mandatory=='MANDATORY'}">*</c:if></label>
			<div>
				<form:textarea cssClass="field textarea medium ${fields.profession.mandatory}" path="profession" rows="5" cols="50"/><form:errors path="profession" cssClass="field_error" />	
			</div>
		</li>
		</ul>
		</div>
	<input id="saveForm" class="btTxt" type="submit" value="<spring:message code='generic.new.submit' text='Create'/>" />
	<form:hidden path="version"/>
	<form:hidden path="id"/>	
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
	<input type="hidden" id="const_name" value="${constituency.name}">
	<input type="hidden" id="const_id" value="${constituency.id}">	
	<input type="hidden" id="photo_size" value="${photoSize}">	
	<input type="hidden" id="photo_ext" value="${photoExt}">
	<input type="hidden" id="positionList" value="${positionList}">			
				
<script type="text/javascript">
	function repositionElements(){	
	var positionList=$('#positionList').val();
	var positionArrays=positionList.split("#");
	for(var i=0;i<positionArrays.length-1;i++){
		var id=positionArrays[i];
    	$('#positionContentDiv').prepend($('#'+id));
	}
	}

	$('document').ready(function(){	
			/*
			*Reposition Elements
			*/
			repositionElements();
			/*
			*Marriage show,hide
			*/	
			if($('#maritalStatus').attr("checked")=="checked"){
			$('.marriage').show();
			}
			else{
			$('.marriage').hide();			
			};			
			$('#maritalStatus').change(function(){
			if($('#maritalStatus').attr("checked")=="checked"){
				$('.marriage').show();
			}
			else{
				$('.marriage').hide();			
			}
			});
			/*
			*Constituency
			*/	
			function autosuggest_onchange(){
				var constituencyName=$('input[name=constituencies]').val();
				$.ajax({
					url:'ref/data/'+constituencyName+'/districts',
					datatype:'json',
					success:function(data){
						if(data.length>=1){
							var districts=data[0].name;
							for(var i=1;i<data.length;i++){
								if(data[i].name!=undefined){
									districts=districts+","+data[i].name;
								}
							}
							$('#district').val(districts);
							$('.districts').show();	
							$('#state').val(data[0].state.name);
							$('.states').show();
						}				
				}							
				});
			};
			var constFlexBox=$('#constituencies').flexbox($('#constituencies').attr('url'), {  
				paging: false,  
				maxVisibleRows: 20,
				onSelect:autosuggest_onchange
			});
			if($('#const_name').val()!=undefined&&$('#const_id').val()!=undefined){
				constFlexBox.setValue($('#const_name').val());	
				$('.states').show();
				$('.districts').show();		
			}
			if($('#const_name').val()!=""&&$('#const_id').val()!=""){
				constFlexBox.setValue($('#const_name').val());	
				$('.states').show();
				$('.districts').show();		
			}
			else{
				$('.states').hide();
				$('.districts').hide();
			}			
			
			/*
			*Photo Upload
			*/	
			if($('#photo').val()==''){
				uploadify('#photo',$('#photo_ext').val(),$('#photo_size').val());
	   		}else if(($('#photo').val()!='') && ($('#photo').val()!=undefined))
	   		{
	   		$('#photoDisplay').attr('src','/els/file/photo/'+$('#photoField').val());
		   	$('#photoDiv').removeClass('hideDiv').addClass('showDiv');
	   		}
			$('#photoRemove').click(function(){
				$.ajax({
				    type: "DELETE",
				    url: "file/remove/"+$('#photoField').val(),
				    contentType: "application/json; charset=utf-8",
				    dataType: "json",
				    success: function(json) {
				        if(json==true){
				        	$('#photo').val('');
							uploadify('#photo',$('#photo_ext').val(),$('#photo_size').val());
	   				        alert('File successfully deleted');
				        }
				    },
				    error: function (xhr, textStatus, errorThrown) {
				    	alert(xhr.responseText);
				    }
				});
			   $('#photoDisplay').attr('src','');
		   	   $('#photoDiv').removeClass('showDiv').addClass('hideDiv');
			})
		
			/*$('li').sortElements(function(a, b){
				if($(a).attr("id")!=undefined&&$(b).attr("id")!=undefined){
	   			 return parseInt($(a).attr("id")) > parseInt($(b).attr("id")) ? 1 : -1;
				}
			})*/

		
			$('.HIDDEN').each(function(){
				$(this).hide();
		})
		
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
	});		
</script>
</body>
</html>