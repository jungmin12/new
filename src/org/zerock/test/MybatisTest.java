package org.zerock.test;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.zerock.dao.MyBatisFactory;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

public class MybatisTest {

	private SqlSessionFactory factory;

	@Before
	public void setUp() throws Exception {
		System.out.println("----Setup-----");
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		factory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	@Test
	public void test() {
		System.out.println(factory);
	}

	@Test
	public void testGetTime() throws Exception {
		SqlSession session = factory.openSession();
		try {
			String now = session.selectOne("org.zerock.dao.TimeMapper.getTime");

			System.out.println(now);
		} finally {
			session.close();
		}
	}

	@Test
	public void testList() {
		SqlSession session = factory.openSession();
		try {
			List<BoardVO> now = session.selectList("org.zerock.dao.BoardMapper.list", 1);
			System.out.println(now);
			System.out.println("aaa");
		} finally {
			session.close();
		}
	}

	@Test
	public void testList1() {
		BoardVO vo = new BoardVO();
		try (SqlSession session = factory.openSession();) {
			vo = session.selectOne("org.zerock.dao.BoardMapper.view", 300);
			System.out.println(vo);
		}

	}

	@Test
	public void testSearch() {
		Criteria cri = new Criteria(1, "t", "1000");

		SqlSession session = factory.openSession();
		try {
			List<BoardVO> now = session.selectList("org.zerock.dao.BoardMapper.listSearch", cri);
			System.out.println(now);

		} finally {
			session.close();
		}
	}

	@Test
	public void testDelete() {
		
		try (SqlSession session = factory.openSession();) {
		 session.selectOne("org.zerock.dao.BoardMapper.delete", 12);
		}

	}
	@Test
	public void testCreate() {
		BoardVO vo = new BoardVO();
		vo.setTitle("dskdfioe");
		vo.setContent("contentttttt");		
		vo.setWriter("writerrrrrrr");
		
		System.out.println(vo);
		
		try (SqlSession session = factory.openSession();) {
			session.selectOne("org.zerock.dao.BoardMapper.create", vo);
		}
		
	}
	
	@Test
	public void testRCreate() {
		ReplyVO vo = new ReplyVO();
		vo.setBno(1376220);
		vo.setReply("야호");	
		vo.setReplyer("다은");
		
		System.out.println(vo);
		
		try (SqlSession session = factory.openSession();) {
			session.selectOne("org.zerock.dao.ReplyMapper.create", vo);
			
		session.commit();
		
		}
		                                                                                                          
		
	}
	@Test
	public void testRCreate2() {
		ReplyVO vo = new ReplyVO();
		vo.setBno(1376220);
		vo.setReply("야호");	
		vo.setReplyer("다은");
		
//		dao.create(vo);
		
	}

}
