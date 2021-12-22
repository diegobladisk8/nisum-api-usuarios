package com.nisum.api.ejercicio.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nisum.api.ejercicio.modelo.Users;
import com.nisum.api.ejercicio.repository.PhonesRepository;
import com.nisum.api.ejercicio.repository.UsersRepository;
import com.nisum.api.ejercicio.util.CommonUtil;
import com.nisum.api.ejercicio.util.OperationResponse;

@Service
public class UserService {

	@Value("${api.expression.pwd}")
	private String expressionPassword;

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private PhonesRepository phonesRepository;

	public List<Users> findAll() {
		return usersRepository.findAll();
	}

	public OperationResponse save(Users users) throws Exception {

		if (users.getEmail() == null || users.getEmail().trim().length() == 0) {
			return new OperationResponse("Email no puede ser nulo o vacio");
		} else if (users.getId() == null && usersRepository.findByEmail(users.getEmail()).size() > 0) {
			return new OperationResponse("El correo ya registrado: " + users.getEmail());
		} else if (!CommonUtil.esValido(users.getEmail())) {
			return new OperationResponse("Formato de correo no valido: " + users.getEmail());
		}

		if (!users.getPassword().matches(expressionPassword)) {
			return new OperationResponse(
					"Password no cumple politica de 1 numero 1 letra mayuscula 1 minuscula y 1 caracter especial y minimo 8 caracteres");
		}

		if (users.getId() == null) {
			users.setCreated(new Date());
			users.setLastLogin(new Date());
		} else {
			users.setCreated(usersRepository.getById(users.getId()).getCreated());
			users.setLastLogin(users.getCreated());
			users.setModified(new Date());
		}
		users.setIsactive(true);
		users.setToken(CommonUtil.getJWTToken(users.getEmail()));
		usersRepository.save(users);
		if (users.getPhones() != null) {
			users.getPhones().forEach(phone -> {
				phone.setUser(users);
				phonesRepository.save(phone);
			});
		}
		return new OperationResponse("Datos guardados con exito...");
	}

	public Users getOne(String uuid) throws Exception {
		return usersRepository.findById(UUID.fromString(uuid)).orElseThrow(() -> new Exception("No existe usuario"));
	}

	public void deleteUsers(String uuid) throws Exception {
		usersRepository.delete(usersRepository.getById(UUID.fromString(uuid)));
	}

}
