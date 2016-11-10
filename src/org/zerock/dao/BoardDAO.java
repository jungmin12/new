package org.zerock.dao;

import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardDAO extends GenericDAO<BoardVO, Integer> {

	public List<BoardVO> listPage(Integer page) throws Exception;

	public List<BoardVO> listSearch(Criteria cri) throws Exception;

	public int getCount(int page) throws Exception;

	public int getCountSearch(Criteria cri) throws Exception;
	
	
}
