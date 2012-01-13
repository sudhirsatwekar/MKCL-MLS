<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
	<title><spring:message code="masters.menus.list.title" text="Menu Hierarchy"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<link rel="stylesheet" media="screen" href="./resources/css/jquery.treeview.css" type="text/css" />
	<script type="text/javascript" src="./resources/js/jquery.treeview.min.js"></script>
	<script type="text/javascript">
	 $(document).ready(function(){
		 $("#treeview").treeview(); 
		 
		 $('#expandAll').click(function() {        
		     $('#treeview a:eq(1)').click();
		     return false;    
		 });
		    
	     $('#collapseAll').click(function() {
	        $('#treeview a:eq(0)').click();
	        return false;        
	     });

	     $('.node_click').click(function(){
	    	 $('.selected').removeClass('selected');
	    	 $(this).addClass('selected');
			 $("#contentPanel").load(this.href,function(data){
				 var title = $(data).filter('title').text();
				 $('#content > .subHeader > div').html(title);
			 });
			 return false;
		 });
	     $('#refresh').click(function(){
	    	 $("#navContent").load('menus/list');
	    	 return false;
	 	 });
	     $('#new_record').click(function(){
		     var select_node = $('.selected').attr('id');
		     if(select_node){
	    	 	$("#contentPanel").load('menus/new?parentId='+select_node,function(data){
	    	 	});
		     }
		     else{
			    alert("Please select the node under which you would like to create a new menu item and then click New"); 
		     }
	    	 return false;
	 	 });
	     $('#delete_record').click(function(){
		     var select_node = $('.selected').attr('id');
		     if(select_node){
		    	 $.ajax({
		    		   url: 'menus/'+select_node+'/delete',
		    		   type: 'DELETE',
		    		   success: function( response ) {
			    			$('#refresh').trigger('click');
			    			$('#contentPanel').empty();
			    	   }
		    	});

		     }
	    	 return false;
	 	 });
	 	 
	 });
	</script>
</head>
<body>
<div>
	<div class="commandbar">
		<div class="commandbarContent" >
			<a href="menus/new" id="new_record"><spring:message code="generic.new" text="New"/></a> | 
			<a href="menus" id="delete_record"><spring:message code="generic.delete" text="Delete"/></a> |
			<a href="menus/list" id="refresh"><spring:message code="generic.refresh" text="Refresh"/></a> |
		</div>
	</div>
</div>
</body>
<c:set var="menu_xsl">
	  <?xml version="1.0"?>
	  <xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	    <xsl:output indent="yes"/>
	    <xsl:template match="/root">
	        <ul id="treeview" class="treeview-black">
	            <xsl:apply-templates select="menu[not(@parent)]"/>
	        </ul>
	    </xsl:template>
	    <xsl:template match="menu">
	        <li>
	            <span><a id="{@id}" href="menus/{@id}/edit" class="node_click"><xsl:value-of select="@text"/></a></span>
	            <xsl:if test="count(../menu[@parent=current()/@id])>0">
	                <ul>
	                    <xsl:apply-templates select="../menu[@parent=current()/@id]"/>
	                </ul>
	            </xsl:if>
	            </li>
	    </xsl:template>
		</xsl:stylesheet>
	</c:set>
    <x:transform xml="${menu_xml}" xslt="${menu_xsl}">
    </x:transform>
</html>
