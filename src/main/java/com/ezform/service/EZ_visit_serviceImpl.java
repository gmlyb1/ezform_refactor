package com.ezform.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezform.domain.EZ_visitVO;
import com.ezform.persistence.EZ_visitDAO;

@Service
public class EZ_visit_serviceImpl implements EZ_visit_service {

	@Inject
	private EZ_visitDAO visitDAO;

	@Override
	public void insertVisit(EZ_visitVO visitVO) {
		visitDAO.insertVisit(visitVO);
	}

	@Override
	public int getVisitCount(EZ_visitVO visitVO) {
		return visitDAO.getVisitCount(visitVO);
	}

	@Override
	public int getTodayVisitCount(EZ_visitVO visitVO) {
		// TODO Auto-generated method stub
		return visitDAO.getTodayVisitCount(visitVO);
	}
	
}
