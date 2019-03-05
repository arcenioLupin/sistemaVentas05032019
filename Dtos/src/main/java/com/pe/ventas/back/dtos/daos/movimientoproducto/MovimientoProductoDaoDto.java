package com.pe.ventas.back.dtos.daos.movimientoproducto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovimientoProductoDaoDto implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -5370649622175507502L;
	
	private Integer codMovimiento;
	private Integer codProducto;
	private Integer movimientoProductoCant;
	

}
