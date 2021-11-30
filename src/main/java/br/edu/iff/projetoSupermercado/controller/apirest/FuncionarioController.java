package br.edu.iff.projetoSupermercado.controller.apirest;

import br.edu.iff.projetoSupermercado.model.Funcionario;

import br.edu.iff.projetoSupermercado.service.FuncionarioService;
import javassist.NotFoundException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/apirest/funcionarios")

public class FuncionarioController {
    @Autowired
   
    private  FuncionarioService service;
    
    @GetMapping
    public ResponseEntity getAll(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required =  false) int size){
        
        return ResponseEntity.ok(service.findAll(page, size));
    }
    
    @GetMapping(path = "/{id}")
    public  ResponseEntity getOne(@PathVariable("id") Long id) throws NotFoundException{
        return ResponseEntity.ok(service.findById(id));
    }
    
    
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Funcionario funcionario) throws NotFoundException{
        funcionario.setId(null);
        service.save(funcionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionario);
    }
        
    @PutMapping(path = "/{id")
    public ResponseEntity update(@PathVariable("id") Long id, @Valid @RequestBody Funcionario funcionario) throws NotFoundException{
        funcionario.setId(id);
        service.update(funcionario, "", "", "");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        
    }
    
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) throws NotFoundException {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping(path = "/{id}/alterarSenha")
    public ResponseEntity alterarSenha(@PathVariable("id") Long id,
            @RequestParam(name = "senhaAtual", defaultValue ="", required =  true) String senhaAtual,
            @RequestParam(name = "novaAtual", defaultValue ="", required =  true) String novaAtual,
            @RequestParam(name = "confirmarSenha", defaultValue ="", required =  true) String confirmarSenha) throws NotFoundException{
        
        Funcionario f = service.findById(id);
        service.update(f, senhaAtual, novaAtual, confirmarSenha);
                return ResponseEntity.ok().build();
        
    }
}
