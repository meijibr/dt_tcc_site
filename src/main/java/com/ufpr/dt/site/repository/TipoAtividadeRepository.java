package com.ufpr.dt.site.repository;


import com.ufpr.dt.site.entity.TipoAtividade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoAtividadeRepository extends JpaRepository<TipoAtividade, Long> {

    TipoAtividade findById(Long id);
}
