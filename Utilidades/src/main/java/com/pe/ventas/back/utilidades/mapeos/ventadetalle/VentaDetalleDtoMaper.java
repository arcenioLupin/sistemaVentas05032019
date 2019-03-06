package com.pe.ventas.back.utilidades.mapeos.ventadetalle;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import com.pe.ventas.back.dtos.daos.ventadetalle.VentaDetalleDaoDto;
import com.pe.ventas.back.dtos.daos.sql.ventadetalle.VentaDetalleSqlDto;
import com.pe.ventas.back.dtos.rest.ventadetalle.VentaDetalleRestDto;
import com.pe.ventas.back.dtos.servicios.ventadetalle.VentaDetalleServicioDto;
import com.pe.ventas.back.utilidades.mapeos.ventadetalle.VentaDetalleDtoMaper;

@Mapper
public interface VentaDetalleDtoMaper {
	
VentaDetalleDtoMaper INSTANCE = Mappers.getMapper(VentaDetalleDtoMaper.class);
	
	@Mappings({ @Mapping(source = "ventaCodigo", target = "codVenta"),
		       @Mapping(source = "ventaPosicion", target = "posicionVenta")
	         })
	VentaDetalleDaoDto VentaDetalleSqlDtoAVentaDetalleDaoDTo(VentaDetalleSqlDto ventaDetalle);	
	
	@InheritInverseConfiguration
	VentaDetalleSqlDto VentaDetalleDaoDtoAVentaDetalleSqlDto(VentaDetalleDaoDto ventaDetalle);
	
	
	
	VentaDetalleDaoDto  VentaDetalleServicioDtoAVentaDetalleDaoDto (VentaDetalleServicioDto ventaDetalle);
	
	VentaDetalleServicioDto VentaDetalleDaoDtoAVentaDetalleServicioDto(VentaDetalleDaoDto ventaDetalle);
	
	
	VentaDetalleServicioDto VentaDetalleRestDtoAVentaDetalleServicioDto (VentaDetalleRestDto ventaDetalle);
	
	VentaDetalleRestDto VentaDetalleServicioDtoAVentaDetalleRestDto(VentaDetalleServicioDto ventaDetalle);
	 
	
    List<VentaDetalleRestDto> convertirListaVentaDetalleServicioDtoVentaDetalleRestDto(List<VentaDetalleServicioDto> ventaDetalle);

    List<VentaDetalleDaoDto> convertirListaVentaDetalleSqlDtoAVentaDetalleDaoDto(List<VentaDetalleSqlDto> ventaDetalle);

    List<VentaDetalleSqlDto> convertirListaVentaDetalleDaoDtoAVentaDetalleSqlDto(List<VentaDetalleDaoDto> ventaDetalle);

    List<VentaDetalleServicioDto> convertirListaVentaDetalleDaoDtoAVentaDetalleServicioDto(List<VentaDetalleDaoDto> ventaDetalle);

}
