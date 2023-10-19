package br.itb.projeto.olympuss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class PageController {

	@GetMapping("/novotreino")
	public String novotreino() {
		return "novotreino";
	}

	@GetMapping("/login")
	public String login() {
		return "/login";
	}


	@GetMapping("/crud")
	public String crud() {
		return "crud";
	}
	
	@GetMapping("/fqs2")
	public String fqs2() {
		return "fqs2";
	}


	@GetMapping("/planos")
	public String planos() {
		return "planos";
	}



	@GetMapping("/inicio")
	public String tela1() {
		return "inicio";
	}


}
