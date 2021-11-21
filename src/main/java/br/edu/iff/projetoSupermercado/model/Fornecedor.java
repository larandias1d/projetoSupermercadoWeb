package br.edu.iff.projetoSupermercado.model;

import br.edu.iff.projetoSupermercado.annotation.EmailValidation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;


@Entity
public class Fornecedor implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id;  
    
    
    @Column(nullable = false, unique = true)
    @NotBlank(message = "Razão Social obrigatório.")
    @Length(min = 4, max = 100, message = "Nome deve ter no máximo 100 caracteres.")
    private String razao_social;
    
    
    @Column(nullable = false, length = 18, unique = true, updatable = false)
    @NotBlank(message = "CNPJ obrigatório.")
    @CNPJ(message = "CNPJ inválido")
    private String cnpj;
   
    
    @Column(nullable = true, length = 100)
    @EmailValidation(message = "Email inválido.")
    private String email;
    
    
    @Embedded
    @Valid
    private Endereco endereco;
    
    
    @Embedded
    @Valid
    private Telefone telefone;
   
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(nullable = false, name = "fornecedor_id")
    @Size(min = 1, message = "Fornecedor deve ter no mínimo 1 produto.")
    @Valid
    private List<Produto> produtos = new ArrayList<>();
       

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    
     public String getRazao_social() {
        return razao_social;
    }

    public void setRazao_social(String razao_social) {
        this.razao_social = razao_social;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
   
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }        

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Fornecedor other = (Fornecedor) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
