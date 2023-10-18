package br.itb.projeto.olympuss.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.itb.projeto.olympuss.model.entity.Plano;

@Repository
public interface PlanoRepository 
extends JpaRepository<Plano, Long> {
	
	Plano findByNOME(String NOME);

 
}
