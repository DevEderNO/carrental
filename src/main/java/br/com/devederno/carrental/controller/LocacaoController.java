package br.com.devederno.carrental.controller;

import java.time.LocalDate;
import java.util.List;

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

import br.com.devederno.carrental.model.Carro;
import br.com.devederno.carrental.model.Locacao;
import br.com.devederno.carrental.model.Motorista;
import br.com.devederno.carrental.service.CarroService;
import br.com.devederno.carrental.service.LocacaoService;
import br.com.devederno.carrental.service.MotoristaService;

@Controller
@RequestMapping("/locacoes")
public class LocacaoController {
	
	private final static String LOCACOES = "locacoes";
	private final static String LOCACAO_CADASTRO = "locacao-cadastro";
	private final static String LOCACAO_LISTA = "locacao-lista";
	private final static String NOVO = "/novo";

	@Autowired
	private LocacaoService locacaoService;
	
	@Autowired
	private MotoristaService motoristaService;
	
	@Autowired
	private CarroService carroService ;

	@RequestMapping(NOVO)
	public ModelAndView novo() {
		ModelAndView modelAndView = new ModelAndView(LOCACAO_CADASTRO);
		Locacao locacao = new Locacao();
		locacao.setDataDeLocacao(LocalDate.now());
		locacao.setDataDeDevolucao(LocalDate.now());
		modelAndView.addObject(locacao);
		return modelAndView;
	}
	
	@ModelAttribute(name = "listarTodosMotoristas")
	public List<Motorista> listarTodosMotoristas(){
		return motoristaService.listar();
	}
	
	@ModelAttribute(name = "listarTodosCarros")
	public List<Carro> listarTodosCarros(){
		return carroService.listar();
	}

	@PostMapping
	public ModelAndView salvar(@Valid Locacao locacao, Errors errors, RedirectAttributes redirectAttributes) {

		if (errors.hasErrors()) {
			return new ModelAndView(LOCACAO_CADASTRO);
		}

		if (locacao.getId() == null) {
			locacaoService.incluir(locacao);
			redirectAttributes.addFlashAttribute("mensagem", "Inclusão realizada com sucesso!");
		} else {
			locacaoService.editar(locacao);
			redirectAttributes.addFlashAttribute("mensagem", "Alteração realizada com sucesso");
		}

		return new ModelAndView("redirect:/"+LOCACOES + "/" + NOVO);
	}

	@GetMapping
	public ModelAndView lista() {
		ModelAndView modelAndView = new ModelAndView(LOCACAO_LISTA);
		modelAndView.addObject(LOCACOES, locacaoService.listar());
		return modelAndView;
	}

	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView(LOCACAO_CADASTRO);
		modelAndView.addObject(locacaoService.pesquisarPorId(id));
		return modelAndView;
	}

	@GetMapping("/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("redirect:/" + LOCACOES);
		locacaoService.excluir(id);
		return modelAndView;
	}
	
	
}
