<%@ include file="/common/taglibs.jsp"%>
<c:if test="${type eq 'error'}">
	<div class="toolTip tpRed clearfix">
		<p>
			<img src="./resources/images/template/icons/light-bulb-off.png">
			<spring:message code="update_failed" text="Please correct following errors."/>
		</p>
		<p></p>
	</div>
</c:if>
<c:if test="${type eq 'success'}">
	<div class="toolTip tpGreen clearfix">
		<p>
			<img src="./resources/images/template/icons/light-bulb-off.png">
			<spring:message code="update_success" text="Data saved successfully."/>
		</p>
		<p></p>
	</div>
</c:if>