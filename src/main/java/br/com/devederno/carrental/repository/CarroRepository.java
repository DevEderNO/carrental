package br.com.devederno.carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.devederno.carrental.model.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long> {

}
