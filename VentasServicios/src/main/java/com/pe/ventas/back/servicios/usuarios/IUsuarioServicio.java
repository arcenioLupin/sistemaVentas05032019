package com.pe.ventas.back.servicios.usuarios;

import java.util.List;

import com.pe.ventas.back.dtos.servicios.usuarios.UsuarioServicioDto;

public interface IUsuarioServicio {

    Boolean autenticar(UsuarioServicioDto usuario);

    Boolean actualizar(UsuarioServicioDto usuario);
    
    Boolean eliminar(UsuarioServicioDto usuario);
    
    Boolean insertarUsuario(final UsuarioServicioDto usuario);
    
    List<UsuarioServicioDto> todosLosUsuarios();
    
    UsuarioServicioDto obtenerUnUsuario(UsuarioServicioDto usuario);
    
    void limpiarCache();

}
