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
import br.com.devederno.carrental.model.Modelo;
import br.com.devederno.carrental.repository.CarroRepository;
import br.com.devederno.carrental.repository.ModeloRepository;

@Controller
@RequestMapping("/carros")
public class CarroController {

	private static final String CARROS = "carros";
	private final static String CARRO_CADASTRO = "carro-cadastro";
	private final static String CARRO_LISTA = "carro-lista";
	private final static String NOVO = "/novo";
	
	@Autowired
	private CarroRepository carroRepository;
	
	@Autowired
	private ModeloRepository modeloRepository;
	
	@RequestMapping(NOVO)
	public ModelAndView novo(){
		ModelAndView modelAndView = new ModelAndView(CARRO_CADASTRO);
		modelAndView.addObject(new Carro());
		return modelAndView;
	}
	
	@ModelAttribute(name = "listarTodosModelos")
	public List<Modelo> listarTodosModelos(){
		return modeloRepository.findAll();
	}
	
	@PostMapping
	public ModelAndView salvar(@Valid Carro carro, Errors errors, RedirectAttributes redirectAttributes){
		
		if(errors.hasErrors()){
			return new ModelAndView(CARRO_CADASTRO);
		}
		
		if(carro.getId() == null){
			carroRepository.save(carro);
			redirectAttributes.addFlashAttribute("mensagem","Inclusão realizada com sucesso!");
		}else{
			carroRepository.save(carro);
			redirectAttributes.addFlashAttribute("mensagem","Alteração realizada com sucesso");
		}
		
		return new ModelAndView("redirect:/"+CARROS + "/" + NOVO);
	}
	
	@GetMapping
	public ModelAndView lista(){
		ModelAndView modelAndView = new ModelAndView(CARRO_LISTA);
		modelAndView.addObject(CARROS,carroRepository.findAll());
		return modelAndView;
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id){
		ModelAndView modelAndView = new ModelAndView(CARRO_CADASTRO);
		modelAndView.addObject(carroRepository.findById(id).orElseThrow(()-> new EmptyResultDataAccessException(0)));
		return modelAndView;
	}
	
	@GetMapping("/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id){
		ModelAndView modelAndView = new ModelAndView("redirect:/"+CARROS);
		carroRepository.deleteById(id);
		return modelAndView;
	}
	
}
