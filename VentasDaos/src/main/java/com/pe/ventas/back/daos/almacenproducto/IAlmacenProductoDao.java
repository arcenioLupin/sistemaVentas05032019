package com.pe.ventas.back.daos.almacenproducto;

import java.util.List;

import com.pe.ventas.back.dtos.daos.almacenproducto.AlmacenProductoDaoDto;

public interface IAlmacenProductoDao {
	
	public List<AlmacenProductoDaoDto> obtenerTodosAlmacenProductos();
	public AlmacenProductoDaoDto obtenerUnAlmacenProducto(AlmacenProductoDaoDto almacenProducto);
	public AlmacenProductoDaoDto crearAlmacenProducto(AlmacenProductoDaoDto almacenProducto);
	public Boolean actualizarAlmacenProducto (AlmacenProductoDaoDto almacenProducto);
	public Boolean eliminaAlmacenProducto(AlmacenProductoDaoDto almacenProducto);
	void limpiarCache();

}
