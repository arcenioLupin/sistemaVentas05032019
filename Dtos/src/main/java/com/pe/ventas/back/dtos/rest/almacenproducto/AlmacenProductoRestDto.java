package com.pe.ventas.back.dtos.rest.almacenproducto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AlmacenProductoRestDto implements Serializable {
	
	private static final long serialVersionUID = 1400848659258447937L;
	
	private Integer codAlmacen;
	private Integer codProducto;
	private Integer almacenProductoStock;
	private String unidadMedidaCodigo;
	private double almacenProductoPrecio;
	private String monedaCodigo;
	private String almacenProductoEstado;

}
