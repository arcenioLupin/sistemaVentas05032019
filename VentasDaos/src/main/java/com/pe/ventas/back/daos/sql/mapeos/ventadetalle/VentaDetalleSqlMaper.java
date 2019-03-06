package com.pe.ventas.back.daos.sql.mapeos.ventadetalle;

import java.util.List;
import org.mapstruct.Mapper;
import com.pe.ventas.back.dtos.daos.sql.ventadetalle.VentaDetalleSqlDto;

@Mapper
public interface VentaDetalleSqlMaper {
	
	public List<VentaDetalleSqlDto> selectTodosVentaDetalle(); 
	public VentaDetalleSqlDto selectUnVentaDetalle(VentaDetalleSqlDto ventaDetalle);
	public Integer insert (VentaDetalleSqlDto ventaDetalle);
	public Integer update (VentaDetalleSqlDto ventaDetalle);
	public Integer delete (VentaDetalleSqlDto ventaDetalle);

}
