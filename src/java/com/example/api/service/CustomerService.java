package com.example.api.service;

import com.example.api.domain.Customer;
import com.example.api.repository.exception.DAOException;
import com.example.api.repository.exception.ServiceException;
import java.util.List;
import java.util.Optional;
import org.hibernate.HibernateException;

public interface CustomerService {
    
    public Customer novoCustomer(Customer customer) throws DAOException, HibernateException;
    public Customer editaCustomer(Customer customer) throws DAOException, HibernateException;
    public void apagaCustomer(Integer Id) throws DAOException, HibernateException;
    public void apagaCustomer(Customer customer) throws DAOException, HibernateException;
    public Customer buscaId(Integer Id) throws DAOException, HibernateException;
    public List<Customer> buscaNome(String nome) throws DAOException, HibernateException;
    public List<Customer> buscaEmail(String email) throws DAOException, HibernateException;
    public List<Customer> buscaTodos() throws DAOException, HibernateException;
       
}
