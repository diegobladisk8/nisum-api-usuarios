package com.nisum.api.ejercicio.dto;

import java.util.ArrayList;
import java.util.List;

import com.nisum.api.ejercicio.modelo.Users;

public class UserDTO {

	private List<Users> users;

	private String mensajeError;

	private String mensaje;

	public UserDTO() {
		this.users = new ArrayList<Users>();
	}

	public void addUser(Users book) {
		this.users.add(book);
	}

	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}

	public String getMensajeError() {
		if (mensajeError == null) {
			mensajeError = "";
		}
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
