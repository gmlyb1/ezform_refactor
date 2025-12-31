package com.ezform.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ezform.domain.EZ_inqVO;
import com.ezform.test.testController;

@Repository
public class EZ_inqDAOImpl implements EZ_inqDAO {

	@Inject
	private static final Logger logger = LoggerFactory.getLogger(testController.class);

	@Inject
	private SqlSession sqlSession;

	private static String namespace = "com.ezform.mapper.inq_Mapper";

	@Override
	public List<EZ_inqVO> selectInquiryList(EZ_inqVO inqVO) {
		return sqlSession.selectList(namespace+".selectInquiryList",inqVO);
	}

	@Override
	public void insertInquiry(EZ_inqVO inqVO) {
		sqlSession.insert(namespace+".insertInquiry", inqVO);
	}

	@Override
	public EZ_inqVO read(int inq_no) {
		return sqlSession.selectOne(namespace+".read", inq_no);
	}

	@Override
	public void remove(int inq_no) {
		sqlSession.delete(namespace+".remove", inq_no);
	}

	@Override
	public void modify(EZ_inqVO vo) {
		sqlSession.update(namespace+".modify", vo);
	}

}
