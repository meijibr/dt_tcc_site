package com.ufpr.dt.site.controller;

import com.ufpr.dt.site.dto.Frase;
import com.ufpr.dt.site.dto.NovaLista;
import com.ufpr.dt.site.entity.Lista;
import com.ufpr.dt.site.entity.Pessoa;
import com.ufpr.dt.site.repository.PessoaRepository;
import com.ufpr.dt.site.service.FraseService;
import com.ufpr.dt.site.service.ListaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/listas")
public class ListaController {

    @Autowired
    ListaService listaService;

    @Autowired
    FraseService fraseService;

    @Autowired
    PessoaRepository pessoaRepository;

    @GetMapping
    public ModelAndView mostrarTodos(){
        ModelAndView modelAndView = new ModelAndView("/vo_lista");
        modelAndView.addObject("listas", listaService.mostrarTodos());
        modelAndView.addObject("novaLista", new NovaLista());
        return modelAndView;
    }
    @GetMapping("/minhas")
    public ModelAndView mostrarMinhas(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Pessoa p = pessoaRepository.findByEmail(authentication.getName());
        ModelAndView modelAndView = new ModelAndView("/vo_lista");
        modelAndView.addObject("listas", p.getListas());
        modelAndView.addObject("novaLista", new NovaLista());
        return modelAndView;
    }

    @PostMapping("/salvar")
    public ModelAndView salvarLista(NovaLista novaLista, BindingResult result) {
        if(result.hasErrors()) {
            return mostrarTodos();
        }

        Lista lista = listaService.salvar(novaLista);
        return insere(lista);
    }

    @GetMapping("/insere")
    public ModelAndView insere(Lista lista) {

        ModelAndView mv = new ModelAndView("/listaInsere");
        mv.addObject("lista", lista);
        mv.addObject("frases", lista.getPalavrasFrases());
        mv.addObject("novaFrase", new Frase());
        return mv;
    }

    @PostMapping("/editar/{id}/frase")
    public ModelAndView editarFrases(@PathVariable("id") Long id, @Valid Frase frase, BindingResult result) {
        if(result.hasErrors()) {
            return insere(listaService.findById(id));
        }

        return insere(fraseService.fraseIntoLista(listaService.findById(id),frase));
    }


    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        return insere(listaService.mostrarUm(id));
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        listaService.delete(id);
        return mostrarTodos();
    }

    @GetMapping("/editar/{id}/frase/{fid}")
    public ModelAndView deleteFrase(@PathVariable("id") Long id, @PathVariable("fid") Long fid) {
        fraseService.deleteFrase(fid);
        return insere(listaService.findById(id));
    }
}
