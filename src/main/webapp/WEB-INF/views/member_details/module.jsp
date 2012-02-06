<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
	<title><spring:message code="member_information_system" text="List of Members"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<script type="text/javascript">
		$(document).ready(function(){

			$('#list_tab').click(function(){
				showList();
			});
			
			$('#details_tab').click(function(){
				var row = $('#key').val();
				if(row == null){
					newRecord();
				}
				else{
					editRecord(row);
				}
			});
			
			$('#contact_tab').click(function(){
				editContactDetails($('#key').val());	
			});
			
			$('#other_tab').click(function(){
				editOtherDetails($('#key').val());	
			});
			
			$(document).keydown(function (e){
				if(e.which==78 && e.ctrlKey){
					newRecord();
				}
				if(e.which==83 && e.ctrlKey){
					$('#submit').trigger('click');
				}
				if(e.which==76 && e.ctrlKey){
					showList();
				}
				if(e.which==79 && e.ctrlKey){
					editRecord($('#key').val());
				}
				if(e.which==8 && e.ctrlKey){
					deleteRecord($('#key').val());
				}
				
				if(e.keyCode == 38 || e.keyCode == 40){
					scrollRowsInGrid(e);
		        }
			});
			showTabByIdAndUrl('list_tab','member_personal_details/list');
		});
		
		function showList() {
			showTabByIdAndUrl('list_tab','member_personal_details/list');
		}

		function newRecord() {
			showTabByIdAndUrl('details_tab','member_personal_details/new')	;
		}

		function editRecord(row) {
			if(this.id =='edit_record' && row==null){
				alert("Please select the desired row to edit");
				return false;
			}
			showTabByIdAndUrl('details_tab','member_personal_details/'+row+'/edit');
		}

		function deleteRecord(row) {
			if(row==null){
				$.prompt("Please select the desired row to delete");		
			}
			else{
				$.prompt('Are you sure you want to delete the record with Id: '+ row,{
					buttons: {Ok:true, Cancel:false}, callback: function(v){
			        if(v){
				        $.delete_('member_personal_details/'+row+'/delete', null, function(data, textStatus, XMLHttpRequest) {
				            showList();
				        });
			        }
				}});
			}
		}

		function editContactDetails(row) {
			if(row == null || row == ''){
				$.prompt("Please select the desired row to edit contact details");		
				return;
			}
			else{
				showTabByIdAndUrl('contact_tab','member_contact_details/'+row+'/edit');
			}
		}
		
		function editOtherDetails(row) {
			if(row == null || row == ''){
				$.prompt("Please select the desired row to edit other details");		
				return;
			}
			else{
				showTabByIdAndUrl('other_tab','member_other_details/'+row+'/edit');
			}
		}
		
		function rowDblClickHandler(rowid, iRow, iCol, e) {
			showTabByIdAndUrl('details_tab', 'member_personal_details/'+rowid+'/edit');
		}
	</script>
</head>
<body>
	<!-- .section -->
	<div class="clearfix tabbar">
		<ul class="tabs">
			<li class="tab1">
				<a id="list_tab" class="selected tab" href="#">
					List
				</a>
			</li>
			<li class="tab2">
				<a id="details_tab" href="#" class="tab">
					Details
				</a>
			</li>
			<li class="tab3">
				<a id="contact_tab" href="#" class="tab">
					Contact
				</a>
			</li>
			<li class="tab4">
				<a id="other_tab" href="#" class="tab">
					Other
				</a>
			</li>
		</ul>
		<div class="tabContent clearfix">
		</div>
		<input type="hidden" id="key" name="key">
	</div> 
</body>
</html>