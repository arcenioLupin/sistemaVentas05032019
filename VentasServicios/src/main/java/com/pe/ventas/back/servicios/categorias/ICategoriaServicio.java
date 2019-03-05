package com.pe.ventas.back.servicios.categorias;

import java.util.List;
import com.pe.ventas.back.dtos.servicios.categorias.CategoriaServicioDto;

public interface ICategoriaServicio {
	
	List<CategoriaServicioDto> obtenerTodasLasCategorias();
	CategoriaServicioDto obtenerUnaCategoria(CategoriaServicioDto categoria);
	Boolean crearCategoria(CategoriaServicioDto categoria);
	Boolean actualizarCategoria(CategoriaServicioDto categoria);
	Boolean eliminaCategoria(CategoriaServicioDto categoria);
	void limpiarCache();

}
