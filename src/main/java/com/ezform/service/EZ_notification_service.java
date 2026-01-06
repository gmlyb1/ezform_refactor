package com.ezform.service;

import java.util.List;

import com.ezform.domain.EZ_notificationVO;

public interface EZ_notification_service {

	public List<EZ_notificationVO> getUnread(int em_id);

	public List<EZ_notificationVO> selectNotificationList(EZ_notificationVO notificationVO);

}
