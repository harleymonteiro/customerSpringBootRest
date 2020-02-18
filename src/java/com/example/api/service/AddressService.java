package com.example.api.service;

import com.example.api.domain.Address;
import com.example.api.repository.exception.DAOException;
import com.example.api.repository.exception.ServiceException;
import java.util.List;
import java.util.Optional;
import org.hibernate.HibernateException;

public interface AddressService {
    
    public Address novoAddress(Address address) throws DAOException, HibernateException;
    public Address editaAddress(Address address) throws DAOException, HibernateException;
    public void apagaAddress(Integer Id) throws DAOException, HibernateException;
    public void apagaAddress(Address address) throws DAOException, HibernateException;
    public Address buscaId(Integer Id) throws DAOException, HibernateException;
    public List<Address> buscaCep(String cep) throws DAOException, HibernateException;
    public List<Address> buscaTodos() throws DAOException, HibernateException;
       
}
