package com.pe.ventas.back.utilidades.mapeos.almacenes;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import com.pe.ventas.back.dtos.daos.almacenes.AlmacenDaoDto;
import com.pe.ventas.back.dtos.daos.sql.almacenes.AlmacenSqlDto;
import com.pe.ventas.back.dtos.rest.almacenes.AlmacenRestDto;
import com.pe.ventas.back.dtos.servicios.almacenes.AlmacenServicioDto;


@Mapper
public interface AlmacenDtoMaper {
	
	AlmacenDtoMaper INSTANCE = Mappers.getMapper(AlmacenDtoMaper.class);
	
	@Mappings({ @Mapping(source = "almacenCodigo", target = "codigo") })
	AlmacenDaoDto almacenSqlDtoAalmacenDaoDTo(AlmacenSqlDto almacen);	
	
	@InheritInverseConfiguration
	AlmacenSqlDto almacenDaoDtoAalmacenSqlDto(AlmacenDaoDto almacen);
		
	
	AlmacenDaoDto  almacenServicioDtoAalmacenDaoDto (AlmacenServicioDto almacen);
	
	AlmacenServicioDto almacenDaoDtoAalmacenServicioDto(AlmacenDaoDto almacen);
	
	AlmacenServicioDto almacenRestDtoAalmacenServicioDto (AlmacenRestDto almacen);
	
	AlmacenRestDto almacenServicioDtoAalmacenRestDto(AlmacenServicioDto almacen);
	 
	
    List<AlmacenRestDto> convertirListaAlmServicioDtoAAlmRestDto(List<AlmacenServicioDto> almacen);

    List<AlmacenDaoDto> convertirListaAlmacenSqlDtoAAlmacenDaoDto(List<AlmacenSqlDto> almacen);

    List<AlmacenSqlDto> convertirListaAlmacenDaoDtoAAlmacenSqlDto(List<AlmacenDaoDto> almacen);

    List<AlmacenServicioDto> convertirListaAlmacenDaoDtoAAlmacenServicioDto(List<AlmacenDaoDto> almacen);

}
