package com.pe.ventas.back.daos.sql.mapeos.monedas;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pe.ventas.back.dtos.daos.sql.monedas.MonedaSqlDto;

@Mapper
public interface MonedaSqlMaper {
	
	public List<MonedaSqlDto> selectTodasMonedas(); 
	public MonedaSqlDto selectUnaMoneda(MonedaSqlDto moneda);
	public Integer insert (MonedaSqlDto moneda);
	public Integer update (MonedaSqlDto moneda);
	public Integer delete (MonedaSqlDto moneda);

}
