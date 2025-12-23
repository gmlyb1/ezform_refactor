package com.ezform.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezform.domain.EZ_messageVO;
import com.ezform.persistence.EZ_messageDAO;


@Service
public class EZ_messageServiceImpl implements EZ_messageService {

	@Inject
	private EZ_messageDAO messageDAO;
	
	@Override
	public String countMessageView(String user_id) {
		return messageDAO.countMessageView(user_id);
	}

	@Override
	public List<EZ_messageVO> findList(String receive_name) {
		return messageDAO.findList(receive_name);
	}

	@Override
	public void sendMessage(EZ_messageVO messageVO) {
		messageDAO.sendMessage(messageVO);
	}

}
