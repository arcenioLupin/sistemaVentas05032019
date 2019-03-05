package com.pe.ventas.back.utilidades.mapeos.productos;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import com.pe.ventas.back.dtos.daos.productos.ProductoDaoDto;
import com.pe.ventas.back.dtos.daos.sql.productos.ProductoSqlDto;
import com.pe.ventas.back.dtos.rest.productos.ProductoRestDto;
import com.pe.ventas.back.dtos.servicios.productos.ProductoServicioDto;


@Mapper
public interface ProductoDtoMaper {
	
	ProductoDtoMaper INSTANCE = Mappers.getMapper(ProductoDtoMaper.class);
	
	@Mappings({ @Mapping(source = "productoCodigo", target = "codigo") })
	ProductoDaoDto productoSqlDtoAproductoDaoDTo(ProductoSqlDto producto);	
	
	@InheritInverseConfiguration
	ProductoSqlDto productoDaoDtoAproductoSqlDto(ProductoDaoDto producto);
	

	
	
	ProductoDaoDto  productoServicioDtoAproductoDaoDto (ProductoServicioDto producto);
	
	ProductoServicioDto productoDaoDtoAproductoServicioDto(ProductoDaoDto producto);
	
	ProductoServicioDto productoRestDtoAproductoServicioDto (ProductoRestDto producto);

	ProductoRestDto productoServicioDtoAproductoRestDto(ProductoServicioDto producto);
	 
	
    List<ProductoRestDto> convertirListaProductoServicioDtoAProductoRestDto(List<ProductoServicioDto> producto);

    List<ProductoDaoDto> convertirListaProductoSqlDtoAProductoDaoDto(List<ProductoSqlDto> producto);

    List<ProductoSqlDto> convertirListaProductoDaoDtoAProductoSqlDto(List<ProductoDaoDto> producto);

    List<ProductoServicioDto> convertirListaProductoDaoDtoAProductoServicioDto(List<ProductoDaoDto> producto);	

}
