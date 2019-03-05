package com.pe.ventas.back.servicios.monedas;

import java.util.List;

import com.pe.ventas.back.dtos.servicios.monedas.MonedaServicioDto;

public interface IMonedaServicio {
	
	List<MonedaServicioDto> obtenerTodasLasMonedas();
	MonedaServicioDto obtenerUnaMoneda(MonedaServicioDto moneda);
	Boolean crearMoneda(MonedaServicioDto moneda);
	Boolean actualizarMoneda(MonedaServicioDto moneda);
	Boolean eliminaMoneda(MonedaServicioDto moneda);
	void limpiarCache();


}
