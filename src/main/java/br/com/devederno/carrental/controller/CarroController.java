package br.com.devederno.carrental.controller;

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
import br.com.devederno.carrental.model.Modelo;
import br.com.devederno.carrental.service.CarroService;
import br.com.devederno.carrental.service.ModeloService;

@Controller
@RequestMapping("/carros")
public class CarroController {

	private static final String CARROS = "carros";
	private final static String CARRO_CADASTRO = "carro-cadastro";
	private final static String CARRO_LISTA = "carro-lista";
	private final static String NOVO = "/novo";
	
	@Autowired
	private CarroService carroService;
	
	@Autowired
	private ModeloService modeloService;
	
	@RequestMapping(NOVO)
	public ModelAndView novo(){
		ModelAndView modelAndView = new ModelAndView(CARRO_CADASTRO);
		modelAndView.addObject(new Carro());
		return modelAndView;
	}
	
	@ModelAttribute(name = "listarTodosModelos")
	public List<Modelo> listarTodosModelos(){
		return modeloService.listar();
	}
	
	@PostMapping
	public ModelAndView salvar(@Valid Carro carro, Errors errors, RedirectAttributes redirectAttributes){
		
		if(errors.hasErrors()){
			return new ModelAndView(CARRO_CADASTRO);
		}
		
		if(carro.getId() == null){
			carroService.incluir(carro);
			redirectAttributes.addFlashAttribute("mensagem","Inclusão realizada com sucesso!");
		}else{
			carroService.editar(carro);
			redirectAttributes.addFlashAttribute("mensagem","Alteração realizada com sucesso");
		}
		
		return new ModelAndView("redirect:/"+CARROS + "/" + NOVO);
	}
	
	@GetMapping
	public ModelAndView lista(){
		ModelAndView modelAndView = new ModelAndView(CARRO_LISTA);
		modelAndView.addObject(CARROS,carroService.listar());
		return modelAndView;
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id){
		ModelAndView modelAndView = new ModelAndView(CARRO_CADASTRO);
		modelAndView.addObject(carroService.pesquisarPorId(id));
		return modelAndView;
	}
	
	@GetMapping("/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id){
		ModelAndView modelAndView = new ModelAndView("redirect:/"+CARROS);
		carroService.excluir(id);
		return modelAndView;
	}
	
}
