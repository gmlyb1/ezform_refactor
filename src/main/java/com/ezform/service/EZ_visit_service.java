package com.ezform.service;

import com.ezform.domain.EZ_visitVO;

public interface EZ_visit_service {

	public void insertVisit(EZ_visitVO visitVO);

	public int getVisitCount(EZ_visitVO visitVO);

	public int getTodayVisitCount(EZ_visitVO visitVO);

}
