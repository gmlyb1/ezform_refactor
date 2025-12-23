package com.ezform.service;

import java.util.List;

import com.ezform.domain.EZ_messageVO;

public interface EZ_messageService {

	public String countMessageView(String user_id);

	public List<EZ_messageVO> findList(String receive_name);

	public void sendMessage(EZ_messageVO messageVO);
}
