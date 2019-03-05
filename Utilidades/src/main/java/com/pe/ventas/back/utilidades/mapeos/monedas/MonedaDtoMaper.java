package com.pe.ventas.back.utilidades.mapeos.monedas;

import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import com.pe.ventas.back.dtos.daos.monedas.MonedaDaoDto;
import com.pe.ventas.back.dtos.daos.sql.monedas.MonedaSqlDto;
import com.pe.ventas.back.dtos.rest.monedas.MonedaRestDto;
import com.pe.ventas.back.dtos.servicios.monedas.MonedaServicioDto;
import com.pe.ventas.back.utilidades.mapeos.monedas.MonedaDtoMaper;

@Mapper
public interface MonedaDtoMaper {
	
	MonedaDtoMaper INSTANCE = Mappers.getMapper(MonedaDtoMaper.class);
	
	@Mappings({ @Mapping(source = "monedaCodigo", target = "codigo") })
	MonedaDaoDto monedaSqlDtoAmonedaDaoDTo(MonedaSqlDto moneda);	
	
	@InheritInverseConfiguration
	MonedaSqlDto monedaDaoDtoAmonedaSqlDto(MonedaDaoDto moneda);
	

	
	
	MonedaDaoDto  monedaServicioDtoAmonedaDaoDto (MonedaServicioDto moneda);
	
	MonedaServicioDto monedaDaoDtoAmonedaServicioDto(MonedaDaoDto moneda);
	
	MonedaServicioDto monedaRestDtoAmonedaServicioDto (MonedaRestDto moneda);
	
	MonedaRestDto monedaServicioDtoAmonedaRestDto(MonedaServicioDto moneda);
	 
	
    List<MonedaRestDto> convertirListaMonedaServicioDtoAMonedaRestDto(List<MonedaServicioDto> moneda);

    List<MonedaDaoDto> convertirListaMonedaSqlDtoAMonedaDaoDto(List<MonedaSqlDto> moneda);

    List<MonedaSqlDto> convertirListaMonedaDaoDtoAMonedaSqlDto(List<MonedaDaoDto> moneda);

    List<MonedaServicioDto> convertirListaMonedaDaoDtoAMonedaServicioDto(List<MonedaDaoDto> moneda);

}
