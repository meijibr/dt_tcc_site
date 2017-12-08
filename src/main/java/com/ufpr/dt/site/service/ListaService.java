package com.ufpr.dt.site.service;

import com.ufpr.dt.site.dto.NovaLista;
import com.ufpr.dt.site.entity.Lista;
import com.ufpr.dt.site.entity.Pessoa;
import com.ufpr.dt.site.repository.ListaRepository;
import com.ufpr.dt.site.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListaService {

    @Autowired
    ListaRepository listaRepository;

    @Autowired
    PessoaRepository pessoaRepository;

    public Lista findById(Long id){
        return listaRepository.findById(id);
    }

    public List<Lista> mostrarTodos() {
        return listaRepository.findAll();
    }

    public Lista mostrarUm(Long id) {
        return listaRepository.findOne(id);
    }

    public void delete(Long id){
        listaRepository.delete(id);
    }

    public Lista salvar(NovaLista novaLista) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Pessoa p = pessoaRepository.findByEmail(authentication.getName());
        Lista lista = listaRepository.findById(novaLista.getId());
        if (lista == null){
            lista = new Lista();
        }
        lista.setPessoa(p);
        lista.setIdioma(novaLista.getIdioma());
        lista.setNome(novaLista.getNome());
        lista.setTurma(novaLista.getTurma());
        return listaRepository.save(lista);
    }
}
