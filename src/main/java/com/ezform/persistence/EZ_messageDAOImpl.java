package com.ezform.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ezform.domain.EZ_messageVO;

@Repository
public class EZ_messageDAOImpl implements EZ_messageDAO {

	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = "com.ezform.mapper.message_Mapper";
	
	@Override
	public String countMessageView(String user_id) {
		return sqlSession.selectOne(namespace + ".countMessageView", user_id);
	}

	@Override
	public List<EZ_messageVO> findList(EZ_messageVO messageVO) {
		return sqlSession.selectList(namespace + ".findList", messageVO);
	}

	@Override
	public void insertMessage(EZ_messageVO messageVO) {
		sqlSession.insert(namespace + ".insertMessage", messageVO);
	}

	@Override
	public List<EZ_messageVO> getUnreadMessages(Integer em_name) {
		return sqlSession.selectList(namespace + ".getUnreadMessages", em_name);
	}

	@Override
	public void markAsRead(Integer ms_seq) {
		sqlSession.update(namespace + ".markAsRead", ms_seq);
	}

	@Override
	public EZ_messageVO getMessageById(Integer ms_seq) {
		return sqlSession.selectOne(namespace + ".getMessageById", ms_seq);
	}

	@Override
	public List<EZ_messageVO> receiveList(EZ_messageVO messageVO) {
		return sqlSession.selectList(namespace + ".receiveList", messageVO);
	}

}
