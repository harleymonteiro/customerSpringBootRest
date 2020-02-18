package com.example.api.repository;
import java.util.List;


import com.example.api.domain.Address;
import com.example.api.repository.exception.DAOException;
import java.io.Serializable;
import org.hibernate.HibernateException;


public interface AddressRepository extends BaseDao<Address,Integer>{

    public Address salvar(Address obj) throws DAOException, HibernateException;

    public Address alterar(Address obj) throws DAOException, HibernateException;

    public void apagar(Address obj) throws DAOException, HibernateException;

    public Address buscarId(int id) throws DAOException, HibernateException;
    
    public List<Address> buscarCep(String  cep) throws DAOException, HibernateException;

    public List<Address> buscarTodos() throws DAOException, HibernateException;
   
}
