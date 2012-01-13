<%@ include file="/common/taglibs.jsp" %>
<aside class="grid_1">
	<div id="activity" style="display:none">Processing. Please wait.. <img src="./images/ajax-loader.gif"/></div>
    <nav class="global">
    	
        <ul class="clearfix">
        	<li  class="active"><a href="#">Welcome! ${auth_user.firstName} ${auth_user.lastName}</a></li>
            <li><a class="nav-icon icon-process menu-action" id="process_list" href="profile"><spring:message code="sidebar.process.title"/></a></li>
            <li><a class="nav-icon icon-process menu-action" id="process_list" href="doc/demo">Doc Demo</a></li>
		</ul>
        <security:authorize access="hasRole('ADMIN')">
        <ul class="clearfix">
        	<li class="active"><a href="#"><spring:message code="sidebar.admin_modules.title"/></a></li>
        	 <li><a class="nav-icon icon-useradd menu-action" id="module_users" href="users"><spring:message code="sidebar.users.title"/></a></li>
            <li><a class="nav-icon icon-roles menu-action" id="module_roles" href="roles"><spring:message code="sidebar.roles.title"/></a></li>
        </ul>
        </security:authorize>
    </nav>
    <nav class="subnav recent">
    </nav>

</aside>