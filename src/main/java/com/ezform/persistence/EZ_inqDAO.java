package com.ezform.persistence;

import java.util.List;

import com.ezform.domain.EZ_inqVO;

public interface EZ_inqDAO {

	public List<EZ_inqVO> selectInquiryList(EZ_inqVO inqVO);

	public void insertInquiry(EZ_inqVO inqVO);

	public EZ_inqVO read(int inq_no);

	public void remove(int inq_no);

	public void modify(EZ_inqVO vo);

	public List<EZ_inqVO> selectInquiryAdminList(EZ_inqVO inqVO);


}
