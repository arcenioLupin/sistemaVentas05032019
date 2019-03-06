package com.pe.ventas.back.dtos.rest.ventadetalle;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VentaDetalleRestDto implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -9091401833837352015L;
	
	private Integer codVenta;
	private Integer posicionVenta;
	private Integer productoCodigo;
	private Integer ventaProductoCant;
	private String  unidadMedidaCodigo;
	private double  ventaProductoPrecio;
	private double  ventaProductoTotal;


}
