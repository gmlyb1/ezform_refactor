package com.ezform.persistence;

import java.util.List;

import com.ezform.domain.EZ_notificationVO;

public interface EZ_notification_DAO {

	public List<EZ_notificationVO> getUnread(int em_id);

	public List<EZ_notificationVO> selectNotificationList(EZ_notificationVO notificationVO);

}
