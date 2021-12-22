package com.nisum.api.ejercicio.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nisum.api.ejercicio.modelo.Users;

public interface UsersRepository extends JpaRepository<Users, UUID> {

	List<Users> findByEmail(String email);

}
