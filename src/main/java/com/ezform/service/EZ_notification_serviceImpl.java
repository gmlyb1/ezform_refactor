package com.ezform.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezform.domain.EZ_notificationVO;
import com.ezform.persistence.EZ_notification_DAO;


@Service
public class EZ_notification_serviceImpl implements EZ_notification_service {

	@Inject
	private EZ_notification_DAO notificationDAO;

	@Override
	public List<EZ_notificationVO> getUnread(EZ_notificationVO nvo) {
		return notificationDAO.getUnread(nvo);
	}

	@Override
	public List<EZ_notificationVO> selectNotificationList(EZ_notificationVO notificationVO) {
		return notificationDAO.selectNotificationList(notificationVO);
	}

	@Override
	public void insertNotification(EZ_notificationVO notificationVO) {
		notificationDAO.insertNotification(notificationVO);
	}

	@Override
	public void updateAllRead(EZ_notificationVO nvo) {
		notificationDAO.updateAllRead(nvo);
	}
	
}
