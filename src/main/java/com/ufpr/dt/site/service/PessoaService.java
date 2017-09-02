package com.ufpr.dt.site.service;



import com.ufpr.dt.site.entity.Pessoa;
import com.ufpr.dt.site.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa salvar(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public List<Pessoa> mostrarTodos() {
        return pessoaRepository.findAll();
    }

    public Pessoa mostrarUm(Long id) {
        return pessoaRepository.findOne(id);
    }

    public void delete(Long id){
        pessoaRepository.delete(id);
    }
}
