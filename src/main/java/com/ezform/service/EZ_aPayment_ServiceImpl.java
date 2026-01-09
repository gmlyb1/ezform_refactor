package com.ezform.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ezform.domain.EZ_aPaymentVO;
import com.ezform.domain.EZ_empVO;
import com.ezform.persistence.EZ_aPayment_DAO;
import com.ezform.test.testController;

@Service
public class EZ_aPayment_ServiceImpl implements EZ_aPayment_Service {

	private static final Logger logger = 
			LoggerFactory.getLogger(testController.class);
	
	@Inject
	private EZ_aPayment_DAO paydao;

	@Override
	public List<EZ_aPaymentVO> holidayList(EZ_aPaymentVO avo) {
		return paydao.holidayList(avo);
	}

	@Override
	public void insertIndividualHoliday(EZ_aPaymentVO avo) {
		paydao.insertIndividualHoliday(avo);
	}

	@Override
	public EZ_aPaymentVO holidayDetail(int ap_id) {
		return paydao.holidayDetail(ap_id);
	}

	@Override
	public void updateStatus(EZ_aPaymentVO avo) {
		paydao.updateStatus(avo);
	}

	@Override
	public void deleteHoliday(EZ_aPaymentVO avo) {
		paydao.deleteHoliday(avo);
	}

	@Override
	public List<EZ_empVO> unApaymentList(EZ_aPaymentVO avo) {
		return paydao.unApaymentList(avo);
	}
}
