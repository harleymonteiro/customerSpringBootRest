package com.example.api.repository;
import java.util.List;


import com.example.api.domain.Customer;
import com.example.api.repository.exception.DAOException;
import java.io.Serializable;
import org.hibernate.HibernateException;


public interface CustomerRepository extends BaseDao<Customer,Integer>{

    public Customer salvar(Customer obj) throws DAOException, HibernateException;

    public Customer alterar(Customer obj) throws DAOException, HibernateException;

    public void apagar(Customer obj) throws DAOException, HibernateException;

    public Customer buscarId(int id) throws DAOException, HibernateException;

    public List<Customer> buscarNome(String  nome) throws DAOException, HibernateException;

    public List<Customer> buscarEmail(String  email) throws DAOException, HibernateException;

    public List<Customer> buscarTodos() throws DAOException, HibernateException;
   
}
