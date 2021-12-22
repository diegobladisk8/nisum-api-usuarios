package com.nisum.api.ejercicio.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
@ApiModel
public class Users implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", updatable = false, nullable = false)
	@ColumnDefault("random_uuid()")
	@ApiModelProperty(hidden = true)
	private UUID id;

	@ApiModelProperty(allowEmptyValue = false, required = true, dataType = "string", example = "diego@email.com", notes = "---------------")
	private String email;

	@ApiModelProperty(allowEmptyValue = false, required = true, dataType = "string", example = "diego cumbicus", notes = "---------------")
	private String name;

	@ApiModelProperty(allowEmptyValue = false, required = true, dataType = "string", notes = "---------------")
	private String password;

	@ApiModelProperty(hidden = true)
	private Date created;

	@ApiModelProperty(hidden = true)
	private Date modified;

	@ApiModelProperty(hidden = true)
	private String token;

	@ApiModelProperty(hidden = true)
	@Column(name = "last_login")
	private Date lastLogin;

	@ApiModelProperty(hidden = true)
	private Boolean isactive;

	@JsonIgnoreProperties(value = "user")
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<Phones> phones;

}
