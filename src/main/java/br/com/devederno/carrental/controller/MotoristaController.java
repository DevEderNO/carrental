package br.com.devederno.carrental.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.devederno.carrental.model.Motorista;
import br.com.devederno.carrental.model.type.Sexo;
import br.com.devederno.carrental.service.MotoristaService;

@Controller
@RequestMapping("/motoristas")
public class MotoristaController {

	private final static String MOTORISTAS = "motoristas";
	private final static String MOTORISTA_CADASTRO = "motorista-cadastro";
	private final static String MOTORISTA_LISTA = "motorista-lista";
	private final static String NOVO = "/novo";

	@Autowired
	private MotoristaService motoristaService;

	@RequestMapping(NOVO)
	public ModelAndView novo() {
		ModelAndView modelAndView = new ModelAndView(MOTORISTA_CADASTRO);
		modelAndView.addObject(new Motorista());
		return modelAndView;
	}
	
	@ModelAttribute(name = "listarTodosSexos")
	public Sexo[] listarTodosSexos() {
		return Sexo.values();
	}

	@PostMapping
	public ModelAndView salvar(@Valid Motorista motorista, Errors errors, RedirectAttributes redirectAttributes) {

		if (errors.hasErrors()) {
			return new ModelAndView(MOTORISTA_CADASTRO);
		}

		if (motorista.getId() == null) {
			motoristaService.incluir(motorista);
			redirectAttributes.addFlashAttribute("mensagem", "Inclusão realizada com sucesso!");
		} else {
			motoristaService.editar(motorista);
			redirectAttributes.addFlashAttribute("mensagem", "Alteração realizada com sucesso");
		}

		return new ModelAndView("redirect:/"+MOTORISTAS + "/" + NOVO);
	}

	@GetMapping
	public ModelAndView lista() {
		ModelAndView modelAndView = new ModelAndView(MOTORISTA_LISTA);
		modelAndView.addObject(MOTORISTAS, motoristaService.listar());
		return modelAndView;
	}

	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView(MOTORISTA_CADASTRO);
		modelAndView.addObject(motoristaService.pesquisarPorId(id));
		return modelAndView;
	}

	@GetMapping("/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("redirect:/" + MOTORISTAS);
		motoristaService.excluir(id);
		return modelAndView;
	}
	
}
