package com.ezform.persistence;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezform.controller.EZ_mem_Controller;
import com.ezform.domain.EZ_visitVO;

@Repository
public class EZ_visitDAOImpl implements EZ_visitDAO {

	@Autowired
	private SqlSession sqlSession;
	
	private static final Logger logger = LoggerFactory.getLogger(EZ_mem_Controller.class);
	
	private static final String namespace = "com.ezform.mapper.visit_Mapper";
	
	@Override
	public void insertVisit(EZ_visitVO visitVO) {
		sqlSession.insert(namespace+".insertVisit",visitVO);
	}

	@Override
	public int getVisitCount(EZ_visitVO visitVO) {
		return sqlSession.selectOne(namespace+".getVisitCount",visitVO);
	}

	@Override
	public int getTodayVisitCount(EZ_visitVO visitVO) {
		return sqlSession.selectOne(namespace+".getTodayVisitCount",visitVO);
	}

}
