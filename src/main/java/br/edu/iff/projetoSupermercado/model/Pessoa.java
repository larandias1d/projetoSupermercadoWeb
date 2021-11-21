package br.edu.iff.projetoSupermercado.model;

import br.edu.iff.projetoSupermercado.annotation.EmailValidation;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.persistence.Embedded;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.Valid;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id;
    
    @Column(nullable = false)
    @NotBlank(message = "Nome obrigatório.")
    @Length(min = 4, max = 100, message = "Nome deve ter no máximo 100 caracteres.")
    private String nome;
    
    @Column(nullable = true, length = 100)
    @EmailValidation(message = "Email inválido.")
    private String email;
    
    @Column(nullable = false,length = 14, unique = true, updatable = false)
    @NotBlank(message = "CPF obrigatório.")
    @CPF(message = "CPF inválido.")
    private String cpf;
    
    
    @Embedded
    @Valid
    private Endereco endereco;
    
    @Embedded
    @Valid
    private Telefone telefone;

    public Pessoa() {
    }
    
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    
    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getCpf(){
        return cpf;
    }
    public void setCpf(String cpf){
        this.cpf = cpf;
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
        
    @Override
    public int hashCode() {
        int hash = 5;
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
        final Pessoa other = (Pessoa) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
