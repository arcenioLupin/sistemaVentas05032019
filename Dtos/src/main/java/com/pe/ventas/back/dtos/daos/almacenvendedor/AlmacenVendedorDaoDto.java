package com.pe.ventas.back.dtos.daos.almacenvendedor;



import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AlmacenVendedorDaoDto implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 4201983356026339955L;
	
	private Integer codAlmacen;
	private Integer codVendedor;
	private String  almacenVendedorEstado;
		

}
