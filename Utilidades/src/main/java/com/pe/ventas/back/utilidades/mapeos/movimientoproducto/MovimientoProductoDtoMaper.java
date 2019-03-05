package com.pe.ventas.back.utilidades.mapeos.movimientoproducto;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.pe.ventas.back.dtos.daos.movimientoproducto.MovimientoProductoDaoDto;
import com.pe.ventas.back.dtos.daos.sql.movimientoproducto.MovimientoProductoSqlDto;
import com.pe.ventas.back.dtos.rest.movimientoproducto.MovimientoProductoRestDto;
import com.pe.ventas.back.dtos.servicios.movimientoproducto.MovimientoProductoServicioDto;
import com.pe.ventas.back.utilidades.mapeos.movimientoproducto.MovimientoProductoDtoMaper;

@Mapper
public interface MovimientoProductoDtoMaper {
	
MovimientoProductoDtoMaper INSTANCE = Mappers.getMapper(MovimientoProductoDtoMaper.class);
	
	@Mappings({ @Mapping(source = "movimientoCodigo", target = "codMovimiento"),
		       @Mapping(source = "productoCodigo", target = "codProducto")
	         })
	MovimientoProductoDaoDto MovimientoProductoSqlDtoAMovimientoProductoDaoDTo(MovimientoProductoSqlDto movimientoProducto);	
	
	@InheritInverseConfiguration
	MovimientoProductoSqlDto MovimientoProductoDaoDtoAMovimientoProductoSqlDto(MovimientoProductoDaoDto movimientoProducto);
	
	
	
	MovimientoProductoDaoDto  MovimientoProductoServicioDtoAMovimientoProductoDaoDto (MovimientoProductoServicioDto movimientoProducto);
	
	MovimientoProductoServicioDto MovimientoProductoDaoDtoAMovimientoProductoServicioDto(MovimientoProductoDaoDto movimientoProducto);
	
	
	MovimientoProductoServicioDto MovimientoProductoRestDtoAMovimientoProductoServicioDto (MovimientoProductoRestDto movimientoProducto);
	
	MovimientoProductoRestDto MovimientoProductoServicioDtoAMovimientoProductoRestDto(MovimientoProductoServicioDto movimientoProducto);
	 
	
    List<MovimientoProductoRestDto> convertirListaMovimientoProductoServicioDtoMovimientoProductoRestDto(List<MovimientoProductoServicioDto> movimientoProducto);

    List<MovimientoProductoDaoDto> convertirListaMovimientoProductoSqlDtoAMovimientoProductoDaoDto(List<MovimientoProductoSqlDto> movimientoProducto);

    List<MovimientoProductoSqlDto> convertirListaMovimientoProductoDaoDtoAMovimientoProductoSqlDto(List<MovimientoProductoDaoDto> movimientoProducto);

    List<MovimientoProductoServicioDto> convertirListaMovimientoProductoDaoDtoAMovimientoProductoServicioDto(List<MovimientoProductoDaoDto> movimientoProducto);

}
