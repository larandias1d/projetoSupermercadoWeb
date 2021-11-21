
package br.edu.iff.projetoSupermercado.service;

import br.edu.iff.projetoSupermercado.model.Funcionario;
import br.edu.iff.projetoSupermercado.model.Pessoa;
import br.edu.iff.projetoSupermercado.repository.FuncionarioRepository;
import java.util.List;
import java.util.Optional;
import br.edu.iff.projetoSupermercado.exception.NotFoundException;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repo;

    public List<Funcionario> findAll(int page, int size) {
        Pageable p = PageRequest.of(page, size);
        return repo.findAll(p).toList();
    }

    public List<Funcionario> findAll() {
        return repo.findAll();
    }

    public Funcionario findByEmail(String email) {
        return repo.findByEmail(email);
    }

    public Funcionario findById(Long id)  {
        Optional<Funcionario> result = repo.findById(id);
        if (result.isEmpty()) {
            throw new NotFoundException("Funcionario não encontrado.");
        }
        return result.get();
    }

    public Funcionario save(Funcionario f) {
        verificaCpfEmailCadastrado(f.getCpf(), f.getEmail());
        try {
            return repo.save(f);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o Funcionario.");
        }
    }

    public Funcionario update(Funcionario f, String senhaAtual, String novaSenha, String confirmarNovaSenha){
        
        Funcionario obj = findById(f.getId());

        alterarSenha(obj, senhaAtual, novaSenha, confirmarNovaSenha);
        try {
            f.setCpf(obj.getCpf());
            f.setEmail(obj.getEmail());
            f.setSenha(obj.getSenha());
            f.setCargo(obj.getCargo());
            f.setSalario(obj.getSalario());
            return repo.save(f);
        } catch (Exception e) {
            Throwable t = e;
            while (t.getCause() != null) {
                t = t.getCause();
                if (t instanceof ConstraintViolationException) {
                    throw ((ConstraintViolationException) t);
                }
            }
            throw new RuntimeException("Erro ao atualizar o funcionario.");
        }
    }

    public void delete(Long id) {
        Funcionario obj = findById(id);
        verificaExclusaoClienteComVendas(obj);
        try {
            repo.delete(obj);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir o funcionario");
        }

    }

    private void verificaCpfEmailCadastrado(String cpf, String email) {
        List<Pessoa> result = repo.findByCpfOrEmail(cpf, email);
        if (!result.isEmpty()) {
            throw new RuntimeException("CPF ou EMAIL já cadastrados.");
        }
    }

    private void alterarSenha(Funcionario obj, String senhaAtual, String novaSenha, String confirmarNovaSenha) {

         if (!senhaAtual.isBlank() && !novaSenha.isBlank() && !confirmarNovaSenha.isBlank()) {
            if (senhaAtual.equals(obj.getSenha())) {
                throw new RuntimeException("Senha atual está incorreta.");
            }
            if (!novaSenha.equals(confirmarNovaSenha)) {
                throw new RuntimeException("Nova Senha e Confirmar Nova Senha não conferem.");
            }
            obj.setSenha(novaSenha);
        }
    }

    private void verificaExclusaoClienteComVendas(Funcionario f) {
        if (!f.getVendas().isEmpty()) {
            throw new RuntimeException("Funcionario possui vendas. Não pode ser excluído.");
        }
    }

    public void save(Funcionario funcionario, MultipartFile file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}