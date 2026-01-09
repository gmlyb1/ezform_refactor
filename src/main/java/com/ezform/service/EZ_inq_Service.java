package com.ezform.service;

import java.util.List;

import com.ezform.domain.EZ_empVO;
import com.ezform.domain.EZ_inqVO;

public interface EZ_inq_Service {

	public List<EZ_inqVO> selectInquiryList(EZ_inqVO inqVO);

	public void insertInquiry(EZ_inqVO inqVO);

	public EZ_inqVO read(int inq_no);

	public void remove(int inq_no);

	public void modify(EZ_inqVO vo);

	public List<EZ_inqVO> selectInquiryAdminList(EZ_inqVO inqVO);

	public void updateCheck(EZ_inqVO inqVO);

	public List<EZ_empVO> unCheckInquiryList(EZ_inqVO inqVO);



}
