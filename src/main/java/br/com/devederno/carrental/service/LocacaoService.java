package br.com.devederno.carrental.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.devederno.carrental.model.Locacao;
import br.com.devederno.carrental.repository.LocacaoRepository;

@Service
public class LocacaoService {
	
	@Autowired
	public LocacaoRepository locacaoRepository;
	
	public Locacao incluir(Locacao locacao) {
		locacao.setId(null);
		return locacaoRepository.save(locacao);
	}
	
	public Locacao editar(Locacao locacao) {
		pesquisarPorId(locacao.getId());
		return locacaoRepository.save(locacao);
	}
	
	public void excluir(Long id) {
		locacaoRepository.deleteById(id);
	}
	
	public List<Locacao> listar(){
		return locacaoRepository.findAll();
	}
	
	public Locacao pesquisarPorId(Long id) {
		return locacaoRepository.findById(id).orElseThrow(()-> new EmptyResultDataAccessException(0));
	}
}
