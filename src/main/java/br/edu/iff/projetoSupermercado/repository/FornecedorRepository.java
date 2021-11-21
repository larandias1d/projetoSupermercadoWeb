package br.edu.iff.projetoSupermercado.repository;
import br.edu.iff.projetoSupermercado.model.Fornecedor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>{
    
    @Query("SELECT f FROM Fornecedor f WHERE f.cnpj = :cnpj OR f.razao_social = :razao_social")
    public List<Fornecedor> findByCnpjOrEmail(@Param("cnpj") String cnpj, @Param("razao_social") String razao_social);
    
}
