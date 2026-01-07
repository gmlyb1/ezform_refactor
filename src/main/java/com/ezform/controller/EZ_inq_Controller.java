package com.ezform.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezform.domain.EZ_empVO;
import com.ezform.domain.EZ_inqVO;
import com.ezform.domain.EZ_notificationVO;
import com.ezform.service.EZ_inq_Service;
import com.ezform.service.EZ_notification_service;
import com.ezform.test.testController;

@Controller
@RequestMapping("/ez_inq/*")
public class EZ_inq_Controller {

	@Inject
	private EZ_inq_Service inq_Service;
	
	@Inject
	private EZ_notification_service notification_service;
	
	private static final Logger logger = LoggerFactory.getLogger(testController.class);
	
	@RequestMapping(value = "/list", method=RequestMethod.GET)
	public String inquiryListPage(EZ_inqVO inqVO, Model model,HttpSession session) throws Exception{
		EZ_empVO evo = (EZ_empVO) session.getAttribute("resultVO");
		if(evo != null) {
			inqVO.setEm_email(evo.getEm_email());
		}
		
		Integer em_id = evo.getEm_id();
		List<EZ_inqVO> inquiryList;
		if(em_id == 9999) {
			inquiryList = inq_Service.selectInquiryAdminList(inqVO);
		}else {
			inquiryList = inq_Service.selectInquiryList(inqVO);
		}
		model.addAttribute("inquiryList", inquiryList);
		model.addAttribute("em_id", evo.getEm_id());
		return "/ez_inq/list";
	}
	
	@RequestMapping(value = "/read" , method=RequestMethod.GET)
	public String inquiryDetailPage(@RequestParam("inq_no") int inq_no,Model model, HttpSession session) throws Exception {
		
		EZ_inqVO inqVO = inq_Service.read(inq_no); 
		model.addAttribute("vo", inqVO);
		
		EZ_empVO evo = (EZ_empVO) session.getAttribute("resultVO");
		if(evo != null) {
			model.addAttribute("isWriter", evo.getEm_email());
		}
		
		return "/ez_inq/read";
	}
	
	@RequestMapping(value = "/register", method=RequestMethod.GET)
	public String insertInquiryPage(EZ_inqVO inqVO) throws Exception {
		return "/ez_inq/register";
	}
	
	@RequestMapping(value = "/register", method=RequestMethod.POST)
	public String insertInquiry(EZ_inqVO inqVO, RedirectAttributes rttr,HttpSession session, HttpServletRequest request) throws Exception
	{
		EZ_empVO evo = (EZ_empVO) session.getAttribute("resultVO");
		if(evo != null) {
			inqVO.setInq_dname(evo.getEm_dept());
			inqVO.setEm_email(evo.getEm_email());
			inqVO.setInq_name(evo.getEm_name());
		}
		
		String inq_file = null;
		MultipartFile uploadFile = inqVO.getUploadFile();

		if (!uploadFile.isEmpty()) {
			String originalFileName = uploadFile.getOriginalFilename();
			String ext = FilenameUtils.getExtension(originalFileName);
			UUID uuid = UUID.randomUUID();
			inq_file = uuid + "." + ext;

			// 절대경로
			String path = request.getSession().getServletContext().getRealPath("/");
			path += "upload\\boardUpload\\";

			String temp_path = path + inq_file;

			logger.info("파일명 : " + inq_file);
			logger.info("path : " + temp_path);
			uploadFile.transferTo(new File(temp_path));

		}
		inqVO.setInq_file(inq_file);
		
		EZ_empVO empVO = (EZ_empVO) session.getAttribute("resultVO");
	    EZ_notificationVO notificationVO = new EZ_notificationVO();
	    notificationVO.setReceiver_name(empVO.getEm_email());
	    notificationVO.setNoti_type("게시판");
	    notificationVO.setNoti_message("1:1문의 게시판 글을 작성했습니다.");
	    notificationVO.setNoti_link("");
	    notification_service.insertNotification(notificationVO);
		
		inq_Service.insertInquiry(inqVO);
		
		return "redirect:/ez_inq/list";
	}
	
	// *글수정 GET
		@RequestMapping(value = "/modify", method = RequestMethod.GET)
		public void modifyGET(@RequestParam("inq_no") int inq_no, Model model, HttpSession session) throws Exception {
			model.addAttribute("vo", inq_Service.read(inq_no));
		}

		// * 글수정(POST)
		@RequestMapping(value = "/modify", method = RequestMethod.POST)
		public void modifyPOST(EZ_inqVO vo, HttpSession session, HttpServletResponse response, HttpServletRequest request)
				throws Exception {
			// 세션
			EZ_empVO evo = (EZ_empVO) session.getAttribute("resultVO");
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			if (evo == null) {
				out.print("<script>location.href='/ezform/logout';</script>");
				out.flush();
			}
			
			String inq_file = null;
			MultipartFile uploadFile = vo.getUploadFile();

			if (!uploadFile.isEmpty()) {
				String originalFileName = uploadFile.getOriginalFilename();
				String ext = FilenameUtils.getExtension(originalFileName);
				UUID uuid = UUID.randomUUID();
				inq_file = uuid + "." + ext;

				String path = request.getSession().getServletContext().getRealPath("/"); // 절대 경로
				path += "upload\\boardUpload\\";

				String temp_path = path + inq_file;

				uploadFile.transferTo(new File(temp_path));
			}
			vo.setInq_file(inq_file);
			inq_Service.modify(vo);
			
			out.print("<script>alert('수정이 완료 되었습니다.'); location.href='/ezform/ez_inq/list';</script>");
			out.flush();
		}
	
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public void removeGET(@RequestParam("inq_no") int inq_no, HttpServletResponse response) throws Exception {
		// 서비스
		inq_Service.remove(inq_no);

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		out.print("<script>alert('삭제가 완료 되었습니다.'); location.href='/ezform/ez_inq/list';</script>");
		out.flush();
	}
	
	@RequestMapping("/userpic")
	 public String listAttach(HttpSession session,HttpServletResponse response, HttpServletRequest request, @RequestParam("imgName") String imgName) throws Exception {
		
		response.setContentType("image/gif");
		ServletOutputStream bout = response.getOutputStream();
		
		String imgPath = request.getSession().getServletContext().getRealPath("/");
		imgPath += "upload\\boardUpload\\";
		imgPath += imgName;
	
		// String exts = {".jpg",".png",".jpeg",".gif"};
		
		FileInputStream f = new FileInputStream(imgPath);
		
		int len;
		byte[] buffer = new byte[10];
		
		while ((len = f.read(buffer)) != -1)
			bout.write(buffer,0,len);
		
		return null;
	 }
	
	@RequestMapping("/viewImg")
	public void viewImg(@RequestParam("imgName") String imgName, Model model) throws Exception {
		
		model.addAttribute("imgName",imgName);
	}
}
