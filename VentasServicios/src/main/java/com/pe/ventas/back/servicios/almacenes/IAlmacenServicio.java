package com.pe.ventas.back.servicios.almacenes;

import java.util.List;
import com.pe.ventas.back.dtos.servicios.almacenes.AlmacenServicioDto;


public interface IAlmacenServicio {
	
	List<AlmacenServicioDto> obtenerTodosLosAlmacenes();
	AlmacenServicioDto obtenerUnAlmacen(AlmacenServicioDto almacen);
	Boolean crearAlmacen(AlmacenServicioDto almacen);
	Boolean actualizarAlmacen(AlmacenServicioDto almacen);
	Boolean eliminaAlmacen(AlmacenServicioDto almacen);
	void limpiarCache();	

}
