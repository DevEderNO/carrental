package br.com.devederno.carrental.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.devederno.carrental.model.Fabricante;
import br.com.devederno.carrental.repository.FabricanteRepository;

@Service
public class FabricanteService {

	@Autowired
	public FabricanteRepository fabricanteRepository;
	
	public Fabricante incluir(Fabricante fabricante) {
		fabricante.setId(null);
		return fabricanteRepository.save(fabricante);
	}
	
	public Fabricante editar(Fabricante fabricante) {
		pesquisarPorId(fabricante.getId());
		return fabricanteRepository.save(fabricante);
	}
	
	public void excluir(Long id) {
		fabricanteRepository.deleteById(id);
	}
	
	public List<Fabricante> listar(){
		return fabricanteRepository.findAll();
	}
	
	public Fabricante pesquisarPorId(Long id) {
		return fabricanteRepository.findById(id).orElseThrow(()-> new EmptyResultDataAccessException(0));
	}
}
