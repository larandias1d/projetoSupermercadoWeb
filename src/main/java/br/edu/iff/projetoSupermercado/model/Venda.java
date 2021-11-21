package br.edu.iff.projetoSupermercado.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Venda implements Serializable {
    private static final long serialVersionUID = 1L;
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false,updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "Data obrigatória.")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Calendar datahora;
    
    @Column(nullable = false)
    @NotNull(message = "Total é obrigatório.")
    @PositiveOrZero
    private double total;
    
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(nullable = false, name = "venda_id")
    @Size(min = 1, message = "Venda deve ter no mínimo 1 produto.")
    private List<Item_venda> item_vendas = new ArrayList<>();  
    
    
    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull(message = "Cliente obrigatório.")
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull(message = "Funcionário obrigatório.")
    private Funcionario funcionario;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getDatahora() {
        return datahora;
    }

    public void setDatahora(Calendar datahora) {
        this.datahora = datahora;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<Item_venda> getItem_vendas() {
        return item_vendas;
    }

    public void setItem_vendas(List<Item_venda> item_vendas) {
        this.item_vendas = item_vendas;
    }    

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
        
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.id);
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
        final Venda other = (Venda) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    }
