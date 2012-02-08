<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<script type="text/javascript">
		$('document').ready(function(){
			$('#file_${param.fileid}').fileupload({url: 'fileupload', formData:null});
		});
	</script>
</head>
</html>
<span id="file_${param.fileid}_upload" style="display: inline; margin: 0px; padding: 0px;">
	<input id="file_${param.fileid}" type="file" class="sText" />
	<input type=hidden id="${param.fileid}" name="${param.fileid}" value=""/>
	<span id="file_${param.fileid}_progress" style="display: none;">File uploading. Please wait...</span>
</span>