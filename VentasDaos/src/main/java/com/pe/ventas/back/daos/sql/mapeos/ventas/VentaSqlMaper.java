package com.pe.ventas.back.daos.sql.mapeos.ventas;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pe.ventas.back.dtos.daos.sql.ventas.VentaSqlDto;

@Mapper
public interface VentaSqlMaper {
	
	public List<VentaSqlDto> selectTodosVentas(); 
	public VentaSqlDto selectUnaVenta(VentaSqlDto venta);
	public Integer insert (VentaSqlDto venta);
	public Integer update (VentaSqlDto venta);
	public Integer delete (VentaSqlDto venta);

}
