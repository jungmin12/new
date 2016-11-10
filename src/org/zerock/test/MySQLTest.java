package org.zerock.test;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;
import org.zerock.dao.BoardDAOImpl;
import org.zerock.domain.BoardVO;

public class MySQLTest {

	BoardDAOImpl dao = new BoardDAOImpl();

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void testConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(
				"jdbc:mysql://192.168.0.19:3306/bit88?useSSL=false", "makemoney",
				"makemoney");

		System.out.println(con);
		con.close();
	}

	@Test
	public void testCreate() throws Exception {

		BoardVO vo = new BoardVO();
		vo.setTitle("testTitle");
		vo.setContent("testContent");
		vo.setWriter("ÀÌ¼ö¿¬");

		dao.create(vo);

	}

	@Test
	public void testSelect() throws Exception {

		System.out.println(dao.read(1));

	}

	@Test
	public void testUpdate() throws Exception {

		BoardVO vo = new BoardVO();
		vo.setContent("testUpdate333333");
		vo.setBno(13);

		dao.update(vo);

	}

	@Test
	public void testDelete() throws Exception {
		BoardVO vo = new BoardVO();
		vo.setBno(12);
		dao.delete(vo);

	}

}
