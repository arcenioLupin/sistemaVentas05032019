package com.pe.ventas.back.dtos.daos.almacenes;

import java.io.Serializable;
import java.util.Date;

import com.pe.ventas.back.dtos.base.JsonDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AlmacenDaoDto implements Serializable {


	private static final long serialVersionUID = -4734443382495107575L;
	
	private Integer codigo;
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
	
	public String aJson() {
	        return JsonDto.aJson(this);
	    }

	

}
