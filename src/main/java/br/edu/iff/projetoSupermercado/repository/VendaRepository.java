package br.edu.iff.projetoSupermercado.repository;

import br.edu.iff.projetoSupermercado.model.Venda;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

    public List<Venda> findByFuncionarioId(Long funcionarioId, Pageable page);

    public List<Venda> findByClienteId(Long clienteId, Pageable page);

    public List<Venda> findByClienteIdAndFuncionarioId(Long clienteId, Long funcionarioId, Pageable page);


}