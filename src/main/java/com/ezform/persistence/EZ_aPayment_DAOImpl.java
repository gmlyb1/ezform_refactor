package com.ezform.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ezform.domain.EZ_aPaymentVO;
import com.ezform.test.testController;

@Repository
public class EZ_aPayment_DAOImpl implements EZ_aPayment_DAO{

	@Inject
	private static final Logger logger = LoggerFactory.getLogger(testController.class);

	@Inject
	private SqlSession sqlSession;

	private static String namespace = "com.ezform.mapper.aPayment_Mapper";

	@Override
	public List<EZ_aPaymentVO> holidayList(EZ_aPaymentVO avo) {
		return sqlSession.selectList(namespace+ ".holidayList", avo);
	}

	@Override
	public void insertIndividualHoliday(EZ_aPaymentVO avo) {
		sqlSession.insert(namespace+ ".insertIndividualHoliday", avo);
	}

	@Override
	public EZ_aPaymentVO holidayDetail(int ap_id) {
		return sqlSession.selectOne(namespace+ ".holidayDetail", ap_id);
	}

	@Override
	public void updateStatus(EZ_aPaymentVO avo) {
		sqlSession.update(namespace+ ".updateStatus", avo);
	}

	@Override
	public void deleteHoliday(EZ_aPaymentVO avo) {
		sqlSession.delete(namespace+ ".deleteHoliday", avo);
	}
}
