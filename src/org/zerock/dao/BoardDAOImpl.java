package org.zerock.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public class BoardDAOImpl extends GenericDAOImpl<BoardVO, Integer> implements BoardDAO {

	//이것마저하기 싫으면 어노테이션...
	public BoardDAOImpl(){
		super("BoardMapper");
	}
	
	public List<BoardVO> listPage(Integer page) throws Exception {
		List<BoardVO> list = null;
		try (SqlSession session = MyBatisFactory.INSTANCE.factory.openSession();) {
			list = session.selectList("org.zerock.dao.BoardMapper.list", (page - 1) * 10);
		}
		return list;
	}

	public int getCount(int page) throws Exception {
		int total = 0;
		try (SqlSession session = MyBatisFactory.INSTANCE.factory.openSession();) {
			total = session.selectOne("org.zerock.dao.BoardMapper.getCount");
		}
		return total;
	}

	public List<BoardVO> listSearch(Criteria cri) throws Exception {
		List<BoardVO> list = null;
		try (SqlSession session = MyBatisFactory.INSTANCE.factory.openSession();) {
			list = session.selectList("org.zerock.dao.BoardMapper.listSearch", cri);
		}
		return list;

	}

	
	public int getCountSearch(Criteria cri) throws Exception {
		int total = 0;
		try (SqlSession session = MyBatisFactory.INSTANCE.factory.openSession();) {
			total = session.selectOne("org.zerock.dao.BoardMapper.getCountSearch", cri);
		}
		return total;
	}
}
