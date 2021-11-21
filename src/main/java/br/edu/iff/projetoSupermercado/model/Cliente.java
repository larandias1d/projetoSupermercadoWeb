package br.edu.iff.projetoSupermercado.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import org.hibernate.validator.constraints.Length;

@Entity
public class Cliente extends Pessoa {
    
    @Column(nullable = true, updatable = false)
    @Length(min = 5, max = 100, message = "Documentos deve ter no máximo 100 caracteres.")
    private String documentos;
    

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Venda> vendas = new ArrayList<>();

    public String getDocumentos() {
        return documentos;
    }

    public void setDocumentos(String documentos) {
        this.documentos = documentos;
    }  

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }  

  
}
