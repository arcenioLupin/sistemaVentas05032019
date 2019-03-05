package com.pe.ventas.back.daos.movimientoproducto;

import java.util.List;

import com.pe.ventas.back.dtos.daos.movimientoproducto.MovimientoProductoDaoDto;

public interface IMovimientoProductoDao {
	
	public List<MovimientoProductoDaoDto> obtenerTodosMovimientoProductos();
	public MovimientoProductoDaoDto obtenerUnMovimientoProducto(MovimientoProductoDaoDto movimientoProducto);
	public MovimientoProductoDaoDto crearMovimientoProducto(MovimientoProductoDaoDto movimientoProducto);
	public Boolean actualizarMovimientoProducto (MovimientoProductoDaoDto movimientoProducto);
	public Boolean eliminaMovimientoProducto(MovimientoProductoDaoDto movimientoProducto);
	void limpiarCache();

}
