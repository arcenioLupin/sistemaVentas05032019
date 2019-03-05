package com.pe.ventas.back.utilidades.mapeos.categorias;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.pe.ventas.back.dtos.daos.categorias.CategoriaDaoDto;
import com.pe.ventas.back.dtos.daos.sql.categorias.CategoriaSqlDto;
import com.pe.ventas.back.dtos.rest.categorias.CategoriaRestDto;
import com.pe.ventas.back.dtos.servicios.categorias.CategoriaServicioDto;

@Mapper
public interface CategoriaDtoMaper {
	
	CategoriaDtoMaper INSTANCE = Mappers.getMapper(CategoriaDtoMaper.class);
		
	@Mappings({ @Mapping(source = "categoriaCodigo", target = "codigo") })
	CategoriaDaoDto categoriaSqlDtoAcategoriaDaoDTo(CategoriaSqlDto categoria);	
	
	@InheritInverseConfiguration
	CategoriaSqlDto categoriaDaoDtoAcategoriaSqlDto(CategoriaDaoDto categoria);
	

	
	
	CategoriaDaoDto  categoriaServicioDtoAcategoriaDaoDto (CategoriaServicioDto categoria);
	
	CategoriaServicioDto categoriaDaoDtoACategoriaServicioDto(CategoriaDaoDto categoria);
	
	@Mappings({ @Mapping(source = "categoriaCodigo", target = "codigo") })
	CategoriaServicioDto categoriaRestDtoAcategoriaServicioDto (CategoriaRestDto categoria);
	
	 @InheritInverseConfiguration
	CategoriaRestDto categoriaServicioDtoAcategoriaRestDto(CategoriaServicioDto categoria);
	 
	
    List<CategoriaRestDto> convertirListaCategoriaServicioDtoACategoriaRestDto(List<CategoriaServicioDto> categoria);

    List<CategoriaDaoDto> convertirListaCategoriaSqlDtoACategoriaDaoDto(List<CategoriaSqlDto> categoria);

    List<CategoriaSqlDto> convertirListaCategoriaDaoDtoACategoriaSqlDto(List<CategoriaDaoDto> categoria);

    List<CategoriaServicioDto> convertirListaCategoriaDaoDtoACategoriaServicioDto(List<CategoriaDaoDto> categoria);
	
}
