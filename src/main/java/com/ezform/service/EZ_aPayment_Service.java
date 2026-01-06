package com.ezform.service;

import java.util.List;

import com.ezform.domain.EZ_aPaymentVO;

public interface EZ_aPayment_Service {

	public List<EZ_aPaymentVO> holidayList(EZ_aPaymentVO avo);

	public void insertIndividualHoliday(EZ_aPaymentVO avo);

	public EZ_aPaymentVO holidayDetail(int ap_id);

	public void updateStatus(EZ_aPaymentVO avo);

	public void deleteHoliday(EZ_aPaymentVO avo);

}
