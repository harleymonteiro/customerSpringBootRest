package com.example.api.restcontroller;


import com.example.api.domain.Customer;
import com.example.api.objct.EnderecoTO;
import com.example.api.repository.exception.DAOException;
import com.example.api.restcontroller.exception.CustomerNotFoundException;
import com.example.api.service.CustomerService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author threadtec
 */



@RestController
@RequestMapping(value="/customer", headers="Accept=*/*",  produces="application/json")
public class CustomerRestController {
    
   @Autowired
   private CustomerService customerService;
   
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Customer>  newCustomer(@Valid @RequestBody Customer customer) {        
       try {
           Customer obj = customerService.novoCustomer(customer);           
           return  new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
       } catch (DAOException ex) {
           Logger.getLogger(CustomerRestController.class.getName()).log(Level.SEVERE, null, ex);
           return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
       } catch (HibernateException ex) {
           Logger.getLogger(CustomerRestController.class.getName()).log(Level.SEVERE, null, ex);
           return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
       }
    }
    
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Customer>  update(@Valid @RequestBody Customer customer, @PathVariable int id) {        
       try {
           Customer obj = customerService.buscaId(id);    
           
           if(obj==null){
               return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
           }
           
           customer.setId(id);
           customerService.editaCustomer(customer);
           
           return  new ResponseEntity<Customer>(customer, HttpStatus.OK);
       } catch (DAOException ex) {
           Logger.getLogger(CustomerRestController.class.getName()).log(Level.SEVERE, null, ex);
           return new ResponseEntity<Customer>(HttpStatus.NOT_MODIFIED);
       } catch (HibernateException ex) {
           Logger.getLogger(CustomerRestController.class.getName()).log(Level.SEVERE, null, ex);
           return new ResponseEntity<Customer>(HttpStatus.NOT_MODIFIED);
       }
       
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Customer>  delete(@PathVariable int id) {        
       try {
           Customer obj = customerService.buscaId(id);  
           if(obj==null){
               return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
           }           
           customerService.apagaCustomer(obj);
           
           return  new ResponseEntity<Customer>(HttpStatus.OK);
       } catch (DAOException ex) {
           Logger.getLogger(CustomerRestController.class.getName()).log(Level.SEVERE, null, ex);
           return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
       } catch (HibernateException ex) {
           Logger.getLogger(CustomerRestController.class.getName()).log(Level.SEVERE, null, ex);
           return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
       }
       
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Customer> findAll() {
       try {
           return customerService.buscaTodos();
       } catch (DAOException ex) {
           Logger.getLogger(CustomerRestController.class.getName()).log(Level.SEVERE, null, ex);
           return null;
       } catch (HibernateException ex) {
           Logger.getLogger(CustomerRestController.class.getName()).log(Level.SEVERE, null, ex);
           return null;
       }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers="Accept=*/*",  produces="application/json")
    public Customer findById(@PathVariable int id) {
       try {
           Customer customer = customerService.buscaId(id);
           Optional<Customer> opcustomer = Optional.of(customer);        
           
           return opcustomer
                   .orElseThrow(() -> new CustomerNotFoundException("id-" + id));
       } catch (DAOException ex) {
           Logger.getLogger(CustomerRestController.class.getName()).log(Level.SEVERE, null, ex);
           throw new CustomerNotFoundException("id-" + id);
       } catch (HibernateException ex) {
           Logger.getLogger(CustomerRestController.class.getName()).log(Level.SEVERE, null, ex);
           throw new CustomerNotFoundException("id-" + id);
       }
    }
    
    @RequestMapping(value = "/getCep/{cep}", method = RequestMethod.GET)
    public ResponseEntity<EnderecoTO> doObterCep(@PathVariable String cep) {
        //
        /**
         * Criando e instanciando um objeto do tipo RestTemplate, este objeto possui métodos que
         * irá permitir a comunicação com o webservice
         */
        RestTemplate restTemplate = new RestTemplate();


        /**
         * Criação de uma String com nome de uri, irá armazenar a uri (endereço) a ser consumido, observe que o cep é passado como parametro e o retorno é json.
         */
        String uri = "http://viacep.com.br/ws/{cep}/json/";
        /**
         * É possível passar mais de um parametro, entretanto só iremos utilizar o cep
         * observe que foi utilizado um Map para permiter obter o valor pela chave
         */
        Map<String, String> params = new HashMap<String, String>();
        params.put("cep", cep);
        /**
         * restTemplate.getForObject(uri, CepTO.class, params);
         * 1 (URI) - Endereço do webservice que será consumido
         * 2 (EnderecoTO.class) - Classe que representa os dados do endereço e que será mapeada no retorno da requisição com base no Json.
         * 3 (Params) - Parametros que serão utilizados na requisição, os mesmos serão includos na uri. Exemplo: {cep} será substituido pelo cep informado  
         * 
         * Após a requisição ser concluida, o retorno será armazenado no enderecoTO, com todos os dados já mapeados.
         */
        EnderecoTO enderecoTO = restTemplate.getForObject(uri, EnderecoTO.class, params);
        /**
         * ResponseEntity permite retornar para tela os dados encontratos, o primeiro parametro recebe os dados, o segundo o status do response. 
         */
        return new ResponseEntity<EnderecoTO>(enderecoTO, HttpStatus.OK);
  }

    
}
