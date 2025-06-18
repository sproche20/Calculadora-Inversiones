package com.israel.tarea2.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tb_inversion")
public class InversionModelo implements Serializable{
	private static final long serialVersionUID = 1L; 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "El capital inicial es obligatorio")
    @DecimalMin(value = "100.01", message = "El capital inicial debe ser mayor a 100")
    private BigDecimal capitalInicial;

    @NotNull(message = "Número de periodos por año es obligatorio")
    @Min(value = 1, message = "Debe haber al menos un periodo")
    @Max(value = 12, message = "Máximo 12 periodos por año")
    private Integer numeroPeriodosPorAnio;

    @NotNull(message = "Tiempo en años es obligatorio")
    @Min(value = 1, message = "Mínimo 1 año")
    private Integer tiempoAnos;

    @NotNull(message = "La tasa de interés es obligatoria")
    @DecimalMin(value = "0.00", inclusive = false)
    private BigDecimal tasaInteres;
	private BigDecimal montoFinal;     // A
	private BigDecimal interesGanado;
	private int categoria;             // 1, 2, o 3
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fkCliente")
	private ClienteModelo fkCliente;

}
