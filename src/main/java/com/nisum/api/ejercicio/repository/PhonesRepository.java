package com.nisum.api.ejercicio.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nisum.api.ejercicio.modelo.Phones;

public interface PhonesRepository extends JpaRepository<Phones, UUID> {

}
