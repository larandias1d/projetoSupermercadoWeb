package br.edu.iff.projetoSupermercado.repository;

import br.edu.iff.projetoSupermercado.model.Produto;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    
    public List<Produto> findByDescricao(String descricao);
    
    public List<Produto> findByFornecedorId(Long fornecedorId, Pageable page);

    
}
