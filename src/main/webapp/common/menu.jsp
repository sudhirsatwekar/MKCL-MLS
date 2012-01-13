<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" media="screen" href="./resources/css/superfish.css"/>
	<script type="text/javascript" src="./resources/js/jquery.hoverIntent.js"></script>
	<script type="text/javascript" src="./resources/js/supersubs.js"></script> 
	<script type="text/javascript" src="./resources/js/superfish.js"></script>
	<script type="text/javascript"> 
	    $(document).ready(function(){ 
	        $("ul.sf-menu").supersubs({ 
	            minWidth:    12,   // minimum width of sub-menus in em units 
	            maxWidth:    27,   // maximum width of sub-menus in em units 
	            extraWidth:  1     // extra width can ensure lines don't sometimes turn over 
	                               // due to slight rounding differences and font-family 
	        }).superfish();  // call supersubs first, then superfish, so that subs are 

	        $('.menu_link').click(function(){
		        if(this.href.indexOf("home")==-1){
		           $("#alertmod").remove();//this is done as fix to jqgrid warning box that shows up at bottom of the page 
			       $("#navContent").load(this.href,function(data){
			    	   var title = $(data).filter('title').text();
						$('#navigation > .subHeader > div').html(title);
			       });
		        }
		        return false;
	        });
	    }); 
	</script>
</head>
<body>
	<c:set var="menu_xsl">
	  <?xml version="1.0"?>
	  <xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	    <xsl:output indent="yes"/>
	    <xsl:template match="/root">
	        <ul id="someid" class="sf-menu sf-js-enabled sf-shadow">
	            <xsl:apply-templates select="menu[not(@parent)]"/>
	        </ul>
	    </xsl:template>
	    <xsl:template match="menu">
	        <li>
	            <a href="{@url}" class="menu_link"><xsl:value-of select="@text"/></a>
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
</body>
</html>
