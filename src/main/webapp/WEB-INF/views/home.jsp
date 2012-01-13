<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>Home</title>
	<style type="text/css">
		.ui-layout-north{
			height:38px !important;
			padding: 1px !important;
			border: 0px !important;
		}
		.north-west {
    		background-color: #EEE !important;
    		padding: 2px !important;
    		height:32px !important;
    		overflow: visible !important;
		}
		.north-center {
    		background-color: #EEE !important;
    		padding: 2px !important;
    		height:32px !important;
    		overflow: visible !important;
		}
		.center-south{
			border: 0px !important;
		}
		.center-center{
			border: 0px !important;
			padding:0px !important;
			overflow: none !important;
		}
		.field_error{
			color:red !important;
		}
		input[readonly]{
		    background-color: #F2F2F2 !important;
		    color: #C6C6C6;
		    border-color: #ddd;
		}
		
	</style>
	<link rel="stylesheet" media="screen" href="./resources/css/aristo/jquery-ui-1.8.7.custom.css" type="text/css" />
	<link type="text/css" rel="stylesheet" href="./resources/css/layout-default.css" />
	<link type="text/css" rel="stylesheet" href="./resources/css/wufoo/theme.css" />
	<link type="text/css" rel="stylesheet" href="./resources/css/wufoo/structure.css" />
	<link type="text/css" rel="stylesheet" href="./resources/css/wufoo/form.css" />
	<link type="text/css" rel="stylesheet" href="./resources/css/jquery.toastmessage.css" />
	<script type="text/javascript" src="./resources/js/jquery-1.6.2.min.js"></script>
	<script type="text/javascript" src="./resources/js/jquery-ui-1.8.15.custom.min.js"></script>
	<script type="text/javascript" src="./resources/js/jquery.hotkeys.js"></script>
	<script type="text/javascript" src="<c:url value="./resources/js/jquery.jlayout.js"/>"></script>
	<script type="text/javascript" src="<c:url value="./resources/js/jquery.toastmessage.js"/>"></script>
	<script type="text/javascript" src="./resources/js/wufoo.js"></script>
	<script type="text/javascript">
		function resizeGrid(pane, $Pane, paneState) {
		    $("#grid").jqGrid('setGridWidth',paneState.innerWidth-2, 'true');
		    $(".ui-jqgrid-bdiv").css('height', paneState.innerHeight-135);
		};
		function handleXhrError(xhr) {
			if(xhr.status==500 && xhr.responseText.indexOf("SESSION_TIMED_OUT")!=-1){
				alert('Due to inactivity for a long time, your session has expired. You will now be logged out.');
				window.location.href='login.htm';
			}
			else{
				$().toastmessage('showToast', {
				    text     : "<p style='font-size:small;color:red'>An error has occured while carrying out this operation. The details have been reported to the project team.</p> You will be notified of the resolution soon.",
				    type     : 'error',
				    stayTime : 3000,
				    inEffectDuration:  600
				});
			}
		};
		var myLayout;
		$(document).ready(function () {
			$("#logout").click(function(){
			    window.location.href($(this).href);
			    return false;
		    });
			$('body').layout({applyDefaultStyles:true,
			  north: {
				spacing_open: 0
			  },	
			  west: {
			    fxName: "slide",
			    size:    600,
			    maxSize: 750,
			    onresize: resizeGrid,
			    triggerEventsOnLoad: true  // resize the grin on load also
			  }
			});
			westLayout = $('div.ui-layout-center').layout({
				minSize:				10	// ALL panes
			,	center__paneSelector:	".center-center"
			,	spacing_open:	0
			,	south__paneSelector:	".center-south"
			,	south__size:			600
			});
			northLayout = $('div.ui-layout-north').layout({
				minSize:				50	// ALL panes
			,	west__paneSelector:	".north-west"
			,	spacing_open:	0
			,	center__paneSelector:	".north-center"
			,	west__size:			1000
			});
		    myLayout = $('body').layout();
		    $.ajaxSetup({
			    error: handleXhrError
			});
		});
	</script>
</head>
<body>
	<div class="ui-layout-center">
		<div class="center-center"></div>
		<div class="center-south"></div>
	</div>
	<div class="ui-layout-west">West</div>
	<div class="ui-layout-north" >
		<div class="north-west" onmouseover="myLayout.allowOverflow('north')" onmouseout="myLayout.resetOverflow(this)"><%@ include file="/common/menu.jsp" %></div>
		<div class="north-center">
			<a href="<c:url value="/j_spring_security_logout" />" id="logout" name="logout">Logout</a> 
		</div>
	</div>
</body>
</html>
