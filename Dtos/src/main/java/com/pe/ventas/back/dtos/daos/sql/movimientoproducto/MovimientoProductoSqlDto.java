package com.pe.ventas.back.dtos.daos.sql.movimientoproducto;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovimientoProductoSqlDto implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 8890526807472273525L;
	private Integer movimientoCodigo;
	private Integer productoCodigo;
	private Integer movimientoProductoCant;

}
