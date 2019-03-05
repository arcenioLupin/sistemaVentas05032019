package com.pe.ventas.back.utilidades.mapeos.vendedores;

import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import com.pe.ventas.back.dtos.daos.sql.vendedores.VendedorSqlDto;
import com.pe.ventas.back.dtos.daos.vendedores.VendedorDaoDto;
import com.pe.ventas.back.dtos.rest.vendedores.VendedorRestDto;
import com.pe.ventas.back.dtos.servicios.vendedores.VendedorServicioDto;


@Mapper
public interface VendedorDtoMaper {
	
	VendedorDtoMaper INSTANCE = Mappers.getMapper(VendedorDtoMaper.class);

	    @Mappings({ @Mapping(source = "vendedorCodigo", target = "codigo") })
	    VendedorDaoDto vendedorSqlDtoAVendedorDaoDto(VendedorSqlDto vendedor);

	    @InheritInverseConfiguration
	    VendedorSqlDto vendedorDaoDtoAVendedorSqlDto(VendedorDaoDto vendedor);
	    
	  
	    VendedorDaoDto vendedorServicioDtoAVendedorDaoDto(VendedorServicioDto vendedor);
	    
	    VendedorServicioDto vendedorDaoDtoAVendedorServicioDto(VendedorDaoDto vendedor);
	    
	    VendedorServicioDto vendedorRestDtoAVendedorServicioDto(VendedorRestDto vendedor);

	    VendedorRestDto vendedorServicioDtoAVendedorRestDto(VendedorServicioDto vendedor);

	    List<VendedorRestDto> convertirListaVendedorServicioDtoAVendedorRestDto(List<VendedorServicioDto> vendedor);

	    List<VendedorDaoDto> convertirListaVendedorSqlDtoAVendedorDaoDto(List<VendedorSqlDto> vendedor);

	    List<VendedorSqlDto> convertirListaVendedorDaoDtoAVendedorSqlDto(List<VendedorDaoDto> vendedor);

	    List<VendedorServicioDto> convertirListaVendedorDaoDtoAVendedorServicioDto(List<VendedorDaoDto> vendedor);

}
