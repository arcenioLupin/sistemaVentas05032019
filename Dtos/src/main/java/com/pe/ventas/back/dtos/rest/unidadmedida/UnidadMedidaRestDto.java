package com.pe.ventas.back.dtos.rest.unidadmedida;

import java.io.Serializable;

import com.pe.ventas.back.dtos.base.JsonDto;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UnidadMedidaRestDto implements Serializable {

	private static final long serialVersionUID = -1596752992848621582L;
	
	private String codigo;
	private String unidadMedidaSimbolo;
	private String unidadMedidaNombre;
	
    public String aJson() {
        return JsonDto.aJson(this);
    }
	

}
