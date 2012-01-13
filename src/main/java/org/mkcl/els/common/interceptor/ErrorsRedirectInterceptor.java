/*
******************************************************************
File: org.mkcl.els.common.interceptor.ErrorsRedirectInterceptor.java
Copyright (c) 2011, vishals, MKCL
All rights reserved.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
POSSIBILITY OF SUCH DAMAGE.

******************************************************************
 */
package org.mkcl.els.common.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ErrorsRedirectInterceptor extends HandlerInterceptorAdapter {
	private final static Logger log = LoggerFactory.getLogger(ErrorsRedirectInterceptor.class);

	private final static String ERRORS_MAP_KEY = ErrorsRedirectInterceptor.class.getName()
	+ "-errorsMapKey";

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView mav)
	throws Exception 
	{
		if (mav == null) { return; }

		if (request.getMethod().equalsIgnoreCase(HttpMethod.POST.toString())||request.getMethod().equalsIgnoreCase(HttpMethod.PUT.toString())) {
			// POST
			Map<String, Errors> sessionErrorsMap = new HashMap<String, Errors>();
			// If there are any Errors in the model, store them in the session
			for (Map.Entry<String, Object> entry : mav.getModel().entrySet()) {
				Object obj = entry.getValue();
				if (obj instanceof Errors) {
					Errors errors = (Errors) obj;
					sessionErrorsMap.put(entry.getKey(), errors);
				}
			}
			if (!sessionErrorsMap.isEmpty()) {
				request.getSession().setAttribute(ERRORS_MAP_KEY, sessionErrorsMap);
			}
		} else if (request.getMethod().equalsIgnoreCase(HttpMethod.GET.toString())) {
			// GET
			Map<String, Errors> sessionErrorsMap =
				(Map<String, Errors>) request.getSession().getAttribute(ERRORS_MAP_KEY);
			if (sessionErrorsMap != null) {
				//Added By sandeep singh
				//Issue can be reproduced by first creating new,than post with some validation message
				//and when errors have appeared click new again.Instead of all errors getting cleared in case of new ,
				//we still see errors attached to the jsp.for edit we need to produce validation messages in edit(put)
				//and then click refresh in grid.Error messages doesn't seem to disaapear
				
					if(request.getRequestURI().contains("/new")||request.getRequestURI().contains("/edit")){
						request.getSession().removeAttribute(ERRORS_MAP_KEY);
					}
					else{
						mav.addAllObjects(sessionErrorsMap);
						request.getSession().removeAttribute(ERRORS_MAP_KEY);
					}
			
				
				
			}
		}
	}
}

