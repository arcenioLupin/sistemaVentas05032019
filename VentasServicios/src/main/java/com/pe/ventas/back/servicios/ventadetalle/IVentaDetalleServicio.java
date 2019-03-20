package com.pe.ventas.back.servicios.ventadetalle;

import java.util.List;

import com.pe.ventas.back.dtos.servicios.ventadetalle.VentaDetalleServicioDto;

public interface IVentaDetalleServicio {
	
	List<VentaDetalleServicioDto> obtenerTodosVentaDetalle();
	VentaDetalleServicioDto obtenerUnaVentaDetalle(VentaDetalleServicioDto VentaDetalle);
	Boolean crearVentaDetalle(VentaDetalleServicioDto VentaDetalle);
	Boolean actualizarVentaDetalle(VentaDetalleServicioDto VentaDetalle);
	Boolean eliminaVentaDetalle(VentaDetalleServicioDto VentaDetalle);
	void limpiarCache();	

}
