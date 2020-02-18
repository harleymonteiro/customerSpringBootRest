package com.example.api.repository;

import com.example.api.domain.BaseEntity;
import com.example.api.repository.exception.DAOException;
import java.util.List;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import org.hibernate.HibernateException;


public interface BaseDao<T extends BaseEntity<I>, I extends Serializable> {

	void saveOrUpdate(T entity) throws DAOException, HibernateException;
	void remove(T entidade) throws DAOException, HibernateException;
	List<T> find(String sqlQuery, Map<String, Object> parametros) throws DAOException, HibernateException;	
	
}

