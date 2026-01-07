package com.ezform.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezform.domain.EZ_empVO;
import com.ezform.domain.EZ_notificationVO;
import com.ezform.service.EZ_notification_service;

@Controller
@RequestMapping("/ez_notification/*")
public class EZ_notification_Controller {

	@Inject
	private EZ_notification_service notificationService;
	
	@RequestMapping(value = "/unread", method=RequestMethod.GET)
	@ResponseBody
	public List<EZ_notificationVO> unread(HttpSession session,EZ_notificationVO nvo) {
	    EZ_empVO user = (EZ_empVO) session.getAttribute("resultVO");
	    
	    if(user != null) {
	    	nvo.setReceiver_name(user.getEm_email());
	        List<EZ_notificationVO> list = notificationService.getUnread(nvo);
	        if(list != null) return list; // 정상 반환
	    }
	    
	    return new ArrayList(); // 항상 JSON 배열 반환
	}
	
	@RequestMapping(value = "/list" , method=RequestMethod.GET)
	public String notificationList(HttpSession session, Model model, EZ_notificationVO notificationVO) throws Exception {
		
		EZ_empVO user = (EZ_empVO) session.getAttribute("resultVO");
	    
	    if(user != null) {
	    	notificationVO.setReceiver_name(user.getEm_email());
	        List<EZ_notificationVO> notificationList = notificationService.selectNotificationList(notificationVO);
	        model.addAttribute("notificationList", notificationList);
	    }
		
		
		return "/ez_notification/list";
	}
	
	@RequestMapping(value= "/readAll" , method=RequestMethod.POST)
	@ResponseBody
	public String notificationReadAll(HttpSession session, EZ_notificationVO nvo) throws Exception {
		
		EZ_empVO loginUser = (EZ_empVO) session.getAttribute("resultVO");
		nvo.setReceiver_name(loginUser.getEm_email());
		notificationService.updateAllRead(nvo);
		
		return "OK";
	}
}
