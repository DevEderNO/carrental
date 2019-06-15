package br.com.devederno.carrental.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.devederno.carrental.model.Modelo;
import br.com.devederno.carrental.repository.ModeloRepository;

@Service
public class ModeloService {
	@Autowired
	public ModeloRepository modeloRepository;
	
	public Modelo incluir(Modelo modelo) {
		modelo.setId(null);
		return modeloRepository.save(modelo);
	}
	
	public Modelo editar(Modelo modelo) {
		pesquisarPorId(modelo.getId());
		return modeloRepository.save(modelo);
	}
	
	public void excluir(Long id) {
		modeloRepository.deleteById(id);
	}
	
	public List<Modelo> listar(){
		return modeloRepository.findAll();
	}
	
	public Modelo pesquisarPorId(Long id) {
		return modeloRepository.findById(id).orElseThrow(()-> new EmptyResultDataAccessException(0));
	}
}
