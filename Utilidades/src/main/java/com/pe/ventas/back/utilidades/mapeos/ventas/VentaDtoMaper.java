package com.pe.ventas.back.utilidades.mapeos.ventas;

import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import com.pe.ventas.back.dtos.daos.ventas.VentaDaoDto;
import com.pe.ventas.back.dtos.daos.sql.ventas.VentaSqlDto;
import com.pe.ventas.back.dtos.rest.ventas.VentaRestDto;
import com.pe.ventas.back.dtos.servicios.ventas.VentaServicioDto;
import com.pe.ventas.back.utilidades.mapeos.ventas.VentaDtoMaper;

@Mapper
public interface VentaDtoMaper {
	
VentaDtoMaper INSTANCE = Mappers.getMapper(VentaDtoMaper.class);
	
	@Mappings({ @Mapping(source = "ventaCodigo", target = "codVenta") })
	VentaDaoDto VentaSqlDtoAVentaDaoDTo(VentaSqlDto venta);	
	
	@InheritInverseConfiguration
	VentaSqlDto VentaDaoDtoAVentaSqlDto(VentaDaoDto venta);
	

	
	
	VentaDaoDto  VentaServicioDtoAVentaDaoDto (VentaServicioDto venta);
	
	VentaServicioDto VentaDaoDtoAVentaServicioDto(VentaDaoDto venta);
	
	VentaServicioDto VentaRestDtoAVentaServicioDto (VentaRestDto venta);

	VentaRestDto VentaServicioDtoAVentaRestDto(VentaServicioDto venta);
	 
	
    List<VentaRestDto> convertirListaVentaServicioDtoAVentaRestDto(List<VentaServicioDto> venta);

    List<VentaDaoDto> convertirListaVentaSqlDtoAVentaDaoDto(List<VentaSqlDto> venta);

    List<VentaSqlDto> convertirListaVentaDaoDtoAVentaSqlDto(List<VentaDaoDto> venta);

    List<VentaServicioDto> convertirListaVentaDaoDtoAVentaServicioDto(List<VentaDaoDto> venta);		

}
