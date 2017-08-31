package com.ufpr.dt.site.repository;


import com.ufpr.dt.site.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
