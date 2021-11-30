
package br.edu.iff.projetoSupermercado.service;

import br.edu.iff.projetoSupermercado.model.Venda;
import br.edu.iff.projetoSupermercado.repository.VendaRepository;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import br.edu.iff.projetoSupermercado.exception.NotFoundException;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VendaService {

    @Autowired
    private VendaRepository repo;

    public List<Venda> findAll() {
        return repo.findAll();
    }

    public List<Venda> findAll(int page, int size, Long clienteId, Long funcionarioId) {
        Pageable p = PageRequest.of(page, size);
        if (clienteId != 0 && funcionarioId != 0) {
            return repo.findByClienteIdAndFuncionarioId(clienteId, funcionarioId, p);
        } else if (clienteId != 0) {
            return repo.findByClienteId(clienteId, p);
        }
        if (funcionarioId != 0) {
            return repo.findByFuncionarioId(funcionarioId, p);
        }

        return repo.findAll(p).toList();
    }

    public Venda findById(Long id) {
        Optional<Venda> obj = repo.findById(id);
        if (obj.isEmpty()) {
            throw new NotFoundException("Venda não encontrado.");
        }
        return obj.get();
    }

    public Venda save(Venda v) {

        try {
            v.setDatahora(Calendar.getInstance());
            return repo.save(v);
        } catch (Exception e) {
            Throwable t = e;
            while (t.getCause() != null) {
                t = t.getCause();
                if (t instanceof ConstraintViolationException) {
                    throw ((ConstraintViolationException) t);
                }
            }

            throw new RuntimeException("Erro ao salvar a Venda.");
        }
    }

    public Venda update(Venda v)  {
        
        Venda obj = findById(v.getId());

        try {
            v.setDatahora(Calendar.getInstance());

            return repo.save(v);
        } catch (Exception e) {
            Throwable t = e;
            while (t.getCause() != null) {
                t = t.getCause();
                if (t instanceof ConstraintViolationException) {
                    throw ((ConstraintViolationException) t);
                }
            }
            throw new RuntimeException("Erro ao atualizar a venda");
        }
    }


    public void delete(Long id)  {
        Venda obj = findById(id);
        try {

            repo.delete(obj);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar a venda.");
        }
    }

    public Object findAll(Long idProduto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}    