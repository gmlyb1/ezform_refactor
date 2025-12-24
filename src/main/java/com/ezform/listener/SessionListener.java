package com.ezform.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.ezform.common.SessionCounter;

public class SessionListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		SessionCounter.increment();
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		SessionCounter.decrement();
	}

}
