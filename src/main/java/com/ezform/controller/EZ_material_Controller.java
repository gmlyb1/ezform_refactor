package com.ezform.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
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

import com.ezform.domain.EZ_empVO;
import com.ezform.domain.EZ_materialVO;
import com.ezform.service.EZ_material_service;
import com.ezform.test.testController;

@Controller
@RequestMapping("/ez_material/*")
public class EZ_material_Controller {

	private static final Logger logger =
			LoggerFactory.getLogger(testController.class);
	
	@Inject
	private EZ_material_service material_service;
	
	@RequestMapping(value = "/list" , method=RequestMethod.GET)
	public String materialBoardList(EZ_materialVO materialVO,Model model) throws Exception {
		
		List<EZ_materialVO> bList = material_service.selectBoardList(materialVO);
		model.addAttribute("bList", bList);
		
		return "/ez_material/list";
	}
	
	@RequestMapping(value = "/read" , method=RequestMethod.GET)
	public String materialDetail(@RequestParam("ma_seq")int ma_seq, Model model) throws Exception{
		
		EZ_materialVO mVO = material_service.read(ma_seq);
		model.addAttribute("mVO", mVO);
		
		return "/ez_material/read";
	}
	
	@RequestMapping(value = "/register" , method=RequestMethod.GET)
	public String materialRegisterPage()throws Exception {
		return "/ez_material/register";
	}
	
	@RequestMapping(value = "/register" , method=RequestMethod.POST)
	public void materialRegisterPOST(EZ_materialVO materialVO,HttpSession session, HttpServletResponse response,HttpServletRequest request ) throws Exception {
		
	// 세션
	EZ_empVO evo = (EZ_empVO)session.getAttribute("resultVO");
	
	int chkAdmin = evo.getEm_id();
	
	response.setContentType("text/html; charset=utf-8");
	PrintWriter out = response.getWriter();
	
		// 파일 업로드 처리
		String ma_file = null;
		MultipartFile uploadFile = materialVO.getUploadFile();
		
		if(!uploadFile.isEmpty()) {
			String originalFileName = uploadFile.getOriginalFilename();
			String ext = FilenameUtils.getExtension(originalFileName);
			UUID uuid = UUID.randomUUID();
			ma_file = uuid+"."+ext;
			
			String path = request.getSession().getServletContext().getRealPath("/"); // 절대 경로
			path += "upload\\boardUpload\\";
			
			String temp_path = path+ma_file;
			
			logger.info("파일명 : "+ma_file);
			logger.info("path : "+temp_path);
			uploadFile.transferTo(new File(temp_path));
		}
		
		materialVO.setMa_file(ma_file);
		material_service.insertBoard(materialVO);
				
		out.print("<script>alert('등록 완료'); location.href='/ezform/ez_material/list';</script>");
		out.flush();
	}
		
	
	@RequestMapping(value = "/modify" , method=RequestMethod.GET)
	public String materialModifyPage(@RequestParam("ma_seq") int ma_seq,EZ_materialVO materialVO,Model model) throws Exception{
		
		EZ_materialVO mVO = material_service.read(ma_seq);
		model.addAttribute("mVO", mVO);
		
		return "/ez_material/modify";
	}
	
	@RequestMapping(value = "/modify" , method=RequestMethod.POST)
	public void materialModifyPOST(EZ_materialVO materialVO,HttpSession session, HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		// 세션
		EZ_empVO evo = (EZ_empVO)session.getAttribute("resultVO");
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		// 파일 업로드 처리
		String ma_file = null;
		MultipartFile uploadFile = materialVO.getUploadFile();
		
		if(!uploadFile.isEmpty()) {
			String originalFileName = uploadFile.getOriginalFilename();
			String ext = FilenameUtils.getExtension(originalFileName);
			UUID uuid = UUID.randomUUID();
			ma_file = uuid+"."+ext;
			
			String path = request.getSession().getServletContext().getRealPath("/"); // 절대 경로
			path += "upload\\boardUpload\\";
			
			String temp_path = path+ma_file;
			
			logger.info("파일명 : "+ma_file);
			logger.info("path : "+temp_path);
			uploadFile.transferTo(new File(temp_path));
		}
		materialVO.setMa_file(ma_file);
		material_service.insertBoard(materialVO);
		
		out.println("<script>alert('수정 완료'); location.href='/ezform/ez_material/list';</script>");
		out.flush();
	}
		
	
	@RequestMapping(value="/delete" ,method=RequestMethod.POST)
	public String materialDeletePost(EZ_materialVO materialVO) throws Exception{
		
		material_service.delete(materialVO);
		
		return "redirect:/ez_material/list";
	}
	
	@RequestMapping(value="/filedown", method=RequestMethod.GET)
	public void material_fileDown(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 파일 다운로드
		
		String fileName = request.getParameter("fileName");
		String realFileName = "";
		
		try {
			String browser = request.getHeader("User-Agent");
			// 파일 인코딩
			if (browser.contains("MSIE") || browser.contains("Trident") || browser.contains("Chrome")) {
				fileName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");
			}
			else {
				fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
			}
			
		} catch(UnsupportedEncodingException ex) {
			logger.info("UnsupportedEncodingException");
		}
		
		String path = request.getSession().getServletContext().getRealPath("/"); // 절대 경로
		path += "upload\\boardUpload\\";
		
		realFileName = path+fileName;
		
		File tmpFile = new File(realFileName);
		
		if (!tmpFile.exists()) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>alert('다운로드 오류! 관리자에게 문의하세요'); location.href='/ezform/ez_material/list';</script>");
			out.flush();
		}
			
		// 파일명 지정
		response.setContentType("application/octer-stream");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		
		try {
			OutputStream os = response.getOutputStream();
			FileInputStream fis = new FileInputStream(realFileName);
			
			int Ncnt = 0;
			byte[] bytes = new byte[512];
			
			while ((Ncnt = fis.read(bytes)) != -1) {
				os.write(bytes,0,Ncnt);
			}
			
			fis.close();
			os.close();
			
		} catch (Exception e) {
			logger.info("FileNotFoundException : "+e);
		}
	}
	
	
	
}
