package br.edu.iff.projetoSupermercado.model;

import java.io.Serializable; 
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;


@Embeddable
public class Endereco implements Serializable {    
    private static final long serialVersionUID = 1L;
    
    
    @Column(nullable = true)
    @Length(min = 10, max = 50, message = "Rua deve ter no máximo 50 caracteres.")
    private String rua;
    
    @Column(nullable = true)
    @Digits(integer = 4, fraction = 0, message = "Número deve ter inteiro e ter até 4 dígitos.")
    private int numero;
    
    @Column(nullable = true)
    @Length(min = 10, max = 50, message = "Bairro deve ter no máximo 50 caracteres.")
    private String bairro;
    
    @Column(nullable = true)
    @Length(min = 5, max = 50, message = "Cidade deve ter no máximo 50 caracteres." )
    private String cidade;
    
    @Column(nullable = true)
    @Length(min = 8, max = 9, message = "CEP deve ter no mínimo 8 caracteres e no máximo 9 ( Ex: 11111111 ou 11111-111")
    @NotBlank(message = "CEP obrigatório")
    @Valid
    private String cep;

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.rua);
        hash = 59 * hash + this.numero;
        hash = 59 * hash + Objects.hashCode(this.bairro);
        hash = 59 * hash + Objects.hashCode(this.cidade);
        hash = 59 * hash + Objects.hashCode(this.cep);
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
        final Endereco other = (Endereco) obj;
        if (this.numero != other.numero) {
            return false;
        }
        if (!Objects.equals(this.rua, other.rua)) {
            return false;
        }
        if (!Objects.equals(this.bairro, other.bairro)) {
            return false;
        }
        if (!Objects.equals(this.cidade, other.cidade)) {
            return false;
        }
        if (!Objects.equals(this.cep, other.cep)) {
            return false;
        }
        return true;
    }
    
    
    
}
