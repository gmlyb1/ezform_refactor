package com.ezform.service;

import java.util.List;

import com.ezform.domain.EZ_messageVO;

public interface EZ_messageService {

	public String countMessageView(String user_id);

	public List<EZ_messageVO> findList(EZ_messageVO messageVO);

	public void insertMessage(EZ_messageVO messageVO);

	public List<EZ_messageVO> getUnreadMessages(Integer em_name);

	public void markAsRead(Integer ms_seq);

	public EZ_messageVO getMessageById(Integer ms_seq);

	public List<EZ_messageVO> receiveList(EZ_messageVO messageVO);

}
