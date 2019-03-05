package com.pe.ventas.back.dtos.rest.usuarios;

import java.io.Serializable;

import com.pe.ventas.back.dtos.base.JsonDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioRestDto implements Serializable {

    private static final long serialVersionUID = 605689904521722810L;

    private Integer id;
    private String alias;
    private String correo;
    private String contrasenya;

    public String aJson() {
        return JsonDto.aJson(this);
    }

}
