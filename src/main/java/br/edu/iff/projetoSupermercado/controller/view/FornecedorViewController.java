package br.edu.iff.projetoSupermercado.controller.view;

import br.edu.iff.projetoSupermercado.model.Fornecedor;
import br.edu.iff.projetoSupermercado.service.FornecedorService;
import javassist.NotFoundException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(path = "/fornecedores")

public class FornecedorViewController {
    
    @Autowired
    private FornecedorService service;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("fornecedores", service.findAll());
        return "fornecedores";
    }

    @GetMapping(path = "/fornecedor")
    public String cadastro(Model model) {
        model.addAttribute("fornecedor", new Fornecedor());
        return "formFornecedor";
    }

    @PostMapping(path = "/fornecedor")
    public String salvar(@Valid @ModelAttribute Fornecedor fornecedor, BindingResult result,
            @RequestParam("file") MultipartFile file, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("msgErros", result.getAllErrors());
            return "formFornecedor";
        }
      
        try {
            service.save(fornecedor);
            model.addAttribute("msgSucesso", "Fornecedor cadastrado com sucesso.");
            model.addAttribute("fornecedor", new Fornecedor());
            return "formFornecedor";
        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("fornecedor", e.getMessage()));
            return "formFornecedor";
        }
    }
    
    @GetMapping(path = "/fornecedor/{id}")
    public String editar(@PathVariable("id") Long id, Model model) throws NotFoundException {
        model.addAttribute("fornecedor", service.findById(id));
        return "formFornecedor";
    }
    
    @PostMapping(path = "/fornecedor/{id}")
    public String atualizar(@Valid @ModelAttribute Fornecedor fornecedor, BindingResult result,
            @PathVariable("id") Long id, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("msgErros", result.getAllErrors());
            return "formFornecedor";
        }
        fornecedor.setId(id);
        try {
            service.update(fornecedor);
            model.addAttribute("msgSucesso", "Fornecedor cadastrado com sucesso.");
            model.addAttribute("fornecedor", fornecedor);
            return "formFornecedor";
        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("fornecedor", e.getMessage()));
            return "formFornecedor";
        }
    }
    
    @GetMapping(path = "{id}/deletar")
    public String deletar(@PathVariable("id") Long id) throws NotFoundException{
        service.delete(id);
        return "redirect:/fornecedores";
    }
    
}