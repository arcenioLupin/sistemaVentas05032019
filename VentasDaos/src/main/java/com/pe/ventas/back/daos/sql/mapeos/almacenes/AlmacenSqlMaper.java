package com.pe.ventas.back.daos.sql.mapeos.almacenes;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pe.ventas.back.dtos.daos.sql.almacenes.AlmacenSqlDto;


@Mapper
public interface AlmacenSqlMaper {
	
	public List<AlmacenSqlDto> selectTodosAlmacenes(); 
	public AlmacenSqlDto selectUnAlmacen(AlmacenSqlDto almacen);
	public Integer insert (AlmacenSqlDto almacen);
	public Integer update (AlmacenSqlDto almacen);
	public Integer delete (AlmacenSqlDto almacen);

}
