package br.com.devederno.carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.devederno.carrental.model.Motorista;

public interface MotoristaRepository extends JpaRepository<Motorista, Long> {

}
