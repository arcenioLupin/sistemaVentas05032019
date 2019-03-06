package com.pe.ventas.back.daos.ventadetalle;

import java.util.List;

import com.pe.ventas.back.dtos.daos.ventadetalle.VentaDetalleDaoDto;

public interface IVentaDetalleDao {
	
	public List<VentaDetalleDaoDto> obtenerTodosVentaDetalles();
	public VentaDetalleDaoDto obtenerUnVentaDetalle(VentaDetalleDaoDto ventaDetalle);
	public VentaDetalleDaoDto crearVentaDetalle(VentaDetalleDaoDto ventaDetalle);
	public Boolean actualizarVentaDetalle (VentaDetalleDaoDto ventaDetalle);
	public Boolean eliminaVentaDetalle(VentaDetalleDaoDto ventaDetalle);
	void limpiarCache();	
	

}
