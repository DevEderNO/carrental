package br.com.devederno.carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.devederno.carrental.model.Locacao;

public interface LocacaoRepository extends JpaRepository<Locacao, Long> {

}
