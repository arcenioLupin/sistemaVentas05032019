package com.pe.ventas.back.dtos.servicios.ventadetalle;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VentaDetalleServicioDto implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 6583004526692972595L;
	
	private Integer codVenta;
	private Integer posicionVenta;
	private Integer productoCodigo;
	private Integer ventaProductoCant;
	private String  unidadMedidaCodigo;
	private double  ventaProductoPrecio;
	private double  ventaProductoTotal;


}
