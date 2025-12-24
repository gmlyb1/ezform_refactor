package com.ezform.service;

import java.util.List;

import com.ezform.domain.EZ_empVO;
import com.ezform.domain.EZ_workVO;

public interface EZ_emp_Service {
	
	// 직원 계정 생성
	public void joinEmp(EZ_empVO vo);
	
	// 직원 정보 수정
	public int updateEmp(EZ_empVO uvo);
	
	// 직원 정보 삭제
	public void deleteEmp(EZ_empVO dvo);
	
	//기존근태 삭제(퇴사)
	public void deleteWork(Integer em_id);
	
	// 직원 1명조회
	public EZ_empVO getEmp(int em_id);
	
	// 직원 전체목록
	public List<EZ_empVO> getEmpList(int em_id);
	
	//로그인체크
	public EZ_empVO loginCheck(EZ_empVO vo);
	
	//근태체크
	public void wstatusEmp(EZ_workVO wvo);

	//근태List
	public List<EZ_empVO> wstatusListEmp();

	// 근태기록 List(본인꺼)
	public List<EZ_workVO> workHistoryList(EZ_workVO wvo);

	// 근태기록 List(관리자용)
	public List<EZ_workVO> UserWorkHistoryList(EZ_workVO wvo);
	
	// 근태기록카운트 List(관리자용)
	public List<EZ_workVO> UserWorkHistoryListCnt(EZ_workVO wvo);

	// 근태기록카운트 List(본인꺼)
	public List<EZ_workVO> workHistoryListCnt(EZ_workVO wvo);
	
	
	
	
	
	
	
	
}
