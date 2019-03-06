package com.pe.ventas.back.dtos.daos.sql.ventadetalle;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VentaDetalleSqlDto implements Serializable {
	
	private static final long serialVersionUID = -5814683468719397994L;
	
	private Integer ventaCodigo;
	private Integer ventaPosicion;
	private Integer productoCodigo;
	private Integer ventaProductoCant;
	private String  unidadMedidaCodigo;
	private double  ventaProductoPrecio;
	private double  ventaProductoTotal;

}
