package com.ufpr.dt.site.controller;


import com.ufpr.dt.site.entity.Pessoa;
import com.ufpr.dt.site.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;


    @GetMapping
    public ModelAndView mostrarTodos(){
        ModelAndView modelAndView = new ModelAndView("/pessoa");
        modelAndView.addObject("pessoas", pessoaService.mostrarTodos());
        return modelAndView;
    }

    @GetMapping("/insere")
    public ModelAndView insere(Pessoa pessoa) {

        ModelAndView mv = new ModelAndView("/pessoaInsere");
        mv.addObject("pessoa", pessoa);

        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {

        return insere(pessoaService.mostrarUm(id));
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        pessoaService.delete(id);
        return mostrarTodos();
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(@Valid Pessoa pessoa, BindingResult result) {

        if(result.hasErrors()) {
            return insere(pessoa);
        }

        pessoaService.salvar(pessoa);

        ModelAndView modelAndView = new ModelAndView("/login");
        return modelAndView;
    }
}
