package com.pe.ventas.back.dtos.daos.sql.movimientos;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovimientoSqlDto implements Serializable {
	
	private static final long serialVersionUID = 5563460802567668525L;
	
	private Integer movimientoCodigo;
	private Date    movimientoFecha;
	private Integer almacenCodOrigen;
	private Integer almacenCodDestino;
	private String  movimientoComentario;
	private Date    movimientoFecre;
	private Date    movimientoHocre;
	private String  movimientoUscre;
	private Date    movimientoFemod;
	private Date    movimientoHomod;
	private String  movimientoUsmod;

}
