package com.ezform.persistence;

import java.util.List;

import com.ezform.domain.EZ_messageVO;

public interface EZ_messageDAO {

	public String countMessageView(String user_id);

	public List<EZ_messageVO> findList(EZ_messageVO messageVO);

	public void insertMessage(EZ_messageVO messageVO);
}
