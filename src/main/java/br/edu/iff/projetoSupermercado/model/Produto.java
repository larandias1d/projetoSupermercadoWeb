package br.edu.iff.projetoSupermercado.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Length;


@Entity
public class Produto implements Serializable{
    private static final long serialVersionUID = 1L;
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    @Length(min = 5, max = 100, message = "Produto deve ter no máximo 100 caracteres.")
    @NotBlank(message = "Descrição obrigatória.")
    private String descricao;
    
    @Column(nullable = false)
    @PositiveOrZero
    private double valor;
    
    @Column(nullable = true)
    @PositiveOrZero
    private int qtde_estoque;
    
    @OneToMany(mappedBy = "produto")
    @JsonIgnore    
    private List<Item_venda> item_vendas = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQtde_estoque() {
        return qtde_estoque;
    }

    public void setQtde_estoque(int qtde_estoque) {
        this.qtde_estoque = qtde_estoque;
    }

    public List<Item_venda> getItem_vendas() {
        return item_vendas;
    }

    public void setItem_vendas(List<Item_venda> item_vendas) {
        this.item_vendas = item_vendas;
    }    

    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final Produto other = (Produto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}
