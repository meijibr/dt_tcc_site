package com.ufpr.dt.site.service;

import com.ufpr.dt.site.entity.Lista;
import com.ufpr.dt.site.repository.ListaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListaService {

    @Autowired
    ListaRepository listaRepository;

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
}
