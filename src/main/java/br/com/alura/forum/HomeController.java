package br.com.alura.forum;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	@RequestMapping("/")
	@ResponseBody
	public String index() {
		System.out.println("Log do servidor que foi feita uma requisicao para '/'");
		return "Bem vindo ao forum da alura";
	}
}
