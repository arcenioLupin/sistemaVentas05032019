package com.pe.ventas.back.dtos.daos.ventadetalle;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VentaDetalleDaoDto implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -9197543492829494613L;
	
	private Integer codVenta;
	private Integer posicionVenta;
	private Integer productoCodigo;
	private Integer ventaProductoCant;
	private String  unidadMedidaCodigo;
	private double  ventaProductoPrecio;
	private double  ventaProductoTotal;

}
