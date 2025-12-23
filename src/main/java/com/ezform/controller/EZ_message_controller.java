package com.ezform.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezform.domain.EZ_messageVO;
import com.ezform.service.EZ_messageService;

@Controller
@RequestMapping("/ez_message/*")
public class EZ_message_controller {

	@Inject
	private EZ_messageService messageService;
	
	@RequestMapping(value = "/list" , method=RequestMethod.GET)
	public String messageListView() throws Exception {
		return "/ez_message/list";
	}
	
	@RequestMapping(value = "/list" , method=RequestMethod.POST) 
	public String messageList(Model model,@ModelAttribute("messageVO") EZ_messageVO messageVO,HttpServletRequest request) throws Exception {
		
		List<EZ_messageVO> msg_result = messageService.findList(messageVO.getReceive_name());
		
		model.addAttribute("messageList", msg_result);
		
		return "/ez_message/list";
	}
	
	@RequestMapping(value = "/sendMsg", method=RequestMethod.POST)
	public String sendMsg(RedirectAttributes rttr,@RequestParam String flag, @ModelAttribute("messageVO")EZ_messageVO messageVO,HttpServletRequest request)throws Exception{
		messageService.sendMessage(messageVO);
	
		return "redirect:/home";
	}
	
//	@RequestMapping(value="/detail" , method=RequestMethod.GET)
//	public String sendMsgListDetail(HttpSession session,EZ_messageVO vo,Model model,HttpServletRequest request) throws Exception {
//		
//		AccountVO loginUserId = (AccountVO)session.getAttribute("member");
//		vo.setRecev_name(loginUserId.getMe_email());
//		
//		MessageVO result = messageService.sendMsgDetail(vo);
//		
//		model.addAttribute("detail", result);
//		
//		return "/message/detail";
//	}
	
	
}
