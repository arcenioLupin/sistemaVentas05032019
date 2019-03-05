package com.pe.ventas.back.dtos.servicios.almacenes;

import java.io.Serializable;
import java.util.Date;
import com.pe.ventas.back.dtos.base.JsonDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AlmacenServicioDto implements Serializable {
	
	private static final long serialVersionUID = -2500023932172546528L;
	
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
