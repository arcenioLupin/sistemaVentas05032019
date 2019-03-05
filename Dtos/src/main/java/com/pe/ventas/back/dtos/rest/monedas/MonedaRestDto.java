package com.pe.ventas.back.dtos.rest.monedas;

import java.io.Serializable;

import com.pe.ventas.back.dtos.base.JsonDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MonedaRestDto implements Serializable {
	
	private static final long serialVersionUID = -4115165761863828883L;
	private String codigo;
	private String monedaSimbolo;
	private String monedaNombre;
	
    public String aJson() {
        return JsonDto.aJson(this);
    }


}
