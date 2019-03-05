package com.pe.ventas.back.daos.sql.mapeos.categorias;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pe.ventas.back.dtos.daos.sql.categorias.CategoriaSqlDto;

@Mapper
public interface CategoriaSqlMaper {
	
	public List<CategoriaSqlDto> selectTodasCategorias(); 
	public CategoriaSqlDto selectUnaCategoria(CategoriaSqlDto categoria);
	public Integer insert (CategoriaSqlDto categoria);
	public Integer update (CategoriaSqlDto categoria);
	public Integer delete (CategoriaSqlDto categoria);
		
}
