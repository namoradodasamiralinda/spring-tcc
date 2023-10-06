package br.itb.projeto.olympuss.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.itb.projeto.olympuss.model.entity.Checkin;
import br.itb.projeto.olympuss.model.repository.CheckinRepository;

@Service
public class CheckinService {
	
	private CheckinRepository categoriaRepository;
	
	public CheckinService(CheckinRepository categoriaRepository) {
		super();
		this.categoriaRepository = categoriaRepository;
	}

	public List<Checkin> findAll() {
		return categoriaRepository.findAll();
	}

}
