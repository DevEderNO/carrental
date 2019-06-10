package br.com.devederno.carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.devederno.carrental.model.Modelo;

public interface ModeloRepository extends JpaRepository<Modelo, Long> {

}
