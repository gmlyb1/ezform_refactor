package com.ezform.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezform.domain.EZ_empVO;
import com.ezform.domain.EZ_messageVO;
import com.ezform.service.EZ_messageService;

@Controller
@RequestMapping("/ez_message/*")
public class EZ_message_controller {

	@Inject
	private EZ_messageService messageService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String messageListPage(HttpSession session,Model model, @ModelAttribute("messageVO")EZ_messageVO messageVO) throws Exception{
		EZ_empVO loginSessionData = (EZ_empVO) session.getAttribute("resultVO");
		if(loginSessionData != null) {
			messageVO.setReceiver_name(loginSessionData.getEm_email());
		}
		List<EZ_messageVO> msg_result = messageService.findList(messageVO);
		model.addAttribute("msg_result", msg_result);
		return "/ez_message/list";
	}
	
	@RequestMapping(value = "/list", method =RequestMethod.POST)
	public String findmessageList(@ModelAttribute("messageVO")EZ_messageVO messageVO,HttpServletResponse response )throws Exception{
		return "/ez_message/list";
	}
	
	@RequestMapping(value="/sendMsg", method = RequestMethod.POST)
	public Map<String, Object> addMessageSend( @ModelAttribute EZ_messageVO messageVO) throws Exception {

	    messageService.insertMessage(messageVO);

	    Map<String, Object> result = new HashMap();
	    result.put("success", true);
	    return result;
	}
	
	
}
