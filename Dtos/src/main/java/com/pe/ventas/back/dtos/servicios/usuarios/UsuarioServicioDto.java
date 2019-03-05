package com.pe.ventas.back.dtos.servicios.usuarios;

import java.io.Serializable;
import java.util.Date;

import com.pe.ventas.back.dtos.base.JsonDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioServicioDto implements Serializable {

    private static final long serialVersionUID = 3295452824284665006L;
    
    private Integer identificador;
    private String alias;
    private String correo;
    private String contrasenya;
    private Date registro;
    private Date acceso;
    private Date modificacion;

    public String aJson() {
        return JsonDto.aJson(this);
    }

}
