<%@ include file="/common/taglibs.jsp"%>
<footer>
      <div id="footer" style="padding:5px;font-weight:bold;float:right;margin-right: 10px;color:navy; ">
      	  <jsp:useBean id="now" class="java.util.Date" />
          <div class="grid_8">
              <spring:message code="app.name" text="MLS"/>&nbsp;v<spring:message code="app.version" text="0.0.1"/> | &copy; <a href="http://www.mkcl.org" target="_blank"><spring:message code="company" text="MKCL"/></a>, <fmt:formatDate pattern="yyyy" value="${now}" />-12. <spring:message code="copyright" text="All Rights reserved"/>. 
          </div>

      </div>
</footer>