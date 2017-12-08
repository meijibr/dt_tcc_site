package com.ufpr.dt.site.controller;

import com.ufpr.dt.site.dto.NovaLista;
import com.ufpr.dt.site.entity.Pessoa;
import com.ufpr.dt.site.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @Autowired
    PessoaRepository pessoaRepository;

    @GetMapping("/")
    public String home1() {
        return "/login";
    }

    @GetMapping("/home")
    public ModelAndView home() {
        return mostrarMinhas();}

    @GetMapping("/admin")
    public String admin() {
        return "/admin";
    }

    @GetMapping("/user")
    public String user() {
        return "/user";
    }

    @GetMapping("/sobre")
    public String sobre() {
        return "/sobre";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }


    public ModelAndView mostrarMinhas(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Pessoa p = pessoaRepository.findByEmail(authentication.getName());
        ModelAndView modelAndView = new ModelAndView("/vo_lista");
        modelAndView.addObject("listas", p.getListas());
        modelAndView.addObject("novaLista", new NovaLista());
        return modelAndView;
    }
}