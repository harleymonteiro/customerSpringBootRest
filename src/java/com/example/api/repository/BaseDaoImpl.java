package com.example.api.repository;


import com.example.api.domain.BaseEntity;
import com.example.api.repository.exception.DAOException;
import java.io.Serializable;
import java.util.List;

import java.util.Map;
import javax.inject.Inject;
import org.hibernate.Query;
import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.engine.spi.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;


@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T extends BaseEntity<I>, I extends Serializable>
		implements BaseDao<T, I> {
    
                /**
	 * Default class logger.
	 */
	



    private final ThreadLocal<Session> sessions;
        
    @Autowired
    private SessionFactory sessionFactory;

    public BaseDaoImpl() {
        sessions = new ThreadLocal<Session>();
    }
    
    public Session conexaoDB(){
        if (sessions.get() != null) {
                if(sessions.get().isOpen()){
                    return sessions.get();
                }else{
                    if(sessionFactory.getCurrentSession().isOpen())
                        sessions.set(sessionFactory.getCurrentSession());
                    else
                        sessions.set(sessionFactory.openSession());
                    
                     return sessions.get();
                }
        }else{
            if(sessionFactory.getCurrentSession().isOpen())
                sessions.set(sessionFactory.getCurrentSession());
            else
                sessions.set(sessionFactory.openSession());
             return sessions.get();
            
        }
        
        
    }
    
    public void fechaConexao() {
            sessions.get().close();
            sessions.set(null);
    }
    
    @Override
    public void saveOrUpdate(T entidade) throws DAOException, HibernateException{
        Transaction tx = conexaoDB().beginTransaction();
        entidade = (T) conexaoDB().merge(entidade);
    }
    
    @Override
    public void remove(T entidade) throws DAOException, HibernateException{
        Transaction tx = conexaoDB().beginTransaction();
        conexaoDB().delete( (T) entidade);        
    }
    
    @Override
    public List<T> find(String sqlQuery, Map<String, Object> parametros)  throws DAOException, HibernateException{
        List<T> resultado = null;
        Transaction tx = conexaoDB().beginTransaction();
        Query query = (Query) conexaoDB().getNamedQuery(sqlQuery);
        if(parametros!=null && !parametros.isEmpty()){
            for (Map.Entry<String, Object> entry : parametros.entrySet()) {
                    query.setParameter(entry.getKey(), entry.getValue());
            }
        }
        List<T> oClass =  query.list();
        
        if(oClass!=null && !oClass.isEmpty()){
            resultado = oClass;
        }else{
            resultado = null;
        }
//        conexaoDB().flush();
//        tx.commit();
        return resultado;

    }	

}
