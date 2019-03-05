package com.pe.ventas.back.dtos.servicios.monedas;

import java.io.Serializable;

import com.pe.ventas.back.dtos.base.JsonDto;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class MonedaServicioDto implements Serializable {


	private static final long serialVersionUID = -5573270599758906271L;
	private String codigo;
	private String monedaSimbolo;
	private String monedaNombre;
	
    public String aJson() {
        return JsonDto.aJson(this);
    }

}
