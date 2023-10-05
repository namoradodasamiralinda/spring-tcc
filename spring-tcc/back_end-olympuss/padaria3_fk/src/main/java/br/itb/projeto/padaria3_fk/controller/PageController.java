package br.itb.projeto.padaria3_fk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class PageController {

	@GetMapping("/index")
	public String index() {
		return "/index";
	}

	@GetMapping("/login")
	public String login() {
		return "/login";
	}


	@GetMapping("/fqs")
	public String fqs() {
		return "/fqs";
	}
	
	@GetMapping("/fqs2")
	public String fqs2() {
		return "/fqs2";
	}

	@GetMapping("/logne2")
	public String logne2() {
		return "/logne2";
	}

	@GetMapping("/adc")
	public String adc() {
		return "/adc";
	}

	@GetMapping("/plano")
	public String plano() {
		return "/plano";
	}

	@GetMapping("/tela1")
	public String tela1() {
		return "/tela1";
	}

	@GetMapping("/usuario")
	public String usuario() {
		return "/usuario";
	}
}
