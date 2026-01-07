package com.ezform.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezform.controller.EZ_mem_Controller;
import com.ezform.controller.EZ_notification_Controller;
import com.ezform.domain.EZ_notificationVO;

@Repository
public class EZ_notification_DAOImpl implements EZ_notification_DAO {

	@Autowired
	private SqlSession sqlSession;
	
	private static final Logger logger = LoggerFactory.getLogger(EZ_notification_Controller.class);
	
	private static final String namespace = "com.ezform.mapper.notification_Mapper";

	@Override
	public List<EZ_notificationVO> getUnread(EZ_notificationVO nvo) {
		return sqlSession.selectList(namespace+".getUnread",nvo);
	}

	@Override
	public List<EZ_notificationVO> selectNotificationList(EZ_notificationVO notificationVO) {
		return sqlSession.selectList(namespace+".selectNotificationList",notificationVO);
	}

	@Override
	public void insertNotification(EZ_notificationVO notificationVO) {
		sqlSession.insert(namespace+".insertNotification",notificationVO);
	}

	@Override
	public void updateAllRead(EZ_notificationVO nvo) {
		sqlSession.update(namespace+".updateAllRead", nvo);
	}
}
