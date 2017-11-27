package com.ufpr.dt.site.repository;




import com.ufpr.dt.site.entity.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {

    Atividade findByPin(Long id);

    Atividade findById(Long id);
}
