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
	


	public Plano findById(long id) {
		return planoRepository.findById(id).get();
	}
	
	@Transactional
	public Plano saveNew(Plano plano) {


		
		return planoRepository.save(plano);
	}
	
	@Transactional
	public Plano update(Plano plano) {

		return planoRepository.save(plano);
	}
	public List<Plano> findAll(){
		return planoRepository.findAll();
	}





}
