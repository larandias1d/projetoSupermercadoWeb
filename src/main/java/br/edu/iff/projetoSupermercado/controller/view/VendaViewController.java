

package br.edu.iff.projetoSupermercado.controller.view;

import br.edu.iff.projetoSupermercado.model.Venda;
import br.edu.iff.projetoSupermercado.service.ClienteService;
import br.edu.iff.projetoSupermercado.service.FuncionarioService;
import br.edu.iff.projetoSupermercado.service.ProdutoService;
import br.edu.iff.projetoSupermercado.service.VendaService;
import java.util.ArrayList;
import java.util.List;
import javassist.NotFoundException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(path = "/vendas")
public class VendaViewController {
    
    @Autowired
    private VendaService service;

    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("vendas", service.findAll());
        return "vendas";
    }

    @GetMapping(path = "/venda")
    public String cadastro(Model model) {
        model.addAttribute("venda", new Venda());
        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("funcionarios", funcionarioService.findAll());
        model.addAttribute("produtos", produtoService.findAll());
        return "formVenda";
    }

    @PostMapping(path = "/venda")
    public String save(@Valid @ModelAttribute Venda venda, BindingResult result, Model model) {

        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("funcionarios", funcionarioService.findAll());
        model.addAttribute("produtos", produtoService.findAll());

        //Elimina erro de dataHora
        List<FieldError> list = new ArrayList<>();
        for (FieldError fe : result.getFieldErrors()) {
            if (!fe.getField().equals("datahora")) {
                list.add(fe);
            }
        }
        if (!list.isEmpty()) {
            model.addAttribute("msgErros", list);
            return "formVenda";
        }

        venda.setId(null);
        try {
            service.save(venda);
            model.addAttribute("msgSucesso", "Venda cadastrada com sucesso.");
            model.addAttribute("venda", new Venda());
            return "formVenda";
        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("venda", e.getMessage()));
            return "formVenda";
        }
    }

    @GetMapping(path = "/venda/{id}")
    public String alterar(@PathVariable("id") Long id, Model model) throws NotFoundException {
        model.addAttribute("venda", service.findById(id));
        model.addAttribute("produtos", produtoService.findAll());
        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("funcionarios", funcionarioService.findAll());

        return "formVenda";
    }

    @PostMapping(path = "/venda/{id}")
    public String update(@Valid @ModelAttribute Venda venda, BindingResult result, @PathVariable("id") Long id, Model model) {
        //Valores a serem retornados no model
        model.addAttribute("produtos", produtoService.findAll());
        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("funcionarios", funcionarioService.findAll());

        if (result.hasErrors()) {
            model.addAttribute("msgErros", result.getAllErrors());
            return "formVenda";
        }
        venda.setId(id);
        try {
            service.update(venda);
            model.addAttribute("msgSucesso", "Venda atualizada com sucesso.");
            model.addAttribute("venda", venda);
            return "formVenda";
        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("Venda", e.getMessage()));
            return "formVenda";
        }
    }

    @GetMapping(path = "/{id}/deletar")
    public String deletar(@PathVariable("id") Long id) throws NotFoundException {
        service.delete(id);
        return "redirect:/vendas";
    }
    
}
