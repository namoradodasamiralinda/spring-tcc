package br.itb.projeto.olympuss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageController {

	@GetMapping("index")
	public String index() {
		return "/index";
	}

	@GetMapping("/login")
	public String login() {
		return "/login";
	}


	@GetMapping("fqs")
	public String fqs() {
		return "fqs";
	}
	
	@GetMapping("fqs2")
	public String fqs2() {
		return "fqs2";
	}


	@GetMapping("cdplano")
	public String adc() {
		return "cdplano";
	}

	@GetMapping("cdusuario")
	public String plano() {
		return "cdusuario";
	}

	@GetMapping("inicio")
	public String tela1() {
		return "inicio";
	}

	@GetMapping("usuario")
	public String usuario() {
		return "usuario";
	}
}
