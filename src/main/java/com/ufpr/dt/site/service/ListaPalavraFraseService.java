package com.ufpr.dt.site.service;

import com.ufpr.dt.site.entity.Lista;
import com.ufpr.dt.site.entity.ListaPalavraFrase;
import com.ufpr.dt.site.entity.PalavraFrase;
import com.ufpr.dt.site.repository.ListaPalavraFraseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListaPalavraFraseService {

    @Autowired
    ListaPalavraFraseRepository listaPalavraFraseRepository;

    public List<PalavraFrase> getListPalavraFrase(Lista lista){
        List<ListaPalavraFrase> listaPalavraFrase = listaPalavraFraseRepository.findByLista(lista);
        List<PalavraFrase> frases = new ArrayList<PalavraFrase>();
        for (int i=0; i < listaPalavraFrase.size(); i++){
            frases.add(listaPalavraFrase.get(i).getPalavraFrase());
        }
        return frases;
    }
}
