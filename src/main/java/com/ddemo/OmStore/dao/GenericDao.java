package com.ddemo.OmStore.dao;

import java.util.List;

public interface GenericDao<T> {

	void create(T t) throws Exception;

	void edit(T t) throws Exception;

	void remove(T t) throws Exception;

	T find(Object object) throws Exception;

	List<T> findAllById(String tableName, int id) throws Exception;

	List<T> findall() throws Exception;

	List<T> findEntities(int maxResult, int firstResult) throws Exception;

	
	
}
