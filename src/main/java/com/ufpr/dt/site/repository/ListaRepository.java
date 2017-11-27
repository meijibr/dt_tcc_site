package com.ufpr.dt.site.repository;


import com.ufpr.dt.site.entity.Lista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListaRepository extends JpaRepository<Lista, Long> {

    Lista findById(Long id);
}
