package org.zerock.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public class BoardDAOImpl extends AbstractDAO<BoardVO, Integer> {

	@Override
	public void create(BoardVO vo) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public BoardVO read(Integer bno) throws Exception {
		BoardVO result = new BoardVO();
		try (SqlSession session = MyBatisFactory.INSTANCE.factory.openSession();) {
			result = session.selectOne("org.zerock.dao.BoardMapper.read", bno);
		}
		return result;
	}

	@Override
	public void update(BoardVO vo) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer bno) throws Exception {
		try (SqlSession session = MyBatisFactory.INSTANCE.factory.openSession();) {
			 session.selectOne("org.zerock.dao.BoardMapper.delete", bno);
		}

	}

	@Override
	public List<BoardVO> listPage(Integer page) throws Exception {
		List<BoardVO> list = null;
		try (SqlSession session = MyBatisFactory.INSTANCE.factory.openSession();) {
			list = session.selectList("org.zerock.dao.BoardMapper.list", (page - 1) * 10);
		}
		return list;
	}

	@Override
	public int getCount(int page) throws Exception {
		int total = 0;
		try (SqlSession session = MyBatisFactory.INSTANCE.factory.openSession();) {
			total = session.selectOne("org.zerock.dao.BoardMapper.getCount");
		}
		return total;
	}

	@Override
	public List<BoardVO> listSearch(Criteria cri) throws Exception {
		List<BoardVO> list = null;
		try (SqlSession session = MyBatisFactory.INSTANCE.factory.openSession();) {
			list = session.selectList("org.zerock.dao.BoardMapper.listSearch", cri);
		}
		return list;

	}

	@Override
	public int getCountSearch(Criteria cri) throws Exception {
		int total = 0;
		try (SqlSession session = MyBatisFactory.INSTANCE.factory.openSession();) {
			total = session.selectOne("org.zerock.dao.BoardMapper.getCountSearch", cri);
		}
		return total;
	}
}
