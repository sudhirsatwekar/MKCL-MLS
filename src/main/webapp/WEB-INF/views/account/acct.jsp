<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
	<title>Preferences</title>
	<script type="text/javascript" src="./resources/js/jquery.scrollTo-min.js"></script>
	<script type="text/javascript" src="./resources/js/jquery.localscroll-min.js"></script>
	<script type="text/javascript">
	 $(document).ready(function(){
		 var $sections = $('#acct_navigation a'),// Links on the left
			last = null;// Last section
		
		$sections.click(function(){
			if( last != this ){ // let's avoid needless requests
				var url = this.href;
				$('#accountContent').html( '<p class="loading">Loading...</p>' ).load( url, function(){
					this.scrollLeft = 0;//scroll back to the left
				});
			}
			last = this;
			this.blur(); // Remove the awful outline
			return false;
		});
		
		$sections.eq(0).click(); // Load the first section
		 $('#accountContent').localScroll({// Only the links inside that jquery object will be affected
				lazy: true, // This is the KEY setting here, makes the links work even after an Ajax load.
				target: '#accountContent', // The element that gets scrolled
				axis:'x', // Horizontal scrolling
				duration:500,
				onBefore:function( e, subsec, $cont ){//'this' is the clicked link
					if( this.blur )
						this.blur(); // Remove the awful outline
				}
		  });
			 
	 });
	</script>
	<style type="text/css">
		body{ 
	padding: 0 5px;
	font-family: Verdana, sans-serif;
	background-color: #DDD;
}
ul, li, h3, h2, h1, p{
	padding:0;
	margin:0;
	list-style:none;
}

.sidebar{
	position:absolute;
	right:5px;
	top:15px;
}

#links{
	border:1px solid black;
	/*width:210px;*/
	padding:10px;
	background-color:white;
}
	#links h3{
		color:#933;
	}
	#links ul{
		padding: 8px 0 3px 20px;
	}
	#links li{
		list-style-type:circle;
	}
	#links a{
		color:#69C;
	}

h1{
	margin:20px 0;
	color:#5B739C;
}
	h1 strong{
		font-size:13px;
		color:#777;
	}
h2.title{
	color:#933;
	margin-bottom:10px;
	text-align:right;
}
.clear{
	clear:left;
}
		#accountContent{
			overflow:hidden;
			width:630px;
			background-color:white;
			position:relative;
			float:left;
			height:400px;
			}
	#accountContent h2{
		color:#993333;
		margin:20px 0pt;
	}
	#accountContent img{
		left:191px;
		position:absolute;
		top:101px;
		z-index:5;
	}
	
	#accountContent .loading{
		font-size:1.2em;
		margin:190px 0pt 0pt 265px;
		color:#933;
	}
#acct_navigation{
	border-right:0px !important;
	border-right:1px;
	float:left;
	width:110px;
	background-color:#5B739C;
	height:400px;
}
	#acct_navigation li{
		margin:48px 0pt 48px 20px;
	}
		#acct_navigation a{
			color:white;
			font-weight:bolder;
			text-decoration:none;
			font-size: 85%;
		}
		#accountContent a{
			color:#777;
			font-weight:bolder;
			text-decoration:none;
		}
		#acct_navigation a.scrolling{
			color:#FF0000;
		}
.section{
	margin:-1px 0 0 -1px;
	width:1900px;
	border-bottom:1px solid black;
	position:relative;
	z-index:1;
}

	.section .sub{
		position:relative;
		float:left;
		padding:9px 21px 42px 45px;
		width:567px;
		height:352px;
	}
		.section .sub p{
			width:550px;
			margin:16px 0;
			font-size:85%;
			line-height:1.4em;
		}
		.section .next, .section .prev{
			font-size:18px;
			position:absolute;
			bottom:15px;
			letter-spacing:-2px;
		}
		.section .next{
			right:30px;
		}
		.section .prev{
			left:30px;
		}

		.sub-sections{
			position:absolute;
			right:20px;
			top:5px;	
			z-index:2;
		}
	.sub-sections li{
		margin:0 10px;
		padding:0;
		float:left;
	}
	.sub-sections a{
		font-size:85%;
	}
	</style>
</head>
<body>
<h1>Account Preferences</h1>
		<ul id="acct_navigation">
			<li><a href="acct/changepwd">Change Password</a></li>
			<li><a href="acct/about">About</a></li>
		</ul>															
	<div id="accountContent">
		<p class="loading">Loading...</p>		
	</div>
</body>
</html>