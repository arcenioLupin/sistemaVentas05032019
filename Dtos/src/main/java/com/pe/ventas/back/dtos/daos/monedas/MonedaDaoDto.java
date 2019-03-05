package com.pe.ventas.back.dtos.daos.monedas;

import java.io.Serializable;

import com.pe.ventas.back.dtos.base.JsonDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MonedaDaoDto implements Serializable {
	
	
	private static final long serialVersionUID = -6942149130903391474L;
	
	private String codigo;
	private String monedaSimbolo;
	private String monedaNombre;

    public String aJson() {
        return JsonDto.aJson(this);
    }
}
