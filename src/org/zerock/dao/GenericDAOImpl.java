package org.zerock.dao;

import org.apache.ibatis.session.SqlSession;

public class GenericDAOImpl<E,K> implements GenericDAO<E, K> {

	private String mapperName;
	public GenericDAOImpl(String name){
		this.mapperName = name;
	}
	
	@Override
	public void create(E vo) throws Exception {
		try (SqlSession session = MyBatisFactory.INSTANCE.factory.openSession();) {
			session.selectOne("org.zerock.dao."+mapperName+".create", vo);
		}
	}

	@Override
	public E read(K key) throws Exception {
		E result = null;
		try (SqlSession session = MyBatisFactory.INSTANCE.factory.openSession();) {
			result = session.selectOne("org.zerock.dao."+mapperName+".read", key);
		}
		return result;
	}

	@Override
	public void update(E vo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(K key) throws Exception {
		try (SqlSession session = MyBatisFactory.INSTANCE.factory.openSession();) {
			session.selectOne("org.zerock.dao."+mapperName+".delete", key);
		}
		
	}

	
	
	
}
