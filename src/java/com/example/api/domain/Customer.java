package com.example.api.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="customer")
@NamedQueries({ @NamedQuery(name="Customer.buscar_customer_id",       query="FROM Customer WHERE id = :_id"),
                @NamedQuery(name="Customer.buscar_customer_nome",     query="FROM Customer WHERE name = :_name"),
                @NamedQuery(name="Customer.buscar_customer_email",    query="FROM Customer WHERE nome = :_email"),
                @NamedQuery(name="Customer.buscar_todos",             query="FROM Customer")})
public class Customer extends BaseEntity<Integer> {

    
        public static final String BUSCAR_ID    = "Customer.buscar_customer_id";
        public static final String BUSCAR_NAME  = "Customer.buscar_customer_nome";
        public static final String BUSCAR_EMAIL = "Customer.buscar_customer_email";
        public static final String BUSCAR_TODOS = "Colaborador.buscar_todos";
        
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = false)
	@NotEmpty
        @Size(min=5, message="Campo nome deve ter mais de 5 letras")
	private String name;

	@Column(nullable = false)
	@NotEmpty
	@Email(message = "Campo e-mail inválido")
	private String email;
        @OneToMany(cascade=CascadeType.ALL, mappedBy="customer", targetEntity=Address.class)
        private List<Address> address = new ArrayList<Address>();

    public Customer() {
    }

        
        
    public Customer(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }
        
        

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }
        
        

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", name=" + name + ", email=" + email + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.email);
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
        final Customer other = (Customer) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }
         

}
