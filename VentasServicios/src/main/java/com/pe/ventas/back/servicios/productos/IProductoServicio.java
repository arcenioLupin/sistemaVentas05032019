package com.pe.ventas.back.servicios.productos;

import java.util.List;

import com.pe.ventas.back.dtos.servicios.productos.ProductoServicioDto;

public interface IProductoServicio {
	
	List<ProductoServicioDto> obtenerTodasLosProductos();
	ProductoServicioDto obtenerUnProducto(ProductoServicioDto producto);
	Boolean crearProducto(ProductoServicioDto producto);
	Boolean actualizarProducto(ProductoServicioDto producto);
	Boolean eliminaProducto(ProductoServicioDto producto);
	void limpiarCache();

}
