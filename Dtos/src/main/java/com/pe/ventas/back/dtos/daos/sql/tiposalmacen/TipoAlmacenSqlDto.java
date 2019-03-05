package com.pe.ventas.back.dtos.daos.sql.tiposalmacen;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TipoAlmacenSqlDto implements Serializable {

	private static final long serialVersionUID = -9007392022373684762L;
	
	private Integer tipoAlmacenCodigo;
	private String  tipoAlmacenNombre;
	private String  tipoAlmacenDescripcion;
	private String  tipoAlmacenEstado;
	private Date    tipoAlmacenFecre;
	private Date    tipoAlmacenHocre;
	private Integer tipoAlmacenUscre;
	private Date    tipoAlmacenFemod;
	private Date    tipoAlmacenHomod;
	private String  tipoAlmacenUsmod;
	

}
