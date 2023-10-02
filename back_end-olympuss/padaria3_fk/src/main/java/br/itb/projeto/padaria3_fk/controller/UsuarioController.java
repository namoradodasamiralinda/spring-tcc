package br.itb.projeto.padaria3_fk.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import br.itb.projeto.padaria3_fk.model.entity.Usuario;
import br.itb.projeto.padaria3_fk.service.UsuarioService;

@Controller
@RequestMapping("/api/usuario")
public class UsuarioController {

	private UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	private String serverMessage = null;
	private String foto = "";
	// CASO O PRODUTO NÃO TENHA UMA IMAGEM CADASTRADA NO BANCO DE DADOS
	private String noImage = "/images/perfil.png";

	@GetMapping("/login")
	public String getLogin(ModelMap model, HttpSession session) {
		
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("serverMessage", serverMessage);
		serverMessage = "";
		return "login";
	}
	
	@GetMapping("/novo-login")
	public String getNovoLogin(ModelMap model) {

		model.addAttribute("usuario", new Usuario());
		model.addAttribute("serverMessage", serverMessage);
		serverMessage = "";
		return "novo-login";
	}
	
	@GetMapping("/usuario-novo")
	public String getNovoUsuario(ModelMap model) {

		model.addAttribute("usuario", new Usuario());
		model.addAttribute("serverMessage", serverMessage);

		serverMessage = null;
		
		return "usuario-novo";
	}
	
	
	@GetMapping("/voltar")
	public String getHome(ModelMap model, HttpSession session) {
		//System.out.println(session.getAttribute("usuarioLogado"));
		serverMessage = null;
		return "redirect:/api/usuario/admin";
	}
	
	@PostMapping("/acessar")
	public String acessar(ModelMap model,
			@RequestParam("email") String email, 
			@RequestParam("senha") String senha, HttpSession session) {
		
		Usuario usuarioLogado = usuarioService.acessar(email, senha);

		if (usuarioLogado != null) {
			
			session.setAttribute("usuarioLogado", usuarioLogado);
			model.addAttribute("usuario", usuarioLogado);
			
			if (usuarioLogado.getNivelAcesso().equals("ADMIN")) {
				
				return "redirect:/api/usuario/admin";
				
			} else if (usuarioLogado.getNivelAcesso().equals("USER")) {
				
				return "redirect:/api/usuario/user";
				
			}
	
		}
		
		serverMessage = "Dados Incorretos!";
		model.addAttribute("serverMessage", serverMessage);
		
		return "redirect:/api/usuario/login";
	}
	
	@GetMapping("/admin")
	public String goAdmin(ModelMap model, HttpSession session) {

		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		model.addAttribute("usuario", session.getAttribute("usuarioLogado"));
			
		if (usuario != null && usuario.getFoto() != null) {
			if (usuario.getFoto().length > 0) {
				foto = Base64.getEncoder().encodeToString(usuario.getFoto());
			}
		}

		model.addAttribute("noImage", noImage);
		model.addAttribute("serverMessage", serverMessage);

		return "admin";
	}
	
	@GetMapping("/user")
	public String goUser(ModelMap model, HttpSession session) {

		model.addAttribute("usuario", session.getAttribute("usuarioLogado"));
		model.addAttribute("noImage", noImage);
		model.addAttribute("serverMessage", serverMessage);

		return "user";
	}

	@GetMapping("/logoff")
	public String sair(ModelMap model, HttpSession session) {
		
		session.removeAttribute("usuarioLogado");
		
		serverMessage = "Usuário desconectado!";
		model.addAttribute("serverMessage", serverMessage);
		serverMessage = "";
		return "redirect:/api/index";
	}
	
	@PostMapping("/logoff")
	public String logoff(ModelMap model, HttpSession session) {

		session.removeAttribute("usuarioLogado");
		serverMessage = "";
		return "redirect:/api/index";
	}
	
	@PostMapping("/salvar")
	public String salvar(ModelMap model,
			@ModelAttribute("usuario") Usuario usuario) {
		
		Usuario _usuario = usuarioService.findByEmail(usuario.getEmail());
		
		if (_usuario == null) {
			
			usuarioService.save(usuario);
			model.addAttribute("usuario", new Usuario());
			serverMessage = "Usuário cadastrado com sucesso!!!";
			
		} else if (_usuario != null) {
			
			model.addAttribute("usuario", new Usuario());
			serverMessage = "Usuário já foi cadastrado no sitema!";	
			
		}
		
		if (usuario.getNome().equals("") || usuario.getEmail().equals("") || usuario.getSenha().equals("")) {
			
			serverMessage = "Dados Incompletos!!!";	
			
		} 
	
		return "redirect:/api/usuario/novo-login";
	}
	
	@PostMapping("/gravar")
	public String gravar(ModelMap model,
			@ModelAttribute("usuario") Usuario usuario) {
		
		Usuario _usuario = usuarioService.findByEmail(usuario.getEmail());
		
		if (_usuario == null) {
			
			usuarioService.saveNewUser(usuario);
			model.addAttribute("usuario", new Usuario());
			serverMessage = "Usuário cadastrado com sucesso!!!";
			
		} else if (_usuario != null) {
			
			model.addAttribute("usuario", new Usuario());
			serverMessage = "Usuário já foi cadastrado no sitema!";	
			
		}
		
		if (usuario.getNome().equals("") || usuario.getEmail().equals("")) {
			
			serverMessage = "Dados Incompletos!!!";	
			
		} 
	
		return "redirect:/api/usuario/usuario-novo";
	}
	
	@PostMapping("/inativar/{id}")
	public String inativar (
			@PathVariable("id") long id, @ModelAttribute("usuario") Usuario usuario) {
	
		usuarioService.update(usuario);
		
		serverMessage = "Usuário inativado com sucesso!!!";
		foto = "";
		return "redirect:/api/usuario/ver/" + id;
	}
	
	@PostMapping("/alterarsenha")
	public String alterarsenha(
			@ModelAttribute("usuario") Usuario usuario, 
			@RequestParam("senha") String senha) {
		
		if (!senha.equals("")) {
			usuarioService.alterarsenha(usuario.getEmail(), senha);
			serverMessage = "Acesse o sistema com a nova senha.";
			return "redirect:/api/usuario/login";
		}
		serverMessage = "";
		serverMessage = "Informe a nova senha.";
		return "redirect:/api/usuario/novaSenha/"+usuario.getId();
	}
	
	
	@GetMapping("/ver/{id}")
	public String verUsuario(@PathVariable("id") long id, ModelMap model) {

		Usuario usuario = usuarioService.findById(id);

		model.addAttribute("usuario", usuario);
		model.addAttribute("noImage", noImage);
		model.addAttribute("serverMessage", serverMessage);
		serverMessage = "";
		return "usuario-editar";
	}
	
	@PostMapping("/editar/{id}")
	public String editar(
			@RequestParam(value = "file", required = false) MultipartFile file,
			@PathVariable("id") long id, @ModelAttribute("usuario") Usuario usuario) {

		byte[] _foto = Base64.getDecoder().decode(foto);
			
		usuarioService.update(file, usuario, _foto);
		
		serverMessage = "Dados alterados com sucesso!!!";
		foto = "";
		return "redirect:/api/usuario/verPerfil/" + id;
	}
	
	
	@GetMapping("/verPerfil/{id}")
	public String verUsuarioPerfil(@PathVariable("id") long id, ModelMap model) {

		Usuario usuario = usuarioService.findById(id);
		
		if (usuario.getFoto() != null) {
			if (usuario.getFoto().length > 0) {
				foto = Base64.getEncoder().encodeToString(usuario.getFoto());
			}
		}

		model.addAttribute("usuario", usuario);
		model.addAttribute("noImage", noImage);
		model.addAttribute("serverMessage", serverMessage);
		
		return "usuario-editarPerfil";
	}
	
	@GetMapping("/novaSenha/{id}")
	public String novaSenha(@PathVariable("id") long id, ModelMap model) {

		Usuario usuario = usuarioService.findById(id);

		model.addAttribute("usuario", usuario);
		model.addAttribute("serverMessage", serverMessage);

		return "usuario-novaSenha";
	}
	
	@GetMapping("/todos")
	public String verTodosUsuarios(ModelMap model) {

		List<Usuario> usuarios = usuarioService.findAll();

		model.addAttribute("usuarios", usuarios);
		model.addAttribute("serverMessage", serverMessage);

		return "usuarios";
	}
	
	@GetMapping("/showImage/{id}")
	@ResponseBody
	public void showImage(
			@PathVariable("id") long id, HttpServletResponse response, Usuario usuario)
			throws ServletException, IOException {

		usuario = usuarioService.findById(id);

		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		if (usuario.getFoto() != null) {
			response.getOutputStream().write(usuario.getFoto());
		} else {
			response.getOutputStream().write(null);
		}

		response.getOutputStream().close();
	}
	

}
