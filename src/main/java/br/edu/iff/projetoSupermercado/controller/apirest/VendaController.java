package br.edu.iff.projetoSupermercado.controller.apirest;

import br.edu.iff.projetoSupermercado.model.Venda;
import br.edu.iff.projetoSupermercado.service.VendaService;
import javassist.NotFoundException;
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

@RequestMapping(path = "/apirest/vendas")

public class VendaController {
    
    @Autowired
    private VendaService service;
    
    @GetMapping
    public ResponseEntity getAll(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            @RequestParam(name = "clienteId", defaultValue = "0", required = false) Long clienteId,
            @RequestParam(name = "funcionarioId", defaultValue = "0", required = false) Long funcionarioId) {

        return ResponseEntity.ok(service.findAll(page, size, clienteId, funcionarioId));
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity getOne(@PathVariable("id") Long id) throws NotFoundException{
        return ResponseEntity.ok(service.findById(id));
    }
    
    @PostMapping
    public ResponseEntity save(@RequestBody Venda venda) throws NotFoundException{
        venda.setId(null);
        service.save(venda);
        return ResponseEntity.status(HttpStatus.CREATED).body(venda);
    }
    
    @PutMapping(path = "/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Venda venda) throws NotFoundException{
        venda.setId(id);
        service.update(venda);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) throws NotFoundException{
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
