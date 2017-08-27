package com.ufpr.dt.site.service;

import com.ufpr.dt.site.entity.Pessoa;
import com.ufpr.dt.site.repository.PessoaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    private static final Logger log = LoggerFactory.getLogger(PessoaService.class);

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa create(Pessoa pessoa) {
        log.debug("metodo create(Employee) chamado com = " + pessoa);
        return pessoaRepository.save(pessoa);
    }

    public List<Pessoa> listAll() {
        return pessoaRepository.findAll();
    }
}
