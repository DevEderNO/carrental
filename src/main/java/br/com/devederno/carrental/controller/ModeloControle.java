package br.com.devederno.carrental.controller;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.annotations.Proxy;
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

import br.com.devederno.carrental.model.Fabricante;
import br.com.devederno.carrental.model.Modelo;
import br.com.devederno.carrental.model.type.Categoria;
import br.com.devederno.carrental.repository.FabricanteRepository;
import br.com.devederno.carrental.repository.ModeloRepository;

@Controller
@RequestMapping("/modelos")
public class ModeloControle {
	private final static String MODELOS = "modelos";
	private final static String MODELO_CADASTRO = "modelo-cadastro";
	private final static String MODELO_LISTA = "modelo-lista";
	private final static String NOVO = "/novo";

	@Autowired
	private ModeloRepository modeloRepository;
	
	@Autowired
	private FabricanteRepository fabricanteRepository;

	@RequestMapping(NOVO)
	public ModelAndView novo() {
		ModelAndView modelAndView = new ModelAndView(MODELO_CADASTRO);
		modelAndView.addObject(new Modelo());
		return modelAndView;
	}
	
	@ModelAttribute(name = "listarTodasCategorias")
	public Categoria[] listarTodasCategorias() {
		return Categoria.values();
	}
	
	@ModelAttribute(name = "listarTodosFabricantes")
	public List<Fabricante> listarTodosFabricantes(){
		return fabricanteRepository.findAll();
	}

	@PostMapping
	public ModelAndView salvar(@Valid Modelo modelo, Errors errors, RedirectAttributes redirectAttributes) {

		if (errors.hasErrors()) {
			return new ModelAndView(MODELO_CADASTRO);
		}

		if (modelo.getId() == null) {
			modeloRepository.save(modelo);
			redirectAttributes.addFlashAttribute("mensagem", "Inclusão realizada com sucesso!");
		} else {
			modeloRepository.save(modelo);
			redirectAttributes.addFlashAttribute("mensagem", "Alteração realizada com sucesso");
		}

		return new ModelAndView("redirect:/" + MODELOS);
	}

	@GetMapping
	public ModelAndView lista() {
		ModelAndView modelAndView = new ModelAndView(MODELO_LISTA);
		modelAndView.addObject(MODELOS, modeloRepository.findAll());
		return modelAndView;
	}

	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView(MODELO_CADASTRO);
		modelAndView.addObject(modeloRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(0)));
		return modelAndView;
	}

	@GetMapping("/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("redirect:/" + MODELOS);
		modeloRepository.deleteById(id);
		return modelAndView;
	}

}
