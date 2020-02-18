package com.example.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.domain.Customer;
import com.example.api.repository.CustomerRepository;
import com.example.api.repository.exception.DAOException;
import com.example.api.repository.exception.ServiceException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    @Transactional
    public Customer novoCustomer(Customer customer) throws DAOException, HibernateException {
       return customerRepository.salvar(customer);
    }

    @Override
    @Transactional
    public Customer editaCustomer(Customer customer) throws DAOException, HibernateException {
        return customerRepository.alterar(customer);
    }

    @Override
    @Transactional
    public void apagaCustomer(Integer Id) throws DAOException, HibernateException {
        Customer customer = new Customer();
        customer.setId(Id);
        customerRepository.apagar(customer);
    }
    
    @Transactional
    public void apagaCustomer(Customer customer) throws DAOException, HibernateException{
        customerRepository.apagar(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public Customer buscaId(Integer Id) throws DAOException, HibernateException {
        return  customerRepository.buscarId(Id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> buscaNome(String nome) throws DAOException, HibernateException {
        return customerRepository.buscarNome(nome);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> buscaEmail(String email) throws DAOException, HibernateException {
        return customerRepository.buscarEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> buscaTodos() throws DAOException, HibernateException {
        return customerRepository.buscarTodos();
    }

    

}