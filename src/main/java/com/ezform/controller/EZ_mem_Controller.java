package com.ezform.controller;

import java.io.File;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ezform.common.SessionCounter;
import com.ezform.domain.EZ_aPaymentVO;
import com.ezform.domain.EZ_calendarVO;
import com.ezform.domain.EZ_empVO;
import com.ezform.domain.EZ_inqVO;
import com.ezform.domain.EZ_mailVO;
import com.ezform.domain.EZ_messageVO;
import com.ezform.domain.EZ_notificationVO;
import com.ezform.domain.EZ_visitVO;
import com.ezform.domain.EZ_workVO;
import com.ezform.service.EZ_mem_Service;
import com.ezform.service.EZ_messageService;
import com.ezform.service.EZ_noti_Service;
import com.ezform.service.EZ_notification_service;
import com.ezform.service.EZ_visit_service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ezform.service.EZ_aPayment_Service;
import com.ezform.service.EZ_bd_Service;
import com.ezform.service.EZ_cal_Service;
import com.ezform.service.EZ_emp_Service;
import com.ezform.service.EZ_inq_Service;
import com.ezform.service.EZ_mail_Service;

@Controller
public class EZ_mem_Controller {

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


	private static final Logger logger = LoggerFactory.getLogger(EZ_mem_Controller.class);

	// 메인페이지
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String mainPageGET(HttpServletRequest request,Model model,HttpSession session,EZ_visitVO visitVO,EZ_messageVO mvo,EZ_calendarVO cvo,EZ_aPaymentVO avo,EZ_mailVO mailVO,EZ_notificationVO notificationVO,EZ_inqVO inqVO) throws Exception {
		
		
		String ip = request.getRemoteAddr();
		String userAgent = request.getHeader("User-Agent");
		
		visitVO.setIp_address(ip);
		visitVO.setUser_agent(userAgent);
		visit_service.insertVisit(visitVO);
		
		int total = visit_service.getVisitCount(visitVO);
		int today = visit_service.getTodayVisitCount(visitVO);
		
		EZ_empVO loginSession = (EZ_empVO)session.getAttribute("resultVO");
		
		EZ_empVO empVO = new EZ_empVO();
		
		if(loginSession != null) {
			empVO.setEm_email(loginSession.getEm_email());
		}
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
		
		return "index";
	}
	

	// 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public void logoutGET(HttpSession session, HttpServletResponse response) throws Exception {
		session.invalidate();
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.print("<script>alert('로그아웃 되었습니다'); location.href='./login';</script>");
		out.flush();
	}
	
	
	
	// 로그인 - 페이지 이동
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPageGET() throws Exception {

		return "/ez_mem/loginPage";
	}

	// 로그인 - 로그인 처리
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPagePOST(EZ_empVO evo, HttpSession session, Model model, HttpServletResponse response) throws Exception {

		// DB에 이메일이 존재하는지 확인
		List loginList = mem_service.memLogin(evo);

		EZ_empVO resultVO = (EZ_empVO) loginList.get(0);
		Integer result = (Integer) loginList.get(1);

		if(resultVO != null && resultVO.getEm_request() == 0) {
			response.setContentType("text/html; charset=UTF-8");
		    PrintWriter out = response.getWriter();
		    out.println("<script>alert('아직 승인 되지 않은 계정입니다.'); history.back();</script>");
		    out.flush();
		    return null;
		}
		
		// result 결과 = (-2 : 이메일존재X) / (-1 : 패스워드 오류) / (0 : null) / (1 : 로그인 성공)
		
		// 로그인 결과값
		session.setAttribute("result", result);

		if (resultVO != null) {
			// 회원정보 세션처리
			session.setAttribute("resultVO", resultVO);
			session.setAttribute("em_id", resultVO.getEm_id());
		
		}
		

		return "redirect:./main";
	}

	@RequestMapping(value = "/joinRequest", method = RequestMethod.GET)
	public String memberJoinRequestGET() throws Exception {
		
		return "/ez_mem/joinRequest";
	}
	
	@RequestMapping(value = "/joinRequest", method = RequestMethod.POST)
	public String memberJoinRequestPOST(EZ_empVO empVO,HttpServletResponse response) throws Exception {
		
		if(empVO != null && empVO.getEm_id() == 9999) {
			response.setContentType("text/html; charset=UTF-8");
		    PrintWriter out = response.getWriter();
		    out.println("<script>alert('직원번호 9999 는 사용할수 없는 번호입니다'); history.back();</script>");
		    out.flush();
		    return null;
		}
		
		mem_service.insertJoinRequest(empVO);
		
		return "redirect:./login";
	}
	
	// 사원생성 - 페이지 이동
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String joinPageGET() throws Exception {


		return "/ez_mem/joinPage";
	}

	// 사원생성 - 처리
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinPagePOST(MultipartHttpServletRequest multiRequest, Model model) throws Exception {

		
		// 사원ID 중복체크 확인
		int checkID = mem_service.checkEmpId(Integer.parseInt(multiRequest.getParameter("em_id")));
		
		if(checkID == 1) {
			model.addAttribute("checkID", checkID);
		} else {
			model.addAttribute("checkID", checkID);
			
			// 해당 파일 가져오기
			MultipartFile file = multiRequest.getFile("em_image");
			
//			System.out.println("파일값 : " + file.getOriginalFilename());
			 
			// 절대경로 설정
			String file_path = multiRequest.getSession().getServletContext().getRealPath("/resources/upload/mem_Image");

			EZ_empVO evo = new EZ_empVO();
			
			evo.setEm_id(Integer.parseInt(multiRequest.getParameter("em_id")));
			evo.setEm_email((String) multiRequest.getParameter("em_email"));
			evo.setEm_name((String) multiRequest.getParameter("em_name"));
			evo.setEm_pw((String) multiRequest.getParameter("em_pw"));
			evo.setEm_extension(multiRequest.getParameter("em_extension"));
			evo.setEm_dept((String) multiRequest.getParameter("em_dept"));
			evo.setEm_posi((String) multiRequest.getParameter("em_posi"));

			// 파일 업로드
			if(file.getOriginalFilename() != "") {
				
				// 중복 파일명 방지
				String uuid = UUID.randomUUID().toString();
				
				String file_newName = uuid + "_" + file.getOriginalFilename();
				file.transferTo(new File(file_path + "/" + file_newName));
				
				
				evo.setEm_image(file_newName);
			}
			// 회원가입하기
			mem_service.memJoin(evo);

		}
		return "redirect:./join";
	}
	
	
	// 회원정보
	@RequestMapping(value = "/infoMember", method = RequestMethod.GET)
	public String mem_infoGET(HttpSession session) throws Exception {
		Integer em_id = (Integer)session.getAttribute("em_id");
		
//		EZ_empVO resultVO = mem_service.memInfo(em_id);
		EZ_empVO resultVO = null;
		if(em_id != null) {
			resultVO = mem_service.memInfo(em_id);
		}
		
		session.setAttribute("resultVO", resultVO);
		
		return "/ez_mem/infoMember";
	}
	
	
	// 회원정보 수정 GET
	@RequestMapping(value = "/updateMember", method = RequestMethod.GET)
	public String mem_updateGET() throws Exception {
		
		return "/ez_mem/updateMember";
	}
	
	// 회원정보 수정 POST
	@RequestMapping(value = "/updateMember", method = RequestMethod.POST)
	public String mem_updatePOST(MultipartHttpServletRequest multiRequest, Model model) throws Exception {
		
		String em_image = "";
		
		// 해당 파일 가져오기
		MultipartFile file = multiRequest.getFile("em_image");
		
		if(file.getOriginalFilename() == "") {
			System.out.print("11 : 기존파일이 넣기");
			em_image = multiRequest.getParameter("default_file");
		} else {
			System.out.print("11 : 변경파일이 넣기");
			
			// 절대경로 설정
			String file_path = multiRequest.getSession().getServletContext().getRealPath("/resources/upload/mem_Image");

			// 중복 파일명 방지
			String uuid = UUID.randomUUID().toString();

			// 파일 업로드
			em_image = uuid + "_" + file.getOriginalFilename();
			
			file.transferTo(new File(file_path + "/" + em_image));
				
			// 기존 파일 삭제
			File f = new File(file_path + "/" + multiRequest.getParameter("default_file"));
			if(f.exists()) {
				f.delete();
			}
		}
		
		EZ_empVO evo = new EZ_empVO();

		evo.setEm_id(Integer.parseInt((String)multiRequest.getParameter("em_id")));
		evo.setEm_email((String) multiRequest.getParameter("em_email"));
		evo.setEm_name((String) multiRequest.getParameter("em_name"));
		evo.setEm_pw((String) multiRequest.getParameter("em_pw"));
		evo.setEm_extension(multiRequest.getParameter("em_extension"));
		evo.setEm_dept((String) multiRequest.getParameter("em_dept"));
		evo.setEm_posi((String) multiRequest.getParameter("em_posi"));
		evo.setEm_image(em_image);

//		System.out.println(" C : 파라미터 값" + evo);
		
		int result = mem_service.memUpdate(evo);
		
		model.addAttribute("updateMem_result", result);
		
		return "redirect:./infoMember";
	}
	
	// 회원탈퇴 GET
	@RequestMapping(value = "/deleteMember", method = RequestMethod.GET)
	public String mem_deleteGET(Model model) throws Exception {
		
		// 가입된 모든 회원 리스트 가져오기
		List<EZ_empVO> listEmp = mem_service.memList();
		
		model.addAttribute("listEmp", listEmp);
		
		return "/ez_mem/deleteMember";
	}
	
	
	// 회원탈퇴 POST
	@RequestMapping(value = "/deleteMember", method = RequestMethod.POST)
	public String mem_deletePOST(MultipartHttpServletRequest multiRequest, HttpSession session, Model model) throws Exception {
		
//		System.out.println(" C : mem_deletePOST() 실행");
		
		EZ_empVO evo = new EZ_empVO();
		
		evo.setEm_id(Integer.parseInt((String)multiRequest.getParameter("em_id")));
		evo.setEm_email((String) multiRequest.getParameter("em_email"));
		evo.setEm_pw((String) multiRequest.getParameter("em_pw"));
		evo.setEm_image((String)multiRequest.getParameter("default_file"));
		
		int result = mem_service.memDelete(evo);
	
		// 회원탈퇴 결과 완료 시
		if(result == 1) {
			// 기존 회원 이미지 파일 삭제
			String file_path = multiRequest.getSession().getServletContext().getRealPath("/resources/upload/mem_Image");
			File f = new File(file_path + "/" + multiRequest.getParameter("default_file"));
			if(f.exists()) {
				f.delete();
			}
		}
	
		model.addAttribute("deleteMem_result", result);
			
		return "redirect:./deleteMember";

	}
	
	
	@RequestMapping(value = "/ez_mem/list" , method=RequestMethod.GET)
	public String memberListGET(Model model, HttpSession session,EZ_workVO wvo, EZ_empVO evo) throws Exception {
		
		evo.setEz_workVO(wvo);
		List<EZ_empVO> statusList = mem_service.memAndStatusList();
		model.addAttribute("memList", statusList);
		
		return "/ez_mem/list";
	}
	

}