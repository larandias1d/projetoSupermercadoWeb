
package br.edu.iff.projetoSupermercado.controller.view;

import br.edu.iff.projetoSupermercado.model.Funcionario;
import br.edu.iff.projetoSupermercado.service.FuncionarioService;
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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/funcionarios")
public class FuncionarioViewController {

    @Autowired
    private FuncionarioService service;
    

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("funcionarios", service.findAll());
        return "funcionarios";
    }

    @GetMapping(path = "/funcionario")
    public String cadastro(Model model) {
        model.addAttribute("funcionario", new Funcionario());
        return "formFuncionario";
    }

    @PostMapping(path = "/funcionario")
    public String save(@Valid @ModelAttribute Funcionario funcionario,
            BindingResult result,
            @RequestParam("confirmarSenha") String confirmarSenha,
            Model model) {


        if (result.hasErrors()) {
            model.addAttribute("msgErros", result.getAllErrors());
            return "formFuncionario";
        }

        if (!funcionario.getSenha().equals(confirmarSenha)) {
            model.addAttribute("msgErros", new ObjectError("funcionario", "Campos Senha e Confirmar Senha devem ser iguais."));
            return "formFuncionario";
        }

        funcionario.setId((Long) null);
        try {
            service.save(funcionario);
            model.addAttribute("msgSucesso", "Funcionário cadastrado com sucesso.");
            model.addAttribute("funcionario", new Funcionario());
            return "formFuncionario";
        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("funcionario", e.getMessage()));
            return "formFuncionario";
        }
    }

    @GetMapping(path = "/funcionario/{id}")
    public String atualizacao(@PathVariable("id") Long id, Model model) throws NotFoundException {
        model.addAttribute("funcionario", service.findById(id));
        return "formFuncionario";
    }

    @PostMapping(path = "/funcionario/{id}")
    public String update(@Valid @ModelAttribute Funcionario funcionario,
            BindingResult result,
            @PathVariable("id") Long id,
            Model model) {

        List<FieldError> list = new ArrayList<>();
        for (FieldError fe : result.getFieldErrors()) {
            if (!fe.getField().equals("senha")) {
                list.add(fe);
            }
        }
        if (!list.isEmpty()) {
            model.addAttribute("msgErros", list);
            return "formFuncionario";
        }

        funcionario.setId(id);
        try {
            service.update(funcionario, "", "", "");
            model.addAttribute("msgSucesso", "Funcionário atualizado com sucesso.");
            model.addAttribute("funcionario", funcionario);
            return "formFuncionario";
        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("funcionario", e.getMessage()));
            return "formFuncionario";
        }
    }

    @GetMapping(path = "/{id}/deletar")
    public String deletar(@PathVariable("id") Long id) throws NotFoundException {
        service.delete(id);
        return "redirect:/funcionarios";
    }
    
}
