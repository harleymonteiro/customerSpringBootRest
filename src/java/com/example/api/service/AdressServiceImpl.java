package com.example.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.domain.Address;
import com.example.api.repository.AddressRepository;
import com.example.api.repository.exception.DAOException;
import com.example.api.repository.exception.ServiceException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;


    @Override
    @Transactional
    public Address novoAddress(Address address) throws DAOException, HibernateException {
       return addressRepository.salvar(address);
    }

    @Override
    @Transactional
    public Address editaAddress(Address address) throws DAOException, HibernateException {
        return addressRepository.alterar(address);
    }

    @Override
    @Transactional
    public void apagaAddress(Integer Id) throws DAOException, HibernateException {
        Address address = new Address();
        address.setId(Id);
        addressRepository.apagar(address);
    }
    
    @Transactional
    public void apagaAddress(Address address) throws DAOException, HibernateException{
        addressRepository.apagar(address);
    }

    @Override
    @Transactional(readOnly = true)
    public Address buscaId(Integer Id) throws DAOException, HibernateException {
        return  addressRepository.buscarId(Id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Address> buscaCep(String cep) throws DAOException, HibernateException {
        return addressRepository.buscarCep(cep);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Address> buscaTodos() throws DAOException, HibernateException {
        return addressRepository.buscarTodos();
    }

    

}