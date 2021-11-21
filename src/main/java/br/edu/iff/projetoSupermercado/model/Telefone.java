package br.edu.iff.projetoSupermercado.model;

import java.io.Serializable;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.hibernate.validator.constraints.Length;

@Embeddable
public class Telefone implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Column(length = 14, nullable = true)
    @Length(min=13, max = 14, message = "Telefone deve ter 13 ou 14 caracteres (Ex: (22)1111-1111 ou (11)11111-1111")
    private String tel;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.tel);
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
        final Telefone other = (Telefone) obj;
        if (!Objects.equals(this.tel, other.tel)) {
            return false;
        }
        return true;
    }  
    
}
