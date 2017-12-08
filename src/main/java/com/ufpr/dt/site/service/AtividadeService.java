package com.ufpr.dt.site.service;

import com.sun.org.apache.regexp.internal.RE;
import com.ufpr.dt.site.entity.Atividade;
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
}
