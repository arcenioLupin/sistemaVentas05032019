package com.pe.ventas.back.servicios.unidadmedida;

import java.util.List;
import com.pe.ventas.back.dtos.servicios.unidadmedida.UnidadMedidaServicioDto;

public interface IUnidadMedidaServicio {
	
	List<UnidadMedidaServicioDto> obtenerTodasLasUnidadMedida();
	UnidadMedidaServicioDto obtenerUnaUnidadMedida(UnidadMedidaServicioDto unidadMedida);
	Boolean crearUnidadMedida(UnidadMedidaServicioDto unidadMedida);
	Boolean actualizarUnidadMedida(UnidadMedidaServicioDto unidadMedida);
	Boolean eliminaUnidadMedida(UnidadMedidaServicioDto unidadMedida);
	void limpiarCache();

}
