/*
******************************************************************
File: org.mkcl.insyncflow.common.resolvers.NotifyingExceptionResolver.java
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
package org.mkcl.els.common.resolvers;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mkcl.els.service.IErrorNotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;


/**
 * The Class NotifyingExceptionResolver.
 *
 * @author vishals
 * @version 1.0.0
 */
public class NotifyingExceptionResolver extends  SimpleMappingExceptionResolver{
	
	/** The log. */
	private Logger log = LoggerFactory.getLogger(NotifyingExceptionResolver.class);
	
	/** The notification service. */
	@Autowired 
	private IErrorNotificationService notificationService;

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.SimpleMappingExceptionResolver#doResolveException(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,  HttpServletResponse response, Object handler, Exception ex) {
		log.warn("An Exception has occured in the application", ex);
		String user = "UNKNOWN";
		if(request.getUserPrincipal() != null)
		{
			user = request.getUserPrincipal().getName();
		}
		sendNotification(user, request.getParameterMap(), ex);
		return super.doResolveException(request, response, handler, ex);
	}

	/**
	 * Send notification.
	 *
	 * @param username the username
	 * @param requestParams the request params
	 * @param ex the ex
	 */
	private void sendNotification(String username, Map<String,String> requestParams, Exception ex) {
		String message = " Exception Occured";
		if(notificationService != null) {
			log.debug("notification message was sent");
			notificationService.sendNotification(message,username, requestParams,  ex);
		}
	}
}
