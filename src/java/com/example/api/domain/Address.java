package com.example.api.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="address")
@NamedQueries({ @NamedQuery(name="Address.buscar_address_id",       query="FROM Address WHERE id = :_id"),
                @NamedQuery(name="Address.buscar_address_cep",     query="FROM Address WHERE cep = :_cep"),
                @NamedQuery(name="Address.buscar_todos",             query="FROM Address")})
public class Address extends BaseEntity<Integer> {

    
        public static final String BUSCAR_ID    = "Address.buscar_address_id";
        public static final String BUSCAR_CEP  = "Address.buscar_address_cep";
        public static final String BUSCAR_TODOS = "Colaborador.buscar_todos";
        
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;        
        @ManyToOne
        @JoinColumn(name="idcustomer")
        private Customer customer;
        @Column(nullable = false)
	@NotEmpty
        @Size(min=7, message="Campo cep inválido")
        private String cep;        
        @Column(nullable = true)
        private String logradouro;
        @Column(nullable = true)
        private String complemento;
        @Column(nullable = true)
        private String bairro;
        @Column(nullable = true)
        private String localidade;
        @Column(nullable = true)
        private String uf;
        @Column(nullable = true)
        private String ibge;

    public Address() {
    }


    public Address(Integer id, Customer customer, String cep, String logradouro, String complemento, String bairro, String localidade, String uf, String ibge) {
        this.id = id;
        this.customer = customer;
        this.cep = cep;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
        this.ibge = ibge;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getIbge() {
        return ibge;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    @Override
    public String toString() {
        return "Address{" + "id=" + id + ", customer=" + customer + ", cep=" + cep + ", logradouro=" + logradouro + ", complemento=" + complemento + ", bairro=" + bairro + ", localidade=" + localidade + ", uf=" + uf + ", ibge=" + ibge + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.customer);
        hash = 23 * hash + Objects.hashCode(this.cep);
        hash = 23 * hash + Objects.hashCode(this.logradouro);
        hash = 23 * hash + Objects.hashCode(this.complemento);
        hash = 23 * hash + Objects.hashCode(this.bairro);
        hash = 23 * hash + Objects.hashCode(this.localidade);
        hash = 23 * hash + Objects.hashCode(this.uf);
        hash = 23 * hash + Objects.hashCode(this.ibge);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Address other = (Address) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.customer, other.customer)) {
            return false;
        }
        if (!Objects.equals(this.cep, other.cep)) {
            return false;
        }
        if (!Objects.equals(this.logradouro, other.logradouro)) {
            return false;
        }
        if (!Objects.equals(this.complemento, other.complemento)) {
            return false;
        }
        if (!Objects.equals(this.bairro, other.bairro)) {
            return false;
        }
        if (!Objects.equals(this.localidade, other.localidade)) {
            return false;
        }
        if (!Objects.equals(this.uf, other.uf)) {
            return false;
        }
        if (!Objects.equals(this.ibge, other.ibge)) {
            return false;
        }
        return true;
    }

        
        
   
         

}
