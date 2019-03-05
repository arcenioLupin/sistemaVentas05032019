package com.pe.ventas.back.daos.sql.mapeos.movimientos;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pe.ventas.back.dtos.daos.sql.movimientos.MovimientoSqlDto;


@Mapper
public interface MovimientoSqlMaper {
	public List<MovimientoSqlDto> selectTodosMovimientos(); 
	public MovimientoSqlDto selectUnMovimiento(MovimientoSqlDto movimiento);
	public Integer insert (MovimientoSqlDto movimiento);
	public Integer update (MovimientoSqlDto movimiento);
	public Integer delete (MovimientoSqlDto movimiento);

}
