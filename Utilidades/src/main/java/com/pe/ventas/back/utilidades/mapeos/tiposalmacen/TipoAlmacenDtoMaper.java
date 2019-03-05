package com.pe.ventas.back.utilidades.mapeos.tiposalmacen;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import com.pe.ventas.back.dtos.daos.sql.tiposalmacen.TipoAlmacenSqlDto;
import com.pe.ventas.back.dtos.daos.tiposalmacen.TipoAlmacenDaoDto;
import com.pe.ventas.back.dtos.rest.tiposalmacen.TipoAlmacenRestDto;
import com.pe.ventas.back.dtos.servicios.tiposalmacen.TipoAlmacenServicioDto;

@Mapper
public interface TipoAlmacenDtoMaper {
	
	TipoAlmacenDtoMaper INSTANCE = Mappers.getMapper(TipoAlmacenDtoMaper.class);
	
	@Mappings({ @Mapping(source = "tipoAlmacenCodigo", target = "codigo") })
	TipoAlmacenDaoDto tipoAlmacenSqlDtoAtipoAlmacenDaoDTo(TipoAlmacenSqlDto tipoAlmacen);	
	
	@InheritInverseConfiguration
	TipoAlmacenSqlDto tipoAlmacenDaoDtoAtipoAlmacenSqlDto(TipoAlmacenDaoDto tipoAlmacen);
		
	
	TipoAlmacenDaoDto  tipoAlmacenServicioDtoAtipoAlmacenDaoDto (TipoAlmacenServicioDto tipoAlmacen);
	
	TipoAlmacenServicioDto tipoAlmacenDaoDtoAtipoAlmacenServicioDto(TipoAlmacenDaoDto tipoAlmacen);
	
	
	TipoAlmacenServicioDto tipoAlmacenRestDtoAtipoAlmacenServicioDto (TipoAlmacenRestDto tipoAlmacen);
		
	TipoAlmacenRestDto tipoAlmacenServicioDtoAtipoAlmacenRestDto(TipoAlmacenServicioDto tipoAlmacen);
	 
	
    List<TipoAlmacenRestDto> convertirListaTipoAlmacenServicioDtoATipoAlmacenRestDto(List<TipoAlmacenServicioDto> tipoAlmacen);

    List<TipoAlmacenDaoDto> convertirListaTipoAlmacenSqlDtoATipoAlmacenDaoDto(List<TipoAlmacenSqlDto> tipoAlmacen);

    List<TipoAlmacenSqlDto> convertirListaTipoAlmacenDaoDtoATipoAlmacenSqlDto(List<TipoAlmacenDaoDto> tipoAlmacen);

    List<TipoAlmacenServicioDto> convertirListaTipoAlmacenDaoDtoATipoAlmacenServicioDto(List<TipoAlmacenDaoDto> tipoAlmacen);

}
