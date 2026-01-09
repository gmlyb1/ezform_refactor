package com.ezform.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezform.domain.EZ_materialVO;
import com.ezform.test.testController;

@Repository
public class EZ_material_DAOImpl implements EZ_material_DAO {

	@Autowired
	private SqlSession sqlSession;
	
	private static final Logger logger = LoggerFactory.getLogger(testController.class);
	private static final String namespace = "com.ezform.mapper.material_Mapper";
	@Override
	public List<EZ_materialVO> selectBoardList(EZ_materialVO materialVO) {
		return sqlSession.selectList(namespace+".selectBoardList", materialVO);
	}
	@Override
	public void insertBoard(EZ_materialVO materialVO) {
		sqlSession.insert(namespace+".insertBoard", materialVO);
	}
	@Override
	public EZ_materialVO read(int ma_seq) {
		return sqlSession.selectOne(namespace+".read", ma_seq);
	}
	@Override
	public void modify(EZ_materialVO materialVO) {
		sqlSession.update(namespace+".modify", materialVO);
	}
	@Override
	public void delete(EZ_materialVO materialVO) {
		sqlSession.delete(namespace+".delete", materialVO);
	}
}
