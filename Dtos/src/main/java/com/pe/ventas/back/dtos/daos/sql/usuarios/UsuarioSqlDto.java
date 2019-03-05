package com.pe.ventas.back.dtos.daos.sql.usuarios;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioSqlDto implements Serializable {

    private static final long serialVersionUID = -6638350426618012372L;

    private Integer id;
    private String alias;
    private String correo;
    private String contrasenya;
    private Date registro;
    private Date acceso;
    private Date modificacion;

}
