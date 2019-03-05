package com.pe.ventas.back.daos.usuarios;

import java.util.List;

import com.pe.ventas.back.dtos.daos.usuarios.UsuarioDaoDto;

public interface IUsuarioDao {

    List<UsuarioDaoDto> obtenerTodos();

    UsuarioDaoDto obtenerUnUsuario(UsuarioDaoDto usuario);

    UsuarioDaoDto crear(UsuarioDaoDto usuario);

    Boolean actualizar(UsuarioDaoDto usuario);

    Boolean eliminar(UsuarioDaoDto usuario);

    void limpiarCache();
}
