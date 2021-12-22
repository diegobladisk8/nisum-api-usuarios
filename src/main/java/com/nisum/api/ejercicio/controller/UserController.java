package com.nisum.api.ejercicio.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nisum.api.ejercicio.dto.UserDTO;
import com.nisum.api.ejercicio.modelo.Phones;
import com.nisum.api.ejercicio.modelo.Users;
import com.nisum.api.ejercicio.service.UserService;
import com.nisum.api.ejercicio.util.OperationResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(tags = "Comprobantes Api Rest")
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/all")
	@ApiOperation(value = "Servicio listado de usuarios ", response = Users.class)
	public ModelAndView findAll(Model model) {
		model.addAttribute("users", userService.findAll());
		return new ModelAndView("allUsers");
	}

	@PostMapping("/save")
	@ApiOperation(value = "Servicio permite la creacion o actualizacion ", response = OperationResponse.class)
	public ModelAndView save(@ModelAttribute UserDTO form, Model model) {
		try {
			OperationResponse or = userService.save(form.getUsers().get(0));
			UserDTO modl = (UserDTO) model.getAttribute("userDTO");

			if (or.getMensaje() != null && or.getMensaje().length() > 0 && !or.getMensaje().contains("Datos")) {
				modl.setMensajeError(or.getMensaje());
				model.addAttribute("form", modl);

			} else {
				modl.setMensaje(or.getMensaje());
				model.addAttribute("form", modl);
			}
			return new ModelAndView("createUsersForm");
		} catch (Exception e) {
			return new ModelAndView("createUsersForm");
		}

	}

	@GetMapping(value = "/create")
	@ApiOperation(value = "Servicio permite la creacion y actualizacion de un usuario ", response = Users.class)
	public ModelAndView crear(Model model) {

		UserDTO userDTO = new UserDTO();
		Users usr = new Users();
		usr.setPhones(new ArrayList<Phones>());
		for (int i = 0; i < 2; i++) {
			usr.getPhones().add(new Phones());
		}

		userDTO.addUser(usr);
		userDTO.setMensajeError("");
		model.addAttribute("form", userDTO);
		return new ModelAndView("createUsersForm");

	}

	@GetMapping(value = "/edit/{paramid}")
	@ApiOperation(value = "Servicio permite editar un usuario ", response = Users.class)
	public ModelAndView edit(@ApiParam("Parametro de entrada ID") @PathVariable("paramid") String paramid,
			Model model) throws Exception {
		UserDTO userDTO = new UserDTO();
		Users usr = userService.getOne(paramid);
		userDTO.addUser(usr);
		userDTO.setMensajeError("");
		model.addAttribute("form", userDTO);
		return new ModelAndView("createUsersForm");

	}

}
