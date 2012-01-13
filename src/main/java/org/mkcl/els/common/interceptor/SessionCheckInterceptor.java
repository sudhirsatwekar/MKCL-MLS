package org.mkcl.els.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mkcl.els.common.exception.SessionExpiredException;
import org.springframework.ui.Model;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionCheckInterceptor extends HandlerInterceptorAdapter{
	
	private String redirect;
	
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler)
	    throws Exception {
		String redirectUrl = this.createRedirectUrl(request);
		if(request.getRequestURI().contains("login")){
			return true;
		}
		if(request.getRequestURI().contains("/ws/")){
			return true;
		}
		if(request.getRequestURI().contains("home") && request.getUserPrincipal()==null){
			throw new SessionExpiredException("The user session has expired, please login again");
		}
		if(request.getRequestURI().contains("upload"))
		{
			return true;
		}
		if(request.getUserPrincipal()!=null){
			if(request.getMethod().equals("DELETE")){
				request.getSession().setAttribute("delete","delete");
			}	
			if(request.getRequestURI().contains("/new")||request.getRequestURI().contains("/edit")){
				if(request.getSession().getAttribute("delete")!=null){
					if(request.getSession().getAttribute("delete").equals("delete")){
						request.setAttribute("type","success");
						request.setAttribute("msg","delete_success");
					}	
					request.getSession().setAttribute("delete","");
				}
			}
			request.getSession().setAttribute("refresh","refresh");
			return true;
		}
		else{
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "SESSION_TIMED_OUT");
			return false;
		}
 
	}
	
	private String createRedirectUrl(HttpServletRequest request) {
		if (redirect.startsWith("/")) {
			return request.getContextPath() + redirect;
		} else {
			return redirect;
		}
	}
	
	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}

}
