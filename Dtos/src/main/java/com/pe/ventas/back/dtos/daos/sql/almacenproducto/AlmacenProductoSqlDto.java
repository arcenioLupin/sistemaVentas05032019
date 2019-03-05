package com.pe.ventas.back.dtos.daos.sql.almacenproducto;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AlmacenProductoSqlDto implements Serializable {
	
	private static final long serialVersionUID = 6776893953860882316L;
	
	private Integer almacenCodigo;
	private Integer productoCodigo;
	private Integer almacenProductoStock;
	private String unidadMedidaCodigo;
	private double almacenProductoPrecio;
	private String monedaCodigo;
	private String almacenProductoEstado;
	

}
