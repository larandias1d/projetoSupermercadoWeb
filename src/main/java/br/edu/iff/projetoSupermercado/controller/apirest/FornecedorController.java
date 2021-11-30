package br.edu.iff.projetoSupermercado.controller.apirest;

import br.edu.iff.projetoSupermercado.model.Fornecedor;
import br.edu.iff.projetoSupermercado.service.FornecedorService;
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
@RequestMapping(path = "/apirest/fornecedores")
public class FornecedorController {
    
    @Autowired
    
    private FornecedorService service;
    
    @GetMapping          
    public ResponseEntity getAll(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll(page, size));   
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity getOne(@PathVariable("id") Long id) throws NotFoundException {
        return ResponseEntity.ok(service.findById(id));
    }
    
    @PostMapping
    public ResponseEntity save (@Valid @RequestBody Fornecedor fornecedor) throws NotFoundException {
        fornecedor.setId(null);
        service.save(fornecedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(fornecedor);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @Valid @RequestBody Fornecedor fornecedor) throws NotFoundException{
        fornecedor.setId(id);
        service.update(fornecedor);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete (@PathVariable("id") Long id) throws NotFoundException{
        service.delete(id);
        return ResponseEntity.ok().build();
    }
    
}