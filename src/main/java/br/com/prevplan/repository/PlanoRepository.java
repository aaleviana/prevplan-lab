package br.com.prevplan.repository;

import br.com.prevplan.model.Plano;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanoRepository extends JpaRepository<Plano, Long> {
    long countByAtivoTrue();
}
