package com.pe.ventas.back.servicios.movimientos;

import java.util.List;
import com.pe.ventas.back.dtos.servicios.movimientos.MovimientoServicioDto;


public interface IMovimientoServicio {
	
	List<MovimientoServicioDto> obtenerTodosLosMovimientos();
	MovimientoServicioDto obtenerUnMovimiento(MovimientoServicioDto movimiento);
	Boolean crearMovimiento(MovimientoServicioDto movimiento);
	Boolean actualizarMovimiento(MovimientoServicioDto movimiento);
	Boolean eliminaMovimiento(MovimientoServicioDto movimiento);
	void limpiarCache();

}
