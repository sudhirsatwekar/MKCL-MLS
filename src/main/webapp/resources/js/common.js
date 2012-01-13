/*Common javascript functions to be used across jsp files */
function initControls(){
	$('select[multiple="multiple"]').sexyselect({width:250,showTitle: false, selectionMode: 'multiple', styleize: true});
	$("input[class^='numeric']").autoNumeric();
	$("input[class^='integer']").autoNumeric({mDec: 0});
	$('.autosuggest').each(function(){
		$(this).flexbox($(this).attr('url'), {  
			paging: false,  
			maxVisibleRows: 20,
			onSelect:autosuggest_onchange
		});
	});	
	$('#dateformat').val();
	$("input[class^='date']").datepicker({changeMonth: true, changeYear: true, dateFormat: $('#dateformat').val(), yearRange: 'c-75:c+20'});

};
function resize_grid(){
	$('#grid').fluidGrid({base:'#grid_container', offset:-0});
	$("#grid").jqGrid('setGridHeight', $('#navigation').innerHeight()-103);
}
function loadGrid(gridId, baseFilter) {
	var c_grid = null;
	$.ajax({async:false,url:'grid/' + gridId + '/meta.json', success:function(grid) {
			c_grid = $('#grid').jqGrid({
			scroll:1,
			altRows:true,
			autowidth:true,
			shrinkToFit:true,
			ajaxGridOptions:{async:false},
			url:'grid/data/'+ gridId +'.json',
			datatype: 'json',
			mtype: 'GET',
			colNames:eval(grid.colNames),
			colModel :eval(grid.colModel),
			pager: '#grid_pager',
			rowNum:grid.pageSize,
			sortname: 'm.id',
			sortorder:grid.sortOrder,
			viewrecords: true,
			jsonReader: { repeatitems : false},
			gridview:true,
			multiselect:eval(grid.multiSelect),
			postData: {
				"baseFilters": baseFilter
			},
			loadComplete:function(data,obj){
				//$("#grid").jqGrid('setGridHeight', $('#navigation').innerHeight()-103);
				if($('#refresh').val()=="refresh"){
					var top_rowid = $('#grid tbody:first-child tr:nth-child(2)').attr('id');
					if(!top_rowid){
						$('#contentPanel').load(grid.detailView +'/new',function(data){
			                var title = $(data).filter('title').text();
							$('#content > .subHeader > div').html(title);
						});
					}
					else{
						$('#contentPanel').load(grid.detailView + '/' + top_rowid+'/edit',function(data){
							var title = $(data).filter('title').text();
							$('#content > .subHeader > div').html(title);

						});
					}	
				}								
			},
			onSelectRow:function() {
				if(!$(this).getGridParam("multiselect")){
					$('#contentPanel').empty();				
					var row = $("#grid").jqGrid('getGridParam','selrow');				
					$('#contentPanel').load(grid.detailView + '/' + row+'/edit',function(data){
						var title = $(data).filter('title').text();
						$('#content > .subHeader > div').html(title);
					});	
				}							
			}
		});
		$("#grid").jqGrid('navGrid','#grid_pager',{edit:false,add:false,del:false, search:true},{},{},{},{multipleSearch:true});
		$("#grid").jqGrid('bindKeys');
		$("#showhide_columns").click(function(){
			$("#grid").setColumns({caption:"Check/Uncheck columns to Show/Hide"});
			return false;
		});		
		$("#new_record").click(function(){
			var url = $(this).attr('href');
			$('.contentPanel').load(url,function(data){
                var title = $(data).filter('title').text();
				$('#content > .subHeader > div').html(title);
			});
   			return false;
		});
		$("#delete_record").click(function() {
			var row = $("#grid").jqGrid('getGridParam','selrow'); 
			if(row==null){
				alert("Please select the desired row to delete");		
			}
			else{
				var url = $(this).attr('href');
				$("#grid").jqGrid('delGridRow',row,{reloadAfterSubmit:true, mtype:'DELETE', url:url+'/'+row+'/delete',modal:true});
				
			}
			return false;
		});
	}});
	return c_grid;
};

/*Code for uploading files*/
function unUploadify(element){
	$(element).unbind("uploadifyComplete");
	$(element).next(element+"Uploader").remove();
	$(element).next(element+"Queue").remove();
	$(element).css("display","inline");
	$(element+"Remove").show();
	alert("File Uploaded Successfully");
};
	
	function uploadify(element,ext,size,url){
		var sizeInMB=size/(1024*1024);	
		var extTokens=ext.split(",");
		var extType="";
		for(var i=0;i<extTokens.length;i++){
			var temp=extTokens[i].replace('.','*.');
			extType=extType+temp+";";
		}	
	$(element).uploadify( {
	'script' : 'file/upload.json?ext='+ext+"#"+size,	
	'fileDataName' : 'file',
	'auto' : true,
	'multi' : false,
	'fileExt' : extType,
	'fileDesc'    : 'Files ('+ext+')',
	'onComplete' : uploadify_oncomplete,
	'uploader':'./resources/js/uploadify.swf',
	'cancelImg':'./resources/images/cancel.png',
	'sizeLimit':size,
	'onError': function (event,ID,fileObj,errorObj) {
		if(errorObj.type=="File Size"){
			alert("It seems you are trying to upload "+ Math.round((fileObj.size/(1024*1024)))+"MB file which exceeds permissible limit of "+sizeInMB+"MB");
		}
		else if(errorObj.type=="HTTP"){
			alert("File is not uploaded...please try uploading again");
		}
		else{
			alert(errorObj.type + ' Error: ' + errorObj.info);
		}
	      $('#'+event.target.id).uploadifyCancel(ID);

	    }
	});
	$(element+"Remove").hide();
};
function uploadify_oncomplete(event, ID, fileObj, response, data){
	try{
		var file = $.parseJSON(response);
		var element = '#'+event.target.id;
		$(element).val(file.file.originalFileName);
		$(element).attr("readonly","true");
		$(element+'Field').val(file.file.tag);
		unUploadify(element);
		if(event.target.id=='photo'){
					 		$('#photoDisplay').attr('src','/els/file/photo/'+$('#'+event.target.id+'Field').val());
				   		   	   $('#photoDiv').removeClass('hideDiv').addClass('showDiv');
			}
	}
	catch(ex){
		alert("File is not uploaded...please try uploading again");
		$('#'+event.target.id).uploadifyCancel(ID);
	}		
	return false;
};
function removeUpload(element,ext)
{
	
$.ajax({
   				    type: "DELETE",
   				    url: "file/remove/"+$(element).val(),
   				    contentType: "application/json; charset=utf-8",
   				    dataType: "json",
   				    success: function(json) {
   				        if(json==true){
   				        	$(element).val('');
   				        	uploadify(element,ext);
   	   				        alert('File successfully deleted');
   				        }
   				    },
   				    error: function (xhr, textStatus, errorThrown) {
   				    	alert(xhr.responseText);
   				    }
   				});

};
/***************/

jQuery.fn.sortElements = (function(){
	 
    var sort = [].sort;
 
    return function(comparator, getSortable) {
 
        getSortable = getSortable || function(){return this;};
 
        var placements = this.map(function(){
 
            var sortElement = getSortable.call(this),
                parentNode = sortElement.parentNode,
 
                // Since the element itself will change position, we have
                // to have some way of storing its original position in
                // the DOM. The easiest way is to have a 'flag' node:
                nextSibling = parentNode.insertBefore(
                    document.createTextNode(''),
                    sortElement.nextSibling
                );
 
            return function() {
 
                if (parentNode === this) {
                   // throw new Error(
                       // "You can't sort elements if any one is a descendant of another."
                   // );
                }
 
                // Insert before flag:
                parentNode.insertBefore(this, nextSibling);
                // Remove flag:
                parentNode.removeChild(nextSibling);
 
            };
 
        });
 
        return sort.call(this, comparator).each(function(i){
            placements[i].call(getSortable.call(this));
        });
 
    };
 
})();