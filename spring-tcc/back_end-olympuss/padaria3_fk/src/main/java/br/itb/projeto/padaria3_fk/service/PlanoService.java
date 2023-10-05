package br.itb.projeto.padaria3_fk.service;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.itb.projeto.padaria3_fk.model.entity.Plano;
import br.itb.projeto.padaria3_fk.model.repository.PlanoRepository;

@Service
public class PlanoService {
	
	private static PlanoRepository planoRepository;

	public PlanoService(PlanoRepository planoRepository) {
		PlanoService.planoRepository = planoRepository;
	}

	public static List<Plano> findAll() {
		List<Plano> Plano = planoRepository.findAll();
		return Plano;
	}
	
	public List<Plano> findAllByNome(String nome) {
		List<Plano> Plano = planoRepository.findByNomeContaining(nome);
		return Plano;
	}

	public static Plano findById(long id) {
		return planoRepository.findById(id).get();
	}
	
	@Transactional
	public Plano saveNew(Plano Plano) {


		
		return planoRepository.save(Plano);
	}
	
	@Transactional
	public Plano update(Plano Plano) {

		return planoRepository.save(Plano);
	}





}
