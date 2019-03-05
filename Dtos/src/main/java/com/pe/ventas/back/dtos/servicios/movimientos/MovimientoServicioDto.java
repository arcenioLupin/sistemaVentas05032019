package com.pe.ventas.back.dtos.servicios.movimientos;

import java.io.Serializable;
import java.util.Date;

import com.pe.ventas.back.dtos.base.JsonDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovimientoServicioDto implements Serializable {
	
	private static final long serialVersionUID = 3677828829563176196L;
	
	private Integer codigo;
	private Date    movimientoFecha;
	private Integer almacenCodOrigen;
	private Integer almacenCodDestino;
	private String  movimientoComentario;
	private Date    movimientoFecre;
	private Date    movimientoHocre;
	private String  movimientoUscre;
	private Date    movimientoFemod;
	private Date    movimientoHomod;
	private String  movimientoUsmod;
	
    public String aJson() {
        return JsonDto.aJson(this);
    }

}
