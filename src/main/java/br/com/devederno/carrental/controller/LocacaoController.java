package br.com.devederno.carrental.controller;

import java.util.List;

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

import br.com.devederno.carrental.model.Carro;
import br.com.devederno.carrental.model.Locacao;
import br.com.devederno.carrental.model.Motorista;
import br.com.devederno.carrental.repository.CarroRepository;
import br.com.devederno.carrental.repository.LocacaoRepository;
import br.com.devederno.carrental.repository.MotoristaRepository;

@Controller
@RequestMapping("/locacoes")
public class LocacaoController {
	
	private final static String LOCACOES = "locacoes";
	private final static String LOCACAO_CADASTRO = "locacao-cadastro";
	private final static String LOCACAO_LISTA = "locacao-lista";
	private final static String NOVO = "/novo";

	@Autowired
	private LocacaoRepository locacaoRepository;
	
	@Autowired
	private MotoristaRepository motoristaRepository;
	
	@Autowired
	private CarroRepository carroRepository ;

	@RequestMapping(NOVO)
	public ModelAndView novo() {
		ModelAndView modelAndView = new ModelAndView(LOCACAO_CADASTRO);
		modelAndView.addObject(new Locacao());
		return modelAndView;
	}
	
	@ModelAttribute(name = "listarTodosMotoristas")
	public List<Motorista> listarTodosMotoristas(){
		return motoristaRepository.findAll();
	}
	
	@ModelAttribute(name = "listarTodosCarros")
	public List<Carro> listarTodosCarros(){
		return carroRepository.findAll();
	}

	@PostMapping
	public ModelAndView salvar(@Valid Locacao locacao, Errors errors, RedirectAttributes redirectAttributes) {

		if (errors.hasErrors()) {
			return new ModelAndView(LOCACAO_CADASTRO);
		}

		if (locacao.getId() == null) {
			locacaoRepository.save(locacao);
			redirectAttributes.addFlashAttribute("mensagem", "Inclusão realizada com sucesso!");
		} else {
			locacaoRepository.save(locacao);
			redirectAttributes.addFlashAttribute("mensagem", "Alteração realizada com sucesso");
		}

		return new ModelAndView("redirect:/"+LOCACOES);
	}

	@GetMapping
	public ModelAndView lista() {
		ModelAndView modelAndView = new ModelAndView(LOCACAO_LISTA);
		modelAndView.addObject(LOCACOES, locacaoRepository.findAll());
		return modelAndView;
	}

	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView(LOCACAO_CADASTRO);
		modelAndView.addObject(locacaoRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(0)));
		return modelAndView;
	}

	@GetMapping("/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("redirect:/" + LOCACOES);
		locacaoRepository.deleteById(id);
		return modelAndView;
	}
	
	
}
