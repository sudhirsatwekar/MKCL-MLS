<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
	<title><spring:message code="party.edit.title" text="Edit Party"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>
<form:form cssClass="wufoo" action="party" method="PUT" 
	modelAttribute="party">
	<div class="info">
		<h2><spring:message code="party.edit.heading" text="Details"/> [Id:${party.id}]</h2>
		<div style="background-color:#C1CDCD; ;padding: 3px"><spring:message code="generic.mandatory.label" text="Note: Fields marked * are mandatory"/></div>

	</div>
	<ul>	
	
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
	<label class="desc"><spring:message code="party.name" text="Party Name"/>&nbsp;*</label>
		<div>
			<form:input cssClass="field text medium" path="name"/>
			<form:errors path="name" cssClass="field_error" />	
		</div>
	</li>
		
	<li>
	<label class="desc"><spring:message code="party.abbreviation" text="Abbreviation"/>&nbsp;*</label>
		<div>
			<form:input cssClass="field text medium" path="abbreviation"/>
			<form:errors path="abbreviation" cssClass="field_error" />	
		</div>
	</li>
	
	<li>
		 <div>
		 	 <label class="desc"><spring:message code="party.photo.label" text="Party Symbol"/>&nbsp;*</label>	
		 	 <div class="hideDiv" id="photoDiv">
		     <img width="40" height="40" id="photoDisplay"/>
		     </div>
		     <div >
			 <input id="photo" readonly="readonly" type="text" value="${photoOriginalName}">
			 <button id="photoRemove" class="btTxt" type="button"><spring:message code="generic.remove" text="Remove"/></button>
			 </div>
			 <form:hidden path="photo" id="photoField"></form:hidden>
			 <form:errors path="photo" cssClass="field_error" />
		 </div>	
		 </li>
		 
	<li class="buttons">
			<input id="saveForm" class="btTxt" type="submit" value="<spring:message code='generic.submit' text='Submit'/>" />
	</li>
	<form:hidden path="version"/>
	</ul>		
</form:form>
<input type="hidden" id="photo_size" value="${photoSize}">	
<input type="hidden" id="photo_ext" value="${photoExt}">	
<script type="text/javascript">
$(document).ready(function(){

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
});
</script>
</body>
</html>