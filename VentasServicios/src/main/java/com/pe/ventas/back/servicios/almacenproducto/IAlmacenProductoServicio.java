package com.pe.ventas.back.servicios.almacenproducto;

import java.util.List;

import com.pe.ventas.back.dtos.servicios.almacenproducto.AlmacenProductoServicioDto;

public interface IAlmacenProductoServicio {
	
	List<AlmacenProductoServicioDto> obtenerTodosAlmacenesProductos();
	AlmacenProductoServicioDto obtenerUnAlmacenProducto(AlmacenProductoServicioDto almacenproducto);
	Boolean crearAlmacenProducto(AlmacenProductoServicioDto almacenproducto);
	Boolean actualizarAlmacenProducto(AlmacenProductoServicioDto almacenproducto);
	Boolean eliminaAlmacenProducto(AlmacenProductoServicioDto almacenproducto);
	void limpiarCache();

}
