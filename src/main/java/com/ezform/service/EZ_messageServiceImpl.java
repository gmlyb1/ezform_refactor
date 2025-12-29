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
	public List<EZ_messageVO> findList(EZ_messageVO messageVO) {
		return messageDAO.findList(messageVO);
	}

	@Override
	public void insertMessage(EZ_messageVO messageVO) {
		messageDAO.insertMessage(messageVO);
	}

	@Override
	public List<EZ_messageVO> getUnreadMessages(Integer em_name) {
		return messageDAO.getUnreadMessages(em_name);
	}

	@Override
	public void markAsRead(Integer ms_seq) {
		messageDAO.markAsRead(ms_seq);
	}

	@Override
	public EZ_messageVO getMessageById(Integer ms_seq) {
		return messageDAO.getMessageById(ms_seq);
	}

	@Override
	public List<EZ_messageVO> receiveList(EZ_messageVO messageVO) {
		// TODO Auto-generated method stub
		return messageDAO.receiveList(messageVO);
	}

	@Override
	public void deleteSelected(List<Integer> msSeqs) {
		messageDAO.deleteSelected(msSeqs);
	}

}
