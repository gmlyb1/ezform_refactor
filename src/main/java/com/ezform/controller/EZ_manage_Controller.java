package com.ezform.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezform.common.SessionCounter;
import com.ezform.domain.EZ_aPaymentVO;
import com.ezform.domain.EZ_calendarVO;
import com.ezform.domain.EZ_empVO;
import com.ezform.domain.EZ_inqVO;
import com.ezform.domain.EZ_mailVO;
import com.ezform.domain.EZ_messageVO;
import com.ezform.domain.EZ_notificationVO;
import com.ezform.domain.EZ_visitVO;
import com.ezform.service.EZ_aPayment_Service;
import com.ezform.service.EZ_bd_Service;
import com.ezform.service.EZ_cal_Service;
import com.ezform.service.EZ_emp_Service;
import com.ezform.service.EZ_inq_Service;
import com.ezform.service.EZ_mail_Service;
import com.ezform.service.EZ_mem_Service;
import com.ezform.service.EZ_messageService;
import com.ezform.service.EZ_noti_Service;
import com.ezform.service.EZ_notification_service;
import com.ezform.service.EZ_visit_service;

@Controller
@RequestMapping("/ez_manage/*")
public class EZ_manage_Controller {

	@Inject
	private EZ_mem_Service mem_service;
	@Inject
	private EZ_noti_Service noti_service;
	@Inject
	private EZ_bd_Service bd_service;
	@Inject
	private EZ_emp_Service emp_service;
	@Inject
	private EZ_visit_service visit_service;
	@Inject
	private EZ_messageService message_service;
	@Inject 
	private EZ_cal_Service cal_service;
	@Inject 
	private EZ_aPayment_Service apayment_service;
	@Inject
	private EZ_mail_Service mail_service;
	@Inject
	private EZ_notification_service notification_service;
	@Inject 
	private EZ_inq_Service inq_Service;
	
	@RequestMapping(value = "/home", method=RequestMethod.GET)
	public String ManageHomeGET(HttpServletRequest request,Model model,HttpSession session,EZ_visitVO visitVO,EZ_messageVO mvo,EZ_calendarVO cvo,EZ_aPaymentVO avo,EZ_mailVO mailVO,EZ_notificationVO notificationVO,EZ_inqVO inqVO) throws Exception {
		
		EZ_empVO loginSession = (EZ_empVO) session.getAttribute("resultVO");
		Integer em_id = loginSession.getEm_id();
		
		if(em_id != 9999) {
			return "redirect:/main";
		}
		
		int total = visit_service.getVisitCount(visitVO);
		int today = visit_service.getTodayVisitCount(visitVO);
		
		EZ_empVO empVO = new EZ_empVO();
		model.addAttribute("messageMemberList", mem_service.messageMemberList(empVO));
		model.addAttribute("total", total);
		model.addAttribute("today", today);
		
//		model.addAttribute("loginSession", "loginSession");
		// 현재 접속자수(세션값)
		model.addAttribute("currentUserCount", SessionCounter.getCount());
		// 커뮤니티 리스트
		model.addAttribute("boardList", bd_service.listCri());
		// 공지사항 리스트
		model.addAttribute("notiList", noti_service.listALL());
		// 근태 리스트
		model.addAttribute("wslist", emp_service.wstatusListEmp());
		// 안읽은 쪽지 개수
		if(loginSession != null) {
			mvo.setReceiver_name(loginSession.getEm_email());
		}
		model.addAttribute("unreadMsg", message_service.findList(mvo).size());
		// 오늘 일정 개수
		model.addAttribute("todayEventList", cal_service.selectEventTodayList(cvo).size());
		// 미결재 개수
		if(loginSession != null) {
			avo.setUser_id(loginSession.getEm_email());
		}
		model.addAttribute("unApaymentList", apayment_service.unApaymentList(avo).size());
		// 안읽은 메일
		if(loginSession != null) {
			mailVO.setMail_id(loginSession.getEm_email());
		}
		model.addAttribute("unReadMailList", mail_service.unreadMailList(mailVO).size());
		// 안읽은 알람
		if(loginSession != null) {
			notificationVO.setReceiver_name(loginSession.getEm_email());
		}
		model.addAttribute("unreadNotificationList", notification_service.getUnreadMessageList(notificationVO).size());
		
		if(loginSession != null) {
			inqVO.setEm_email(loginSession.getEm_email());
		}
		model.addAttribute("unCheckInquiryList", inq_Service.unCheckInquiryList(inqVO).size());
		
		int currentlyAtWork = 0;
		List<EZ_empVO> list = mem_service.memAndStatusList();
		for (EZ_empVO status : list) {
		    if (status.getEz_workVO() != null && "출근".equals(status.getEz_workVO().getWork_status())) { // getStatus()는 실제 필드명으로 변경
		        currentlyAtWork++;
		    }
		}
		model.addAttribute("currentStatusList", currentlyAtWork);
		
		return "/ez_manage/home";
	}
}
