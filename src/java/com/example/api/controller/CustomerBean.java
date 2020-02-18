/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.api.controller;

import com.example.api.domain.Address;
import com.example.api.domain.Customer;
import com.example.api.repository.CustomerRepository;
import com.example.api.repository.exception.DAOException;
import com.example.api.service.CustomerService;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;
/**
 *
 * @author threadtec
 */



@ManagedBean
@SessionScoped
public class CustomerBean implements  Serializable{

    private static final long serialVersionUID = 1L; 
    private Customer customer;  
    private Address address;  
    
   
    
    @Autowired
    private CustomerService customerservice;
    
    @PostConstruct
    public void init(){
        this.customer = new Customer();      
        this.customer.setAddress(new ArrayList<Address>());
        
  }

  private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
    ois.defaultReadObject();
    init();
  }
    
    @PreDestroy
    public void fecha(){
       
    }
    

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public CustomerService getCustomerservice() {
        return customerservice;
    }

    public void setCustomerservice(CustomerService customerservice) {
        this.customerservice = customerservice;
    }
    
    public void selecionarAddress(ActionEvent event){
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            int objid = (Integer) event.getComponent().getAttributes().get("objid");
            
            //this.address = ;
            
            
        } catch (Exception ex) {
            Logger.getLogger(CustomerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Recebe as informacoes de login e senha da pag. e cria a sessao do usuario
    public void salvarColaborador(ActionEvent actionEvent){
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            this.customerservice.novoCustomer(customer);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "ATENÇÂO:", "O registro do novo colaborador já exite!"));
        } catch (DAOException ex) {
            Logger.getLogger(CustomerBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HibernateException ex) {
            Logger.getLogger(CustomerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void buscaColaborador(ActionEvent actionEvent){
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            this.customer = customerservice.buscaId(2);
             this.customer.setName("sdfhf");
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "ATENÇÂO:", "O registro do novo colaborador já exite!"));
        } catch (DAOException ex) {
            Logger.getLogger(CustomerBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HibernateException ex) {
//            Logger.getLogger(CustomerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
              
    
}
