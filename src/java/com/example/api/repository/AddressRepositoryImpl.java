package com.example.api.repository;

import com.example.api.domain.Address;
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
public class AddressRepositoryImpl extends BaseDaoImpl<Address, Integer> implements AddressRepository {
    
    @Override
    public Address salvar(Address obj) throws DAOException, HibernateException{
        this.saveOrUpdate(obj);
        
        return obj;
    }

    @Override
    public Address alterar(Address obj) throws DAOException, HibernateException {
        this.saveOrUpdate(obj);
        
        return obj;
    }

    @Override
    public void apagar(Address obj) throws DAOException, HibernateException{
        try {
            this.remove(obj);
        } catch (DAOException ex) {
            Logger.getLogger(AddressRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Address buscarId(int id) throws DAOException, HibernateException{
        try {
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("_id", id);
            List<Address> lst = this.find(Address.BUSCAR_ID, parametros);
            if(lst !=null)
                if(lst.size()>0)
                    return lst.get(0);
        } catch (DAOException ex) {
            Logger.getLogger(AddressRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        return null;
    }

    @Override
    public List<Address> buscarCep(String cep) throws DAOException, HibernateException{
        try {
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("_cep", cep);
            List<Address> lst = this.find(Address.BUSCAR_CEP, parametros);
            if(lst !=null)
                    return lst;
        } catch (DAOException ex) {
            Logger.getLogger(AddressRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return null;
    }

    
    @Override
    public List<Address> buscarTodos() throws DAOException, HibernateException{
       
        List<Address> lst = this.find(Address.BUSCAR_TODOS, null);
        if(lst !=null )
                return lst;
        
        return null;
    }

    

}