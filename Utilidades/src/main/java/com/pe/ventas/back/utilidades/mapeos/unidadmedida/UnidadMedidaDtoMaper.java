package com.pe.ventas.back.utilidades.mapeos.unidadmedida;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.pe.ventas.back.dtos.daos.unidadmedida.UnidadMedidaDaoDto;
import com.pe.ventas.back.dtos.daos.sql.unidadmedida.UnidadMedidaSqlDto;
import com.pe.ventas.back.dtos.rest.unidadmedida.UnidadMedidaRestDto;
import com.pe.ventas.back.dtos.servicios.unidadmedida.UnidadMedidaServicioDto;
import com.pe.ventas.back.utilidades.mapeos.unidadmedida.UnidadMedidaDtoMaper;

@Mapper
public interface UnidadMedidaDtoMaper {
	
	
	UnidadMedidaDtoMaper INSTANCE = Mappers.getMapper(UnidadMedidaDtoMaper.class);
	
	@Mappings({ @Mapping(source = "unidadMedidaCodigo", target = "codigo") })
	UnidadMedidaDaoDto unidadMedidaSqlDtoAunidadMedidaDaoDTo(UnidadMedidaSqlDto unidadMedida);	
	
	@InheritInverseConfiguration
	UnidadMedidaSqlDto unidadMedidaDaoDtoAunidadMedidaSqlDto(UnidadMedidaDaoDto unidadMedida);
	

	
	
	UnidadMedidaDaoDto  unidadMedidaServicioDtoAunidadMedidaDaoDto (UnidadMedidaServicioDto unidadMedida);
	
	UnidadMedidaServicioDto unidadMedidaDaoDtoAunidadMedidaServicioDto(UnidadMedidaDaoDto unidadMedida);
	
	UnidadMedidaServicioDto unidadMedidaRestDtoAunidadMedidaServicioDto (UnidadMedidaRestDto unidadMedida);
	
	UnidadMedidaRestDto unidadMedidaServicioDtoAunidadMedidaRestDto(UnidadMedidaServicioDto unidadMedida);
	 
	
    List<UnidadMedidaRestDto> convertirListaunidadMedidaServicioDtoAunidadMedidaRestDto(List<UnidadMedidaServicioDto> unidadMedida);

    List<UnidadMedidaDaoDto> convertirListaunidadMedidaSqlDtoAunidadMedidaDaoDto(List<UnidadMedidaSqlDto> unidadMedida);

    List<UnidadMedidaSqlDto> convertirListaunidadMedidaDaoDtoAunidadMedidaSqlDto(List<UnidadMedidaDaoDto> unidadMedida);

    List<UnidadMedidaServicioDto> convertirListaunidadMedidaDaoDtoAunidadMedidaServicioDto(List<UnidadMedidaDaoDto> unidadMedida);

}
