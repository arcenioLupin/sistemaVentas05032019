package com.pe.ventas.back.dtos.daos.sql.almacenvendedor;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AlmacenVendedorSqlDto implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 900432191177769679L;
	private Integer almacenCodigo;
	private Integer vendedorCodigo;
	private String  almacenVendedorEstado;

}
