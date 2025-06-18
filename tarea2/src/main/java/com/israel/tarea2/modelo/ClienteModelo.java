package com.israel.tarea2.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tb_Cliente")
public class ClienteModelo implements Serializable{
		private static final long serialVersionUID = 1L; 
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

	    @NotBlank(message = "El nombre es obligatorio")
	    private String nombres;

	    @NotBlank(message = "La cédula es obligatoria")
	    private String cedula;

	    @NotBlank(message = "El email es obligatorio")
	    @Email(message = "Formato de email inválido")
	    private String email;

	    @NotBlank(message = "El celular es obligatorio")
	    @Pattern(regexp = "^\\d{10}$", message = "El celular debe tener 10 dígitos")
	    private String celular;
	    @OneToMany(mappedBy = "fkCliente")
	    private List<InversionModelo> listarinversion = new ArrayList<>();
}
