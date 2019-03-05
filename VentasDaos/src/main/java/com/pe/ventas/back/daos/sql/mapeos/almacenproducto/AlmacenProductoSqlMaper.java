package com.pe.ventas.back.daos.sql.mapeos.almacenproducto;

import java.util.List;
import org.mapstruct.Mapper;
import com.pe.ventas.back.dtos.daos.sql.almacenproducto.AlmacenProductoSqlDto;


@Mapper
public interface AlmacenProductoSqlMaper {
	
	public List<AlmacenProductoSqlDto> selectTodosAlmacenProducto(); 
	public AlmacenProductoSqlDto selectUnaAlmacenProducto(AlmacenProductoSqlDto almacenProducto);
	public Integer insert (AlmacenProductoSqlDto almacenProducto);
	public Integer update (AlmacenProductoSqlDto almacenProducto);
	public Integer delete (AlmacenProductoSqlDto almacenProducto);

}
