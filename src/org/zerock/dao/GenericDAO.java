package org.zerock.dao;

public interface GenericDAO<E, K> {
//Element, KeyType
//어노테이션에 비해 인터페이스는 파라미터,리턴타입 제약 있음.->그래서 제네릭 사용
	
	public void create(E vo)throws Exception;
	
	public E read(K key)throws Exception;
	
	public void update(E vo)throws Exception;
	
	public void delete(K key)throws Exception;
	
}
