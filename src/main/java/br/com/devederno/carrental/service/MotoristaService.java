package br.com.devederno.carrental.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.devederno.carrental.model.Motorista;
import br.com.devederno.carrental.repository.MotoristaRepository;

@Service
public class MotoristaService {
	@Autowired
	public MotoristaRepository motoristaRepository;
	
	public Motorista incluir(Motorista motorista) {
		motorista.setId(null);
		return motoristaRepository.save(motorista);
	}
	
	public Motorista editar(Motorista motorista) {
		pesquisarPorId(motorista.getId());
		return motoristaRepository.save(motorista);
	}
	
	public void excluir(Long id) {
		motoristaRepository.deleteById(id);
	}
	
	public List<Motorista> listar(){
		return motoristaRepository.findAll();
	}
	
	public Motorista pesquisarPorId(Long id) {
		return motoristaRepository.findById(id).orElseThrow(()-> new EmptyResultDataAccessException(0));
	}
}
