package com.ufpr.dt.site.repository;


import com.ufpr.dt.site.entity.Palavra;
import com.ufpr.dt.site.entity.Pessoa;
import com.ufpr.dt.site.entity.PessoaPalavra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaPalavraRepository extends JpaRepository<PessoaPalavra, Long> {

    PessoaPalavra findById(Long id);

    PessoaPalavra findByPessoaAndPalavra(Pessoa pessoa, Palavra palavra);
}
