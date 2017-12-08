package com.ufpr.dt.site.service;

import com.ufpr.dt.site.dto.Frase;
import com.ufpr.dt.site.entity.Lista;
import com.ufpr.dt.site.entity.ListaPalavraFrase;
import com.ufpr.dt.site.entity.Palavra;
import com.ufpr.dt.site.entity.PalavraFrase;
import com.ufpr.dt.site.repository.ListaPalavraFraseRepository;
import com.ufpr.dt.site.repository.ListaRepository;
import com.ufpr.dt.site.repository.PalavraFraseRepository;
import com.ufpr.dt.site.repository.PalavraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FraseService {

    @Autowired
    PalavraRepository palavraRepository;

    @Autowired
    PalavraFraseRepository palavraFraseRepository;

    @Autowired
    ListaPalavraFraseRepository listaPalavraFraseRepository;

    @Autowired
    ListaRepository listaRepository;

    public Lista fraseIntoLista(Lista lista, Frase frase){

        Palavra p = new Palavra();
        p.setIdioma(frase.getIdioma());
        p.setPalavra(frase.getPalavra());
        p = palavraRepository.save(p);

        PalavraFrase pf = new PalavraFrase();
        pf.setPalavra(p);
        pf.setFrase(frase.getFrase());
        pf.setTraducao(frase.getTraducao());
        pf = palavraFraseRepository.save(pf);

        ListaPalavraFrase lpf = new ListaPalavraFrase();
        lpf.setLista(lista);
        lpf.setPalavraFrase(pf);
        listaPalavraFraseRepository.save(lpf);

        return lista;
    }

    public void deleteFrase(Long fid) {
        listaPalavraFraseRepository.delete(fid);
    }
}
