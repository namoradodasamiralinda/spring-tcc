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

		Plano.setStatusProduto("ATIVO");
		
		return planoRepository.save(Plano);
	}
	
	@Transactional
	public Plano update(Plano Plano) {

		Plano.setStatusProduto("ATIVO");
		
		return planoRepository.save(Plano);
	}
	

	@Transactional
	public Plano saveNew(MultipartFile file, Plano Plano) {

		if (file != null && file.getSize() > 0) {
			try {
				Plano.setFoto(file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			Plano.setFoto(null);
		}

		Plano.setStatusProduto("ATIVO");
		
		return planoRepository.save(Plano);
	}

	@Transactional
	public void update(MultipartFile file, Plano Plano, byte[] foto) {
		
		if (file.getSize() == 0 && foto.length == 0) {
			Plano.setFoto(null);
		} 
		
		if (file.getSize() == 0 && foto.length > 0) {
			Plano.setFoto(foto);
		} 

		if (file != null && file.getSize() > 0 ) {
			try {
				Plano.setFoto(file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Plano.setStatusProduto("ATIVO");
		planoRepository.save(Plano);
	} 


}
