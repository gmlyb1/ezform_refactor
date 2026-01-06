package com.ezform.persistence;

import java.util.List;

import com.ezform.domain.EZ_aPaymentVO;

public interface EZ_aPayment_DAO {

	public List<EZ_aPaymentVO> holidayList(EZ_aPaymentVO avo);

	public void insertIndividualHoliday(EZ_aPaymentVO avo);

	public EZ_aPaymentVO holidayDetail(int ap_id);

	public void updateStatus(EZ_aPaymentVO avo);

	public void deleteHoliday(EZ_aPaymentVO avo);

}
