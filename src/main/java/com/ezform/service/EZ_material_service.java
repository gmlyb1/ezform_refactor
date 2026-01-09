package com.ezform.service;

import java.util.List;

import com.ezform.domain.EZ_materialVO;

public interface EZ_material_service {

	public List<EZ_materialVO> selectBoardList(EZ_materialVO materialVO);

	public void insertBoard(EZ_materialVO materialVO);

	public EZ_materialVO read(int ma_seq);

	public void modify(EZ_materialVO materialVO);

	public void delete(EZ_materialVO materialVO);

}
