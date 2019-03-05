package com.pe.ventas.back.utilidades.mapeos.almacenproducto;

import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import com.pe.ventas.back.dtos.daos.almacenproducto.AlmacenProductoDaoDto;
import com.pe.ventas.back.dtos.daos.sql.almacenproducto.AlmacenProductoSqlDto;
import com.pe.ventas.back.dtos.rest.almacenproducto.AlmacenProductoRestDto;
import com.pe.ventas.back.dtos.servicios.almacenproducto.AlmacenProductoServicioDto;

@Mapper
public interface AlmacenProductoDtoMaper {
	
	AlmacenProductoDtoMaper INSTANCE = Mappers.getMapper(AlmacenProductoDtoMaper.class);
	
	@Mappings({ @Mapping(source = "almacenCodigo", target = "codAlmacen"),
		       @Mapping(source = "productoCodigo", target = "codProducto")
	         })
	AlmacenProductoDaoDto almacenProductoSqlDtoAalmacenProductoDaoDTo(AlmacenProductoSqlDto almacenProducto);	
	
	@InheritInverseConfiguration
	AlmacenProductoSqlDto almacenProductoDaoDtoAalmacenProductoSqlDto(AlmacenProductoDaoDto almacenProducto);
	

	
	
	AlmacenProductoDaoDto  almacenProductoServicioDtoAalmacenProductoDaoDto (AlmacenProductoServicioDto almacenProducto);
	
	AlmacenProductoServicioDto almacenProductoDaoDtoAalmacenProductoServicioDto(AlmacenProductoDaoDto almacenProducto);
	
	
	AlmacenProductoServicioDto almacenProductoRestDtoAalmacenProductoServicioDto (AlmacenProductoRestDto almacenProducto);
	
	AlmacenProductoRestDto almacenProductoServicioDtoAalmacenProductoRestDto(AlmacenProductoServicioDto almacenProducto);
	 
	
    List<AlmacenProductoRestDto> convertirListaAlmacenProductoServicioDtoAlmacenProductoRestDto(List<AlmacenProductoServicioDto> almacenProducto);

    List<AlmacenProductoDaoDto> convertirListaAlmacenProductoSqlDtoAAlmacenProductoDaoDto(List<AlmacenProductoSqlDto> almacenProducto);

    List<AlmacenProductoSqlDto> convertirListaAlmacenProductoDaoDtoAAlmacenProductoSqlDto(List<AlmacenProductoDaoDto> almacenProducto);

    List<AlmacenProductoServicioDto> convertirListaAlmacenProductoDaoDtoAAlmacenProductoServicioDto(List<AlmacenProductoDaoDto> almacenProducto);

}
