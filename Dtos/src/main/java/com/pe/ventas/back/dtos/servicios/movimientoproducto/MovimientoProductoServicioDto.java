package com.pe.ventas.back.dtos.servicios.movimientoproducto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovimientoProductoServicioDto implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 4927588956706398098L;
	
	private Integer codMovimiento;
	private Integer codProducto;
	private Integer movimientoProductoCant;

}
