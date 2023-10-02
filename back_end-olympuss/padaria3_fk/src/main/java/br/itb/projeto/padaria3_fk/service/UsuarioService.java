package br.itb.projeto.padaria3_fk.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.itb.projeto.padaria3_fk.model.entity.Usuario;
import br.itb.projeto.padaria3_fk.model.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	private UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	
	@Transactional
	public Usuario acessar(String email, String senha) {
		
		Usuario usuario = usuarioRepository.findByEmail(email);
		
		String _senha = Base64.getEncoder().encodeToString(senha.getBytes());
		
		if (usuario != null && usuario.getStatusUsuario().equals("ATIVO")) {
			
			if (usuario.getSenha().equals(_senha)) {
				
				return usuario;
			
			}
		} 
		return null;	
	}

	@Transactional
	public Usuario save(Usuario usuario) {

		String senha = Base64.getEncoder().encodeToString(usuario.getSenha().getBytes());
		usuario.setSenha(senha);
		usuario.setFoto(null);
		usuario.setNivelAcesso("USER");
		usuario.setStatusUsuario("ATIVO");
		
		return usuarioRepository.save(usuario);
	}
	
	@Transactional
	public Usuario saveNewUser(Usuario usuario) {

		usuario.setFoto(null);
		String senha = Base64.getEncoder().encodeToString("12345678".getBytes());
		usuario.setSenha(senha);
		usuario.setStatusUsuario("ATIVO");
		
		return usuarioRepository.save(usuario);
	}
	
	@Transactional
	public void alterarsenha (String email, String novaSenha) {
		Usuario usuario = usuarioRepository.findByEmail(email);
		
		String senha = Base64.getEncoder().encodeToString(novaSenha.getBytes());
		usuario.setSenha(senha);
		usuario.setStatusUsuario("ATIVO");
		
		usuarioRepository.save(usuario);
	}


	public Usuario findById(long id) {
		return usuarioRepository.findById(id).get();
	}


	public Usuario findByEmail(String email) {
		
		Usuario usuario = usuarioRepository.findByEmail(email);
		
		return usuario;
	}


	public List<Usuario> findAll() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		return usuarios;
	}


	public void update(Usuario usuario) {
		usuario.setFoto(null);
		String senha = Base64.getEncoder().encodeToString("12345678".getBytes());
		usuario.setSenha(senha);
		usuario.setStatusUsuario("INATIVO");
		usuarioRepository.save(usuario);
	}

	
	@Transactional
	public void update(MultipartFile file, Usuario usuario, byte[] foto) {
		
		if (file.getSize() == 0 && foto.length == 0) {
			usuario.setFoto(null);
		} 
		
		if (file.getSize() == 0 && foto.length > 0) {
			usuario.setFoto(foto);
		} 

		if (file != null && file.getSize() > 0 ) {
			try {
				usuario.setFoto(file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
		usuarioRepository.save(usuario);
	} 


}
