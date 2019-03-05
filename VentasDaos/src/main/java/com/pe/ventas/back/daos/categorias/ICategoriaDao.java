package com.pe.ventas.back.daos.categorias;

import java.util.List;

import com.pe.ventas.back.dtos.daos.categorias.CategoriaDaoDto;

public interface ICategoriaDao {
	
	public List<CategoriaDaoDto> obtenerTodasCategorias();
	public CategoriaDaoDto obtenerUnaCategoria(CategoriaDaoDto categoria);
	public CategoriaDaoDto crearCategoria(CategoriaDaoDto categoria);
	public Boolean actualizarCategoria (CategoriaDaoDto categoria);
	public Boolean eliminaCategoria(CategoriaDaoDto categoria);
	void limpiarCache();

}
 