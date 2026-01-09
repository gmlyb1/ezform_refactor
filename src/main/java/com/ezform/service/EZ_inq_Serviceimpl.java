package com.ezform.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ezform.controller.EZ_inq_Controller;
import com.ezform.domain.EZ_empVO;
import com.ezform.domain.EZ_inqVO;
import com.ezform.persistence.EZ_inqDAO;


@Service
public class EZ_inq_Serviceimpl implements EZ_inq_Service {

	@Inject
	private EZ_inqDAO inqDAO;

	private static final Logger logger = LoggerFactory.getLogger(EZ_inq_Controller.class);

	@Override
	public List<EZ_inqVO> selectInquiryList(EZ_inqVO inqVO) {
		return inqDAO.selectInquiryList(inqVO);
	}

	@Override
	public void insertInquiry(EZ_inqVO inqVO) {
		inqDAO.insertInquiry(inqVO);
	}

	@Override
	public EZ_inqVO read(int inq_no) {
		return inqDAO.read(inq_no);
	}

	@Override
	public void remove(int inq_no) {
		inqDAO.remove(inq_no);
	}

	@Override
	public void modify(EZ_inqVO vo) {
		inqDAO.modify(vo);
	}

	@Override
	public List<EZ_inqVO> selectInquiryAdminList(EZ_inqVO inqVO) {
		return inqDAO.selectInquiryAdminList(inqVO);
	}

	@Override
	public void updateCheck(EZ_inqVO inqVO) {
		inqDAO.updateCheck(inqVO);
	}

	@Override
	public List<EZ_empVO> unCheckInquiryList(EZ_inqVO inqVO) {
		return inqDAO.unCheckInquiryList(inqVO);
	}

}
