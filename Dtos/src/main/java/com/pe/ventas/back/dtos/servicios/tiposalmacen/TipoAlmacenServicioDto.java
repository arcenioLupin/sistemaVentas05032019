package com.pe.ventas.back.dtos.servicios.tiposalmacen;

import java.io.Serializable;
import java.util.Date;

import com.pe.ventas.back.dtos.base.JsonDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TipoAlmacenServicioDto implements Serializable {

	private static final long serialVersionUID = -7411325901122715991L;
	
	private Integer codigo;
	private String  tipoAlmacenNombre;
	private String  tipoAlmacenDescripcion;
	private String  tipoAlmacenEstado;
	private Date    tipoAlmacenFecre;
	private Date    tipoAlmacenHocre;
	private Integer tipoAlmacenUscre;
	private Date    tipoAlmacenFemod;
	private Date    tipoAlmacenHomod;
	private String  tipoAlmacenUsmod;
	
    public String aJson() {
        return JsonDto.aJson(this);
    }


}
