package br.itb.projeto.padaria3_fk.service;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.itb.projeto.padaria3_fk.model.entity.Plano;
import br.itb.projeto.padaria3_fk.model.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	private ProdutoRepository produtoRepository;

	public ProdutoService(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	public List<Plano> findAll() {
		List<Plano> produtos = produtoRepository.findAll();
		return produtos;
	}
	
	public List<Plano> findAllByNome(String nome) {
		List<Plano> produtos = produtoRepository.findByNomeContaining(nome);
		return produtos;
	}

	public Plano findById(long id) {
		return produtoRepository.findById(id).get();
	}
	
	@Transactional
	public Plano saveNew(Plano produto) {

		produto.setStatusProduto("ATIVO");
		
		return produtoRepository.save(produto);
	}
	
	@Transactional
	public Plano update(Plano produto) {

		produto.setStatusProduto("ATIVO");
		
		return produtoRepository.save(produto);
	}
	

	@Transactional
	public Plano saveNew(MultipartFile file, Plano produto) {

		if (file != null && file.getSize() > 0) {
			try {
				produto.setFoto(file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			produto.setFoto(null);
		}

		produto.setStatusProduto("ATIVO");
		
		return produtoRepository.save(produto);
	}

	@Transactional
	public void update(MultipartFile file, Plano produto, byte[] foto) {
		
		if (file.getSize() == 0 && foto.length == 0) {
			produto.setFoto(null);
		} 
		
		if (file.getSize() == 0 && foto.length > 0) {
			produto.setFoto(foto);
		} 

		if (file != null && file.getSize() > 0 ) {
			try {
				produto.setFoto(file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		produto.setStatusProduto("ATIVO");
		produtoRepository.save(produto);
	} 


}
