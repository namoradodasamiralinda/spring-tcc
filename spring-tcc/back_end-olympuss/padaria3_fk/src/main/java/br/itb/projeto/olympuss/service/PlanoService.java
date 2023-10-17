package br.itb.projeto.olympuss.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.itb.projeto.olympuss.model.entity.Plano;
import br.itb.projeto.olympuss.model.repository.PlanoRepository;
import jakarta.transaction.Transactional;

@Service
public class PlanoService {
	
	private static PlanoRepository planoRepository;

	public PlanoService(PlanoRepository planoRepository) {
		PlanoService.planoRepository = planoRepository;
	}

	public static List<Plano> findAll1() {
		List<Plano> Plano = planoRepository.findAll();
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
	public List<Plano> findAll(){
		return planoRepository.findAll();
	}





}
