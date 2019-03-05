package com.pe.ventas.back.utilidades.mapeos.almacenvendedor;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.pe.ventas.back.dtos.daos.almacenvendedor.AlmacenVendedorDaoDto;
import com.pe.ventas.back.dtos.daos.sql.almacenvendedor.AlmacenVendedorSqlDto;
import com.pe.ventas.back.dtos.rest.almacenvendedor.AlmacenVendedorRestDto;
import com.pe.ventas.back.dtos.servicios.almacenvendedor.AlmacenVendedorServicioDto;
import com.pe.ventas.back.utilidades.mapeos.almacenvendedor.AlmacenVendedorDtoMaper;

@Mapper
public interface AlmacenVendedorDtoMaper {
	
	AlmacenVendedorDtoMaper INSTANCE = Mappers.getMapper(AlmacenVendedorDtoMaper.class);
	
	@Mappings({ @Mapping(source = "almacenCodigo", target = "codAlmacen"),
		       @Mapping(source = "vendedorCodigo", target = "codVendedor")
	         })
	AlmacenVendedorDaoDto AlmacenVendedorSqlDtoAAlmacenVendedorDaoDTo(AlmacenVendedorSqlDto almacenVendedor);	
	
	@InheritInverseConfiguration
	AlmacenVendedorSqlDto AlmacenVendedorDaoDtoAAlmacenVendedorSqlDto(AlmacenVendedorDaoDto almacenVendedor);
	

	
	
	AlmacenVendedorDaoDto  AlmacenVendedorServicioDtoAAlmacenVendedorDaoDto (AlmacenVendedorServicioDto almacenVendedor);
	
	AlmacenVendedorServicioDto AlmacenVendedorDaoDtoAAlmacenVendedorServicioDto(AlmacenVendedorDaoDto almacenVendedor);
	
	
	AlmacenVendedorServicioDto AlmacenVendedorRestDtoAAlmacenVendedorServicioDto (AlmacenVendedorRestDto almacenVendedor);
	
	AlmacenVendedorRestDto AlmacenVendedorServicioDtoAAlmacenVendedorRestDto(AlmacenVendedorServicioDto almacenVendedor);
	 
	
    List<AlmacenVendedorRestDto> convertirListaAlmacenVendedorServicioDtoAlmacenVendedorRestDto(List<AlmacenVendedorServicioDto> almacenVendedor);

    List<AlmacenVendedorDaoDto> convertirListaAlmacenVendedorSqlDtoAAlmacenVendedorDaoDto(List<AlmacenVendedorSqlDto> almacenVendedor);

    List<AlmacenVendedorSqlDto> convertirListaAlmacenVendedorDaoDtoAAlmacenVendedorSqlDto(List<AlmacenVendedorDaoDto> almacenVendedor);

    List<AlmacenVendedorServicioDto> convertirListaAlmacenVendedorDaoDtoAAlmacenVendedorServicioDto(List<AlmacenVendedorDaoDto> categoria);

}
