package com.ufpr.dt.site.controller;

import com.ufpr.dt.site.entity.Lista;
import com.ufpr.dt.site.service.ListaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/listas")
public class ListaController {

    @Autowired
    ListaService listaService;

//    @GetMapping
//    public ModelAndView mostrarTodos(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("listas", listaService.mostrarTodos());
//        return modelAndView;
//    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Lista>> listarAtividades() {
        List<Lista> atividades = listaService.mostrarTodos();
        if(atividades.isEmpty()){
            return new ResponseEntity<List<Lista>>(HttpStatus.NOT_FOUND);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Lista>>(atividades, HttpStatus.OK);
    }

    @GetMapping("/insere")
    public ModelAndView insere(Lista lista) {

        ModelAndView mv = new ModelAndView("/listaInsere");
        mv.addObject("lista", lista);

        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        return insere(listaService.mostrarUm(id));
    }
}
