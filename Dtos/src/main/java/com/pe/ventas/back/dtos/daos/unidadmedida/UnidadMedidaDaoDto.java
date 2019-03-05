package com.pe.ventas.back.dtos.daos.unidadmedida;

import java.io.Serializable;

import com.pe.ventas.back.dtos.base.JsonDto;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UnidadMedidaDaoDto implements Serializable {
	
	private static final long serialVersionUID = 4168549485860129950L;

	private String codigo;
	private String unidadMedidaSimbolo;
	private String unidadMedidaNombre;
	
    public String aJson() {
        return JsonDto.aJson(this);
    }

}
