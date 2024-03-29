package br.com.devederno.carrental.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.devederno.carrental.model.Fabricante;
import br.com.devederno.carrental.service.FabricanteService;

@Controller
@RequestMapping("/fabricantes")
public class FabricanteController {

	private final static String FABRICANTES = "fabricantes";
	private final static String FABRICANTE_CADASTRO = "fabricante-cadastro";
	private final static String FABRICANTE_LISTA = "fabricante-lista";
	private final static String NOVO = "/novo";

	@Autowired
	private FabricanteService fabricanteService;
	
	@RequestMapping(NOVO)
	public ModelAndView novo() {
		ModelAndView modelAndView = new ModelAndView(FABRICANTE_CADASTRO);
		modelAndView.addObject(new Fabricante());
		return modelAndView;
	}

	@PostMapping
	public ModelAndView salvar(@Valid Fabricante fabricante, Errors errors, RedirectAttributes redirectAttributes) {

		if (errors.hasErrors()) {
			return new ModelAndView(FABRICANTE_CADASTRO);
		}

		if (fabricante.getId() == null) {
			fabricanteService.incluir(fabricante);
			redirectAttributes.addFlashAttribute("mensagem", "Inclusão realizada com sucesso!");
		} else {
			fabricanteService.editar(fabricante);
			redirectAttributes.addFlashAttribute("mensagem", "Alteração realizada com sucesso");
		}

		return new ModelAndView("redirect:/"+FABRICANTES + "/" + NOVO);
	}

	@GetMapping
	public ModelAndView lista() {
		ModelAndView modelAndView = new ModelAndView(FABRICANTE_LISTA);
		modelAndView.addObject(FABRICANTES,fabricanteService.listar());
		return modelAndView;
	}

	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView(FABRICANTE_CADASTRO);
		modelAndView
				.addObject(fabricanteService.pesquisarPorId(id));
		return modelAndView;
	}

	@GetMapping("/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("redirect:/" + FABRICANTES);
		fabricanteService.excluir(id);
		return modelAndView;
	}

}
