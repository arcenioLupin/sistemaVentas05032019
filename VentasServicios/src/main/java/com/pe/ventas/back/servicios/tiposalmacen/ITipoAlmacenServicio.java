package com.pe.ventas.back.servicios.tiposalmacen;

import java.util.List;
import com.pe.ventas.back.dtos.servicios.tiposalmacen.TipoAlmacenServicioDto;

public interface ITipoAlmacenServicio {
	
	List<TipoAlmacenServicioDto> obtenerTodasLosTiposAlmacen();
	TipoAlmacenServicioDto obtenerUnTipoAlmacen(TipoAlmacenServicioDto tipoAlmacen);
	Boolean crearTipoAlmacen(TipoAlmacenServicioDto categoria);
	Boolean actualizarTipoAlmacen(TipoAlmacenServicioDto categoria);
	Boolean eliminaTipoAlmacen(TipoAlmacenServicioDto categoria);
	void limpiarCache();

}
