package br.itb.projeto.olympuss.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.itb.projeto.olympuss.model.entity.Usuario;
import br.itb.projeto.olympuss.model.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService {
	
	private UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	
	@Transactional
	public Usuario acessar(String email, String senha) {
		
		Usuario usuario = usuarioRepository.findByEMAIL(email);


		if (usuario != null) {
			
			if (usuario.getSENHA().equals(senha)) {
				
				return usuario;
			
			}
		} 
		return null;	
	}

	@Transactional
	public Usuario save(Usuario usuario) {

//		String senha = Base64.getEncoder().encodeToString(usuario.getSENHA().getBytes());
//		usuario.setSENHA(senha);
		usuario.setFOTO(null);
		usuario.setNIVEL_ACESSO("USER");
		usuario.setSTATUS_USUARIO("ATIVO");
		
		return usuarioRepository.save(usuario);
	}
	
	@Transactional
	public Usuario saveNewUser(Usuario usuario) {

		usuario.setFOTO(null);
//		String senha = Base64.getEncoder().encodeToString("12345678".getBytes());
//		usuario.setSENHA(senha);
		usuario.setNIVEL_ACESSO("USER");
		usuario.setSTATUS_USUARIO("ATIVO");
		
		return usuarioRepository.save(usuario);
	}
	
	@Transactional
	public void alterarsenha (String email, String novaSenha) {
		Usuario usuario = usuarioRepository.findByEMAIL(email);
		
		String senha = Base64.getEncoder().encodeToString(novaSenha.getBytes());
		usuario.setSENHA(senha);
		usuario.setSTATUS_USUARIO("ATIVO");
		
		usuarioRepository.save(usuario);
	}


	public Usuario findById(long id) {
		return usuarioRepository.findById(id).get();
	}


	public Usuario findByEmail(String email) {
		
		Usuario usuario = usuarioRepository.findByEMAIL(email);
		
		return usuario;
	}


	public List<Usuario> findAll() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		return usuarios;
	}


	public void update(Usuario usuario) {
		usuario.setFOTO(null);
		String senha = Base64.getEncoder().encodeToString("12345678".getBytes());
		usuario.setSENHA(senha);
		usuario.setSTATUS_USUARIO("INATIVO");
		usuarioRepository.save(usuario);
	}
	
	@Transactional
	public void updateSemFoto(Usuario usuario) {

			usuario.setFOTO(null);
			usuario.setSTATUS_USUARIO("Ativo");

		usuarioRepository.save(usuario);
	} 

//	
//	@Transactional
//	public void updateFoto(MultipartFile file, Usuario usuario, byte[] foto) {
//		
//		if (file.getSize() == 0 && foto.length == 0) {
//			usuario.setFOTO(null);
//		} 
//		
//		if (file.getSize() == 0 && foto.length > 0) {
//			usuario.setFOTO(foto);
//		} 
//
//		if (file != null && file.getSize() > 0 ) {
//			try {
//				usuario.setFOTO(file.getBytes());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	
//		usuarioRepository.save(usuario);
//	} 


}
