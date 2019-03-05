package com.pe.ventas.back.dtos.servicios.almacenvendedor;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AlmacenVendedorServicioDto implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 188153045423872052L;
	
	private Integer codAlmacen;
	private Integer codVendedor;
	private String  almacenVendedorEstado;

}
