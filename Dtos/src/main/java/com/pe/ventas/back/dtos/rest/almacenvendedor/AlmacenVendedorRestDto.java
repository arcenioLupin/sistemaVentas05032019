package com.pe.ventas.back.dtos.rest.almacenvendedor;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AlmacenVendedorRestDto implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -4513050624364648385L;
	private Integer codAlmacen;
	private Integer codVendedor;
	private String  almacenVendedorEstado;

}
