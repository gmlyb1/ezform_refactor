package com.ezform.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezform.domain.EZ_board_comVO;
import com.ezform.domain.EZ_empVO;
import com.ezform.service.EZ_bdcom_Service;
import com.ezform.test.testController;

@Controller
@RequestMapping("/comment/*")
public class EZ_bdcom_Controller {
	
	@Inject
	private EZ_bdcom_Service service;
	
	private static final Logger logger = LoggerFactory.getLogger(testController.class);
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addPOST(EZ_board_comVO cvo, HttpSession session) throws Exception {
		// content bnum만 받아옴
		// 세션
		EZ_empVO evo = (EZ_empVO)session.getAttribute("resultVO");
		
		cvo.setCom_dept(evo.getEm_dept());
		cvo.setCom_name(evo.getEm_name());
		cvo.setCom_id(evo.getEm_id());
		
		service.write(cvo);
		
		return "redirect:/board/read?nohit=true&cm_bnum="+cvo.getCom_bnum();
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modifyComment(EZ_board_comVO cvo,
			@RequestParam int com_cnum,
	        @RequestParam String com_content,
	        @RequestParam int com_bnum) throws Exception {
		
		service.modify(cvo);
		
		return "redirect:/board/read?cm_bnum="+com_bnum;
	}

	@RequestMapping(value="/remove", method=RequestMethod.GET)
	public String removeGET(@RequestParam("com_cnum") int com_cnum, @RequestParam("com_bnum") int com_bnum) throws Exception {
		service.delete(com_cnum);
		
		return "redirect:/board/read?cm_bnum="+com_bnum;
	}
}
