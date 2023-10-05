package br.itb.projeto.padaria3_fk.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.itb.projeto.padaria3_fk.model.entity.CHECKIN;
import br.itb.projeto.padaria3_fk.model.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	private CategoriaRepository categoriaRepository;
	
	public CategoriaService(CategoriaRepository categoriaRepository) {
		super();
		this.categoriaRepository = categoriaRepository;
	}

	public List<CHECKIN> findAll() {
		return categoriaRepository.findAll();
	}

}
