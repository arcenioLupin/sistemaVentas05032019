package com.pe.ventas.back.daos.sql.mapeos.tiposalmacen;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.pe.ventas.back.dtos.daos.sql.tiposalmacen.TipoAlmacenSqlDto;

@Mapper
public interface TiposAlmacenSqlMaper {
	
	public List<TipoAlmacenSqlDto> selectTodosTipoAlmacen(); 
	public TipoAlmacenSqlDto selectUnTipoAlmacen(TipoAlmacenSqlDto tipoAlmacen);
	public Integer insert (TipoAlmacenSqlDto tipoAlmacen);
	public Integer update (TipoAlmacenSqlDto tipoAlmacen);
	public Integer delete (TipoAlmacenSqlDto tipoAlmacen);

}
