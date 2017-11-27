package com.ufpr.dt.site.repository;

import com.ufpr.dt.site.entity.Lista;
import com.ufpr.dt.site.entity.ListaPalavraFrase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListaPalavraFraseRepository extends JpaRepository<ListaPalavraFrase, Long> {

    List<ListaPalavraFrase> findByLista(Lista lista);
}
