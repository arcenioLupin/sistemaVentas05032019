package com.pe.ventas.back.dtos.daos.sql.almacenes;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AlmacenSqlDto implements Serializable {
	
	private static final long serialVersionUID = -390992487258277185L;
	
	private Integer almacenCodigo;
	private String  almacenNombre;
	private Integer tipoAlmacenCodigo;
	private String  almacenDireccion;
	private String  almacenDescripcion;
	private String  almacenEstado;
	private Date    almacenFecre;
	private Date    almacenHocre;
	private String  almacenUscre;
	private Date    almacenFemod;
	private Date    almacenHomod;
	private String  almacenUsmod;
	
	

}
