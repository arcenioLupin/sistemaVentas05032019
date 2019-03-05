package com.pe.ventas.back.dtos.servicios.unidadmedida;

import java.io.Serializable;

import com.pe.ventas.back.dtos.base.JsonDto;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UnidadMedidaServicioDto implements Serializable {


	private static final long serialVersionUID = -7691269915744859207L;
	
	private String codigo;
	private String unidadMedidaSimbolo;
	private String unidadMedidaNombre;
	
    public String aJson() {
        return JsonDto.aJson(this);
    }

}
