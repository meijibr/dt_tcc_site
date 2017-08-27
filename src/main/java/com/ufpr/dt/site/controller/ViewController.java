package com.ufpr.dt.site.controller;

import com.ufpr.dt.site.entity.Pessoa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    private static final Logger log = LoggerFactory.getLogger(ViewController.class);

    @GetMapping("/inserir")
    public String inserirPessoa(Pessoa pessoa){
        return "pessoa";
    }
}
