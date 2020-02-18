package com.example.api.repository;

import com.example.api.domain.Customer;
import com.example.api.repository.exception.DAOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository
@Transactional 
@SuppressWarnings("unchecked")
public class CustomerRepositoryImpl extends BaseDaoImpl<Customer, Integer> implements CustomerRepository {
    
    @Override
    public Customer salvar(Customer obj) throws DAOException, HibernateException{
        this.saveOrUpdate(obj);
        
        return obj;
    }

    @Override
    public Customer alterar(Customer obj) throws DAOException, HibernateException {
        this.saveOrUpdate(obj);
        
        return obj;
    }

    @Override
    public void apagar(Customer obj) throws DAOException, HibernateException{
        try {
            this.remove(obj);
        } catch (DAOException ex) {
            Logger.getLogger(CustomerRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Customer buscarId(int id) throws DAOException, HibernateException{
        try {
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("_id", id);
            List<Customer> lst = this.find(Customer.BUSCAR_ID, parametros);
            if(lst !=null)
                if(lst.size()>0)
                    return lst.get(0);
        } catch (DAOException ex) {
            Logger.getLogger(CustomerRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        return null;
    }

    @Override
    public List<Customer> buscarNome(String nome) throws DAOException, HibernateException{
        try {
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("_name", nome);
            List<Customer> lst = this.find(Customer.BUSCAR_NAME, parametros);
            if(lst !=null)
                    return lst;
        } catch (DAOException ex) {
            Logger.getLogger(CustomerRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return null;
    }

    @Override
    public List<Customer> buscarEmail(String email) throws DAOException, HibernateException{
        try {
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("_email", email);
            List<Customer> lst = this.find(Customer.BUSCAR_EMAIL, parametros);
            if(lst !=null)
                    return lst;
        } catch (DAOException ex) {
            Logger.getLogger(CustomerRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return null;
    }

    @Override
    public List<Customer> buscarTodos() throws DAOException, HibernateException{
       
        List<Customer> lst = this.find(Customer.BUSCAR_TODOS, null);
        if(lst !=null )
                return lst;
        
        return null;
    }

    

}