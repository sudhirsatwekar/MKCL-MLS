<%@ include file="/common/taglibs.jsp" %>
<%@ page contentType="text/html;charset=utf-8" %>

<html>
<head>
	<title>
		प्रस्ताव सूचना प्रणाली
	</title>
</head>
<body>
<div class="commandbar">
		<div class="commandbarContent">
			<a  href="#" class="mois" id="motion_assembly">सभा विवरण</a> |
			<a  href="#" class="mois" id="motion_information">प्रस्ताव विवरण</a> 
		</div>
</div>	
<form:form  action="motion_assembly" method="PUT" modelAttribute="motionInformation" >
	<div class="info">
		 <h2>सभा विवरण </h2>		
	</div>
	<div id="positionContentDiv">
	<ul>	
		<li>
		<label class="desc">"सूचना क्रमांक"&nbsp;</label>
			<div>
				<form:input cssClass="field text medium" path="id" readonly="true" cssStyle="width:260px"/>
			</div>
		</li>
		<li>
		<label class="desc">सूचना प्रकार &nbsp;</label>
			<div>
				<form:select cssClass="field select medium" path="motionType"> 
					<form:option value="Adjournment Motion">स्थगन प्रस्ताव</form:option>
					<form:option value="Calling Attention">लक्षवेधी सूचना</form:option>
					<form:option value="Half an hour discussion">अर्धा-तास चर्चा</form:option>
					<form:option value="Short Duration Discussion">अल्पकालीन चर्चा</form:option>
					<form:option value="Last Week Motion">अंतिम आठवडा प्रस्ताव</form:option>
					<form:option value="No Day Yet Named Motion">अनियत दिन प्रस्ताव</form:option>
					<form:option value="No Confidence Motion">मंत्रिमंडळावर अविश्वास व्यक्त करणारा प्रस्ताव</form:option>
					<form:option value="Confidence Motion">मंत्रिमंडळावर विश्वास व्यक्त करणारा प्रस्ताव</form:option>
					<form:option value="Removal of Speaker/ Dy. Speaker Motion">अध्यक्षांना/ उपाध्यक्षांना पदावरून दूर करण्यासंबंधीचा प्रस्ताव</form:option>
				</form:select>
			</div>
		</li>
		<li>
		 <span>
		 	<label class="desc">वर्ष &nbsp;</label>
	  	 	<form:input cssClass="field date" path="year" maxlength="4" cssStyle="width:260px"/>
		 </span>	
		 </li>
		<li>
		<label class="desc">सभा &nbsp;</label>
			<div>
				<form:select cssClass="field select medium" path="assembly"> 
					<form:option value="Monsoon">उन्हाळी</form:option>
					<form:option value="Winter">हिवाळी</form:option>
					<form:option value="Budget">बजट</form:option>
				</form:select>
			</div>
		</li>			
		<li>
		<span>
		<label class="desc">दिनांक&nbsp;</label>
		<form:input cssClass="date field text medium" path="assemblyDate" cssStyle="width:260px"/>
		</span>
		</li>
				
		</ul>
		</div>
		<input id="saveForm" class="btTxt" type="submit" value="सेव करणे" />
		
	<form:hidden path="id"/>
	<form:hidden path="version"/>
</form:form>
<script type="text/javascript">
		$('document').ready(function(){
			$('.mois').click(function(event){
				var id=1;
				if(id!=undefined && id!=""){
					$.get($(this).attr('id')+'/'+id+'/edit', function(data) {
				  		$('#contentPanel').html(data);
					});
				}			
			return false;			
			});	
			
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
			});
				if(count>0){
					
				}
				else{
					$.post($('form').attr('action'),  
				            $("form").serialize(),  
				            function(data){	
			   				$('.contentPanel').html(data);	
			   				$('#refresh').val($('#refreshSe').val());
			   				if($('#info_type').val()=='success'){			   				
				   	   	   		//$("#grid").trigger("reloadGrid");		   				
							}						   				  					   						   					
				            }                                         
		            );  
				}				
				        return false;
			});
		});
</script>
</body>
</html>