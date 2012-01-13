package org.mkcl.els.service;

import java.util.Map;

public interface IErrorNotificationService {
	  public void sendNotification(String message, Exception exception);	  
	  public void sendNotification(String message, String AuthUser, Map<String,String> requestParams, Exception exception);	
}
