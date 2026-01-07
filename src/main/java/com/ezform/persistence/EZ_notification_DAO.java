package com.ezform.persistence;

import java.util.List;

import com.ezform.domain.EZ_notificationVO;

public interface EZ_notification_DAO {

	public List<EZ_notificationVO> getUnread(EZ_notificationVO nvo);

	public List<EZ_notificationVO> selectNotificationList(EZ_notificationVO notificationVO);

	public void insertNotification(EZ_notificationVO notificationVO);

	public void updateAllRead(EZ_notificationVO nvo);

}
