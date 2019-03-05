package com.pe.ventas.back.servicios.movimientoproducto;

import java.util.List;

import com.pe.ventas.back.dtos.servicios.movimientoproducto.MovimientoProductoServicioDto;

public interface IMovimientoProductoServicio {
	
	List<MovimientoProductoServicioDto> obtenerTodosMovimientosProductos();
	MovimientoProductoServicioDto obtenerUnMovimientoProducto(MovimientoProductoServicioDto movimientoProducto);
	Boolean crearMovimientoProducto(MovimientoProductoServicioDto movimientoProducto);
	Boolean actualizarMovimientoProducto(MovimientoProductoServicioDto movimientoProducto);
	Boolean eliminaMovimientoProducto(MovimientoProductoServicioDto movimientoProducto);
	void limpiarCache();


}
