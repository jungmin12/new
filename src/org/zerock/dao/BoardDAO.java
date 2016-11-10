package org.zerock.dao;

import java.util.List;

import org.zerock.domain.Criteria;

public interface BoardDAO<BoardVO, Integer> {

	public void create(BoardVO vo) throws Exception;

	public BoardVO read(Integer bno) throws Exception;

	public void update(BoardVO vo) throws Exception;

	public void delete(BoardVO vo) throws Exception;

	public List<BoardVO> listPage(Integer page) throws Exception;

	public List<BoardVO> listSearch(Criteria cri) throws Exception;

	public int getCount(int page) throws Exception;

	public int getCountSearch(Criteria cri) throws Exception;
	
	
}
