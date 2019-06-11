package br.com.devederno.carrental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.devederno.carrental.CarrentalApplication;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String index(CarrentalApplication application) {
		return "/index";
	}
	
}
