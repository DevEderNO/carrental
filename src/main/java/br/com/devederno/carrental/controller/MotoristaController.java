package br.com.devederno.carrental.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
import br.com.devederno.carrental.repository.MotoristaRepository;

@Controller
@RequestMapping(name = "/motoristas")
public class MotoristaController {

	private final static String MOTORISTAS = "motoristas";
	private final static String MOTORISTA_CADASTRO = "motorista-cadastro";
	private final static String MOTORISTA_LISTA = "motorista-lista";
	private final static String NOVO = "/novo";

	@Autowired
	private MotoristaRepository motoristaRepository;

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
			motoristaRepository.save(motorista);
			redirectAttributes.addFlashAttribute("mensagem", "Inclusão realizada com sucesso!");
		} else {
			motoristaRepository.save(motorista);
			redirectAttributes.addFlashAttribute("mensagem", "Alteração realizada com sucesso");
		}

		return new ModelAndView("redirect:/"+MOTORISTAS);
	}

	@GetMapping
	public ModelAndView lista() {
		ModelAndView modelAndView = new ModelAndView(MOTORISTA_LISTA);
		modelAndView.addObject(MOTORISTAS, motoristaRepository.findAll());
		return modelAndView;
	}

	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView(MOTORISTA_CADASTRO);
		modelAndView.addObject(motoristaRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(0)));
		return modelAndView;
	}

	@GetMapping("/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("redirect:/" + MOTORISTAS);
		motoristaRepository.deleteById(id);
		return modelAndView;
	}
	
}
