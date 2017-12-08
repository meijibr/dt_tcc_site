package com.ufpr.dt.site.repository;

import com.ufpr.dt.site.entity.Palavra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PalavraRepository extends JpaRepository<Palavra, Long> {
}
