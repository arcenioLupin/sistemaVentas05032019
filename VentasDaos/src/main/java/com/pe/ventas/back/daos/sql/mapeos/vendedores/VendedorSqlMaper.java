package com.pe.ventas.back.daos.sql.mapeos.vendedores;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pe.ventas.back.dtos.daos.sql.vendedores.VendedorSqlDto;

@Mapper
public interface VendedorSqlMaper {
	
	public List<VendedorSqlDto> selectTodosVendedores(); 
	public VendedorSqlDto selectUnVendedor(VendedorSqlDto vendedor);
	public Integer insert (VendedorSqlDto vendedor);
	public Integer update (VendedorSqlDto vendedor);
	public Integer delete (VendedorSqlDto vendedor);

}
