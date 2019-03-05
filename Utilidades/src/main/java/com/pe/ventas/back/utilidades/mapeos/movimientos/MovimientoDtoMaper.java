package com.pe.ventas.back.utilidades.mapeos.movimientos;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import com.pe.ventas.back.dtos.daos.movimientos.MovimientoDaoDto;
import com.pe.ventas.back.dtos.daos.sql.movimientos.MovimientoSqlDto;
import com.pe.ventas.back.dtos.rest.movimientos.MovimientoRestDto;
import com.pe.ventas.back.dtos.servicios.movimientos.MovimientoServicioDto;


@Mapper
public interface MovimientoDtoMaper {
	
	
	MovimientoDtoMaper INSTANCE = Mappers.getMapper(MovimientoDtoMaper.class);
	
	@Mappings({ @Mapping(source = "movimientoCodigo", target = "codigo") })
	MovimientoDaoDto movimientoSqlDtoAmovimientoDaoDTo(MovimientoSqlDto movimiento);	
	
	@InheritInverseConfiguration
	MovimientoSqlDto movimientoDaoDtoAmovimientoSqlDto(MovimientoDaoDto movimiento);
	
	
	MovimientoDaoDto  movimientoServicioDtoAmovimientoDaoDto (MovimientoServicioDto movimiento);
	
	MovimientoServicioDto movimientoDaoDtoAMovimientoServicioDto(MovimientoDaoDto movimiento);
	
	MovimientoServicioDto movimientoRestDtoAmovimientoServicioDto (MovimientoRestDto movimiento);
	

	MovimientoRestDto movimientoServicioDtoAmovimientoRestDto(MovimientoServicioDto movimiento);
	 
	
    List<MovimientoRestDto> convertirListaMovimientoServicioDtoAMovimientoRestDto(List<MovimientoServicioDto> movimiento);

    List<MovimientoDaoDto> convertirListaMovimientoSqlDtoAMovimientoDaoDto(List<MovimientoSqlDto> movimiento);

    List<MovimientoSqlDto> convertirListaMovimientoDaoDtoAMovimientoSqlDto(List<MovimientoDaoDto> movimiento);

    List<MovimientoServicioDto> convertirListaMovimientoDaoDtoAMovimientoServicioDto(List<MovimientoDaoDto> movimiento);
	

}
