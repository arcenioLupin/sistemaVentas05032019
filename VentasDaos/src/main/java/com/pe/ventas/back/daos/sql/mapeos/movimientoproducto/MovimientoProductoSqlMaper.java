package com.pe.ventas.back.daos.sql.mapeos.movimientoproducto;

import java.util.List;
import org.mapstruct.Mapper;
import com.pe.ventas.back.dtos.daos.sql.movimientoproducto.MovimientoProductoSqlDto;

@Mapper
public interface MovimientoProductoSqlMaper {
	
	public List<MovimientoProductoSqlDto> selectTodosMovimientoProducto(); 
	public MovimientoProductoSqlDto selectUnMovimientoProducto(MovimientoProductoSqlDto movimientoProducto);
	public Integer insert (MovimientoProductoSqlDto movimientoProducto);
	public Integer update (MovimientoProductoSqlDto movimientoProducto);
	public Integer delete (MovimientoProductoSqlDto movimientoProducto);	

}
