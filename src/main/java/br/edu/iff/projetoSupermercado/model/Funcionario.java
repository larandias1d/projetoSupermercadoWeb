package br.edu.iff.projetoSupermercado.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;


@Entity
@JsonIgnoreProperties(value = "senha", allowGetters = false, allowSetters = true)

public class Funcionario extends Pessoa {
    
    
    @Column(nullable = false)
    @NotBlank(message = "Cargo obrigatório.")
    @Length(max = 50, message = "Cargo deve ter no máximo 50 caracteres.")
    private String cargo;
    
    @Column(nullable = false)
    private double salario;
    
    @Column(nullable = false)
    @NotBlank(message = "Senha obrigatória.")
    @Length(min = 8, max = 20, message = "Senha deve ter no mínimo 8 caracteres e no máximo 20.")
    private String senha;
    
    @JsonIgnore
    @OneToMany(mappedBy = "funcionario")
    private List<Venda> vendas = new ArrayList<>();

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }  

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }
}
