package com.pe.ventas.back.dtos.rest.tiposalmacen;

import java.io.Serializable;

import com.pe.ventas.back.dtos.base.JsonDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TipoAlmacenRestDto implements Serializable{
	
	private static final long serialVersionUID = -5212641236429900103L;
	
	private Integer codigo;
	private String  tipoAlmacenNombre;
	private String  tipoAlmacenDescripcion;
	private String  tipoAlmacenEstado;
	
    public String aJson() {
        return JsonDto.aJson(this);
    }

}
