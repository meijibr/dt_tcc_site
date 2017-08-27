package com.ufpr.dt.site.controller;

import com.ufpr.dt.site.entity.Pessoa;
import com.ufpr.dt.site.service.PessoaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaController {

    private static final Logger log = LoggerFactory.getLogger(PessoaController.class);

    @Autowired
    private PessoaService pessoaService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody Pessoa pessoa) {
        try {
            Pessoa pessoaSalva = pessoaService.create(pessoa);
            return new ResponseEntity<Pessoa>(pessoaSalva , HttpStatus.CREATED);
        } catch (RuntimeException e) {
            log.debug("Falha ao criar um novo funcionario: {}. Erro: {}", pessoa, e.getMessage());
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<List<Pessoa>>(pessoaService.listAll(), HttpStatus.OK);
    }

}
