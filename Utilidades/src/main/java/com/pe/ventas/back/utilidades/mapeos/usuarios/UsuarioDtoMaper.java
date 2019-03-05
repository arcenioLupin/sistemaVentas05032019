package com.pe.ventas.back.utilidades.mapeos.usuarios;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.pe.ventas.back.dtos.daos.sql.usuarios.UsuarioSqlDto;
import com.pe.ventas.back.dtos.daos.usuarios.UsuarioDaoDto;
import com.pe.ventas.back.dtos.rest.usuarios.UsuarioRestDto;
import com.pe.ventas.back.dtos.servicios.usuarios.UsuarioServicioDto;

@Mapper
public interface UsuarioDtoMaper {

    UsuarioDtoMaper INSTANCE = Mappers.getMapper(UsuarioDtoMaper.class);

    @Mappings({ @Mapping(source = "id", target = "identificador") })
    UsuarioDaoDto usuarioSqlDtoAUsuarioDaoDto(UsuarioSqlDto usuario);

    @InheritInverseConfiguration
    UsuarioSqlDto usuarioDaoDtoAUsuarioSqlDto(UsuarioDaoDto usuario);

    

  
    UsuarioDaoDto usuarioServicioDtoAUsuarioDaoDto(UsuarioServicioDto usuario);
    
    UsuarioServicioDto usuarioDaoDtoAUsuarioServicioDto(UsuarioDaoDto usuario);
    
    @Mappings({ @Mapping(source = "id", target = "identificador") })
    UsuarioServicioDto usuarioRestDtoAUsuarioServicioDto(UsuarioRestDto usuario);
    
    @InheritInverseConfiguration
    @Mapping(target = "contrasenya", ignore = true)
    UsuarioRestDto usuarioServicioDtoAUsuarioRestDto(UsuarioServicioDto usuario);

   

    List<UsuarioRestDto> convertirListaUsuarioServicioDtoAUsuarioRestDto(List<UsuarioServicioDto> usuario);

    List<UsuarioDaoDto> convertirListaUsuarioSqlDtoAUsuarioDaoDto(List<UsuarioSqlDto> usuario);

    List<UsuarioSqlDto> convertirListaUsuarioDaoDtoAUsuarioSqlDto(List<UsuarioDaoDto> usuario);

    List<UsuarioServicioDto> convertirListaUsuarioDaoDtoAUsuarioServicioDto(List<UsuarioDaoDto> usuario);

}
