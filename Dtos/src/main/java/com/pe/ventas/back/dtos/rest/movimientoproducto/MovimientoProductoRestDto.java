package com.pe.ventas.back.dtos.rest.movimientoproducto;



import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovimientoProductoRestDto implements Serializable  {/**
	 * 
	 */
	private static final long serialVersionUID = -5768341465869491712L;
	
	private Integer codMovimiento;
	private Integer codProducto;
	private Integer movimientoProductoCant;

}
