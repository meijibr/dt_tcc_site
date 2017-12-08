package com.ufpr.dt.site.service;

import com.sun.org.apache.regexp.internal.RE;
import com.ufpr.dt.site.dto.AtividadeLista;
import com.ufpr.dt.site.dto.AtividadeRelatorio;
import com.ufpr.dt.site.dto.FraseAvaliacao;
import com.ufpr.dt.site.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AtividadeService {

    @Autowired
    ListaPalavraFraseService listaPalavraFraseService;

    @Autowired
    PessoaPalavraService pessoaPalavraService;

    public void start(Atividade atividade) {
        RestTemplate restTemplate = new RestTemplate();
        String uri = "/atividade/" + atividade.getPin() + "/iniciar";

        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080" + uri, String.class);
        System.out.printf("\n " + response + "\n");
    }

    public void create(Atividade atividade) {
        System.out.printf("\n Criar jogadores--- " + atividade.getPin().toString() + "\n");
        String uri = "/atividade/" + atividade.getPin() + "/criar";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080" + uri, String.class);
        System.out.printf("\n " + response + "\n");
        System.out.printf("\n criou---\n");
    }

    public List<AtividadeLista> atividadeLista(Pessoa p) {
        List<Atividade> atividades = p.getAtividades();
        List<AtividadeLista> atividadeListas = new ArrayList<>();
        for (Atividade atividade : atividades) {
            AtividadeLista atividadeLista = new AtividadeLista();
            atividadeLista.setNomeLista(atividade.getLista().getNome());
            atividadeLista.setTipoAtividade(atividade.getTipoAtividade().getAtividade());
            atividadeLista.setRounds(Long.toString(atividade.getRounds()));
            atividadeLista.setPin(Long.toString(atividade.getPin()));
            atividadeListas.add(atividadeLista);
        }
        return atividadeListas;
    }

    public List<AtividadeRelatorio> atividadeRelatorios(Atividade a) {
        List<AtividadeRelatorio> atividadeRelatorios = new ArrayList<>();
        for (Pessoa p : a.getPessoas()) {
            List<PalavraFrase> frases;
            frases = listaPalavraFraseService.getListPalavraFrase(a.getLista());
            for (int j = 0; j < frases.size(); j++) {
                AtividadeRelatorio atividadeRelatorio = new AtividadeRelatorio();
                double ratio = pessoaPalavraService.getAvaliacao(p, frases.get(j).getPalavra());
                atividadeRelatorio.setNomeAluno(p.getNome());
                atividadeRelatorio.setPalavra(frases.get(j).getFrase());
                atividadeRelatorio.setNotaAluno(Double.toString(ratio));
                atividadeRelatorios.add(atividadeRelatorio);
            }
        }
        return atividadeRelatorios;
    }
}
