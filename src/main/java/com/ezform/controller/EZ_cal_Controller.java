package com.ezform.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezform.domain.EZ_calendarVO;
import com.ezform.domain.EZ_empVO;
import com.ezform.domain.EZ_notificationVO;
import com.ezform.service.EZ_cal_Service;
import com.ezform.service.EZ_notification_service;

@Controller
@RequestMapping("/calendar/*")
public class EZ_cal_Controller {

	private static final Logger logger = LoggerFactory.getLogger(EZ_cal_Controller.class);

	@Autowired
	private EZ_cal_Service service;
	
	@Autowired
	private EZ_notification_service notification_service;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void calendarGET() throws Exception {

	}
	
	@RequestMapping(value = "/selectEventList", method = RequestMethod.GET)
	@ResponseBody
	public List<EZ_calendarVO> selectEventList(@RequestParam int userid) throws Exception {
		return service.selectEventList(userid);
	}
	
	@ResponseBody
	@RequestMapping(value = "/insertEvent", method = RequestMethod.POST)
	public Map<Object,Object> insertEvent(@RequestBody EZ_calendarVO vo,HttpSession session) throws Exception{
		Map<Object,Object>map = new HashMap<Object,Object>();
		
		EZ_empVO empVO = (EZ_empVO) session.getAttribute("resultVO");
	    EZ_notificationVO notificationVO = new EZ_notificationVO();
	    notificationVO.setReceiver_name(empVO.getEm_email());
	    notificationVO.setNoti_type("캘린더");
	    notificationVO.setNoti_message("캘린더 일정을 등록했습니다.");
	    notificationVO.setNoti_link("");
	    notification_service.insertNotification(notificationVO);
		
	    service.insertEvent(vo);
		return map;
	}
	
	@RequestMapping(value = "/resizeEvent", method = RequestMethod.POST)
	@ResponseBody
	public Map<Object,Object> resizeEvent(@RequestBody EZ_calendarVO vo) throws Exception {
		Map<Object,Object>map = new HashMap<Object,Object>();
		service.resizeEvent(vo);
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/updateEvent", method = RequestMethod.POST)
	public Map<Object,Object> updateEvent(@RequestBody EZ_calendarVO vo,Model model,HttpSession session) throws Exception{
		Map<Object,Object>map = new HashMap<Object,Object>();
		
		EZ_empVO empVO = (EZ_empVO) session.getAttribute("resultVO");
	    EZ_notificationVO notificationVO = new EZ_notificationVO();
	    notificationVO.setReceiver_name(empVO.getEm_email());
	    notificationVO.setNoti_type("캘린더");
	    notificationVO.setNoti_message("캘린더 일정을 수정했습니다.");
	    notificationVO.setNoti_link("");
	    notification_service.insertNotification(notificationVO);
		
		service.updateEvent(vo);
		return map;
	}
	
	@RequestMapping(value = "/deleteEvent", method = RequestMethod.GET)
	public String deleteEvent(@RequestParam int _id) throws Exception {
		service.deleteEvent(_id);
		return "redirect:/calendar/";
	}
}
