package com.nisum.api.ejercicio.modelo;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Entity
@Table(name = "phones")
public class Phones implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", updatable = false, nullable = false)
	@ColumnDefault("random_uuid()")
	private UUID id;

	@ApiModelProperty(allowEmptyValue = false, required = true, dataType = "string", example = "4444444444", notes = "---------------")
	private String number;

	@ApiModelProperty(allowEmptyValue = false, required = true, dataType = "string", example = "21", notes = "---------------")
	private String citycode;

	@ApiModelProperty(allowEmptyValue = false, required = true, dataType = "string", example = "5", notes = "---------------")
	private String contrycode;

	@ManyToOne
	@JoinColumn(name = "id_user")
	@JsonIgnoreProperties(value = "user")
	private Users user;

}
