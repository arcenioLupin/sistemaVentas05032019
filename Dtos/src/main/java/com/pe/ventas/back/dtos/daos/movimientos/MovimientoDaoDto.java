package com.pe.ventas.back.dtos.daos.movimientos;

import java.io.Serializable;
import java.util.Date;
import com.pe.ventas.back.dtos.base.JsonDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovimientoDaoDto implements Serializable {
	
	private static final long serialVersionUID = -5511295972463992861L;
	
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
