package com.pe.ventas.back.servicios.ventas;

import java.util.List;

import com.pe.ventas.back.dtos.servicios.ventas.VentaServicioDto;

public interface IVentaServicio {
	
	List<VentaServicioDto> obtenerTodasLasVentas();
	VentaServicioDto obtenerUnaVenta(VentaServicioDto venta);
	Boolean crearVenta(VentaServicioDto venta);
	Boolean actualizarVenta(VentaServicioDto venta);
	Boolean eliminaVenta(VentaServicioDto venta);
	void limpiarCache();

}
