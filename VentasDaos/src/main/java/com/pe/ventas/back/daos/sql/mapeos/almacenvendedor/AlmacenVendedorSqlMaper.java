package com.pe.ventas.back.daos.sql.mapeos.almacenvendedor;

import java.util.List;

import org.mapstruct.Mapper;

import com.pe.ventas.back.dtos.daos.sql.almacenvendedor.AlmacenVendedorSqlDto;

@Mapper
public interface AlmacenVendedorSqlMaper {
	
	public List<AlmacenVendedorSqlDto> selectTodosAlmacenVendedor(); 
	public AlmacenVendedorSqlDto selectUnAlmacenVendedor(AlmacenVendedorSqlDto almacenVendedor);
	public Integer insert (AlmacenVendedorSqlDto almacenVendedor);
	public Integer update (AlmacenVendedorSqlDto almacenVendedor);
	public Integer delete (AlmacenVendedorSqlDto almacenVendedor);

}
