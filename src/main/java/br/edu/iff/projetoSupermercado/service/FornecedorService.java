
package br.edu.iff.projetoSupermercado.service;

import br.edu.iff.projetoSupermercado.model.Fornecedor;
import br.edu.iff.projetoSupermercado.repository.FornecedorRepository;
import java.util.List;
import java.util.Optional;
import br.edu.iff.projetoSupermercado.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class FornecedorService {
    
    @Autowired
    private FornecedorRepository repo;
    
    public List<Fornecedor> findAll(int page, int size) {
        Pageable p = PageRequest.of(page, size);
        return repo.findAll(p).toList();
    }

    public List<Fornecedor> findAll() {
        return repo.findAll();
    }

    public Fornecedor findById(Long id) {
        Optional<Fornecedor> result = repo.findById(id);
        if (result.isEmpty()) {
            throw new NotFoundException("Fornecedor não encontrado.");
        }
        return result.get();
    }

    public Fornecedor save(Fornecedor f)  {
       
        verificaCnpjEmailCadastrado(f.getCnpj(), f.getEmail());
        try {
            return repo.save(f);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o fornecedor.");
        }
    }

    public Fornecedor update(Fornecedor f) {

        Fornecedor obj = findById(f.getId());

        try {
            f.setCnpj(obj.getCnpj());
            f.setEmail(obj.getEmail());
            return repo.save(f);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar o fornecedor.");
        }
    }

    public void delete(Long id) {
        Fornecedor obj = findById(id);

        //verificaExclusaoFornecedorComProdutos(obj);
        try {
            repo.delete(obj);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao excluir o fornecedor.");
        }
    }

    private void verificaCnpjEmailCadastrado(String cnpj, String email)  {
        List<Fornecedor> result = repo.findByCnpjOrEmail(cnpj, email);
        if (!result.isEmpty()) {
            throw new RuntimeException("CNPJ ou EMAIL já cadastrados.");
        }
    }

 /*   private void verificaExclusaoFornecedorComProdutos(Fornecedor f)  {
        if (!f.getProdutos().isEmpty()) {
            throw new RuntimeException("Fornecedor possui produtos. Não pode ser excluído.");
        }
    } */
    
}
