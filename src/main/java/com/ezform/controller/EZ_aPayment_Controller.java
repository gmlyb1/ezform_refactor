package com.ezform.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezform.domain.EZ_aPaymentVO;
import com.ezform.domain.EZ_empVO;
import com.ezform.domain.EZ_notificationVO;
import com.ezform.service.EZ_aPayment_Service;
import com.ezform.service.EZ_notification_service;
import com.ezform.test.testController;

@Controller
@RequestMapping("/ez_aPayment/*")
public class EZ_aPayment_Controller {

	@Inject
	private EZ_aPayment_Service paymentService;
	
	@Inject
	private EZ_notification_service notification_service;
	
	private static final Logger logger = LoggerFactory.getLogger(testController.class);
	
	@RequestMapping(value = "/list", method= RequestMethod.GET)
	public String aPaymentListPage(EZ_aPaymentVO avo,Model model,HttpSession session) throws Exception{
		
		EZ_empVO loginUser = (EZ_empVO) session.getAttribute("resultVO");
		if(loginUser != null) {
			avo.setUser_id(loginUser.getEm_email());
		}
		List<EZ_aPaymentVO> holiList = paymentService.holidayList(avo);
		model.addAttribute("holiList", holiList);
		
		return "/ez_aPayment/list";
	}
	
	@RequestMapping(value = "/detail", method= RequestMethod.GET)
	public String aPaymentDetailPage(EZ_aPaymentVO avo,@RequestParam("ap_id") int ap_id,Model model,HttpSession session) throws Exception{
		
		EZ_aPaymentVO detail = paymentService.holidayDetail(ap_id);
		model.addAttribute("detail", detail);
		
		return "/ez_aPayment/detail";
	}
	
	@RequestMapping(value="/register" , method=RequestMethod.GET)
	public String aPaymentHolidayRegisterGET(HttpSession session, Model model) throws Exception{
		
		EZ_empVO loginUser = (EZ_empVO) session.getAttribute("resultVO");
		model.addAttribute("loginUser", loginUser);
		
		return "/ez_aPayment/register";
	}
	
	@RequestMapping(value="/register" , method=RequestMethod.POST)
	public String aPaymentHolidayRegisterPOST(EZ_aPaymentVO avo,HttpSession session) throws Exception{
		
		EZ_empVO empVO = (EZ_empVO) session.getAttribute("resultVO");
	    EZ_notificationVO notificationVO = new EZ_notificationVO();
	    notificationVO.setReceiver_name(empVO.getEm_email());
	    notificationVO.setNoti_type("휴가원");
	    notificationVO.setNoti_message("휴가원을 상신했습니다.");
	    notificationVO.setNoti_link("");
	    notification_service.insertNotification(notificationVO);
		
		paymentService.insertIndividualHoliday(avo);
		
		return "redirect:/ez_aPayment/list";
	}
	
	@RequestMapping(value = "/updateStatus" ,method=RequestMethod.POST)
	public String updateStatus(EZ_aPaymentVO avo,HttpSession session)throws Exception 
	{
		EZ_empVO empVO = (EZ_empVO) session.getAttribute("resultVO");
	    EZ_notificationVO notificationVO = new EZ_notificationVO();
	    notificationVO.setReceiver_name(empVO.getEm_email());
	    notificationVO.setNoti_type("휴가원");
	    notificationVO.setNoti_message("휴가원 결재 상태가 변경된 이력이 있습니다.");
	    notificationVO.setNoti_link("");
	    notification_service.insertNotification(notificationVO);
		paymentService.updateStatus(avo);
		
		return "redirect:/ez_aPayment/list";
	}
	
	@RequestMapping(value = "/deleteHoliday" , method=RequestMethod.POST)
	public String deleteHolidayData(EZ_aPaymentVO avo) throws Exception 
	{
		
		paymentService.deleteHoliday(avo);
		
		return "redirect:/ez_aPayment/list";
	}	
	
	
}
