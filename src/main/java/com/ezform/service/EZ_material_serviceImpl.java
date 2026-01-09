package com.ezform.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ezform.domain.EZ_materialVO;
import com.ezform.persistence.EZ_material_DAO;
import com.ezform.test.testController;

@Service
public class EZ_material_serviceImpl implements EZ_material_service {

private static final Logger logger = LoggerFactory.getLogger(testController.class);
	
	@Inject
	private EZ_material_DAO mdao;

	@Override
	public List<EZ_materialVO> selectBoardList(EZ_materialVO materialVO) {
		return mdao.selectBoardList(materialVO);
	}

	@Override
	public void insertBoard(EZ_materialVO materialVO) {
		mdao.insertBoard(materialVO);
	}

	@Override
	public EZ_materialVO read(int ma_seq) {
		return mdao.read(ma_seq);
	}

	@Override
	public void modify(EZ_materialVO materialVO) {
		mdao.modify(materialVO);
	}

	@Override
	public void delete(EZ_materialVO materialVO) {
		mdao.delete(materialVO);
	}
}
