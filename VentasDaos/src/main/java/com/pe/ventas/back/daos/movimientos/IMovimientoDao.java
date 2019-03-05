package com.pe.ventas.back.daos.movimientos;

import java.util.List;
import com.pe.ventas.back.dtos.daos.movimientos.MovimientoDaoDto;


public interface IMovimientoDao {
	
	public List<MovimientoDaoDto> obtenerTodosMovimientos();
	public MovimientoDaoDto obtenerUnMovimiento(MovimientoDaoDto movimiento);
	public MovimientoDaoDto crearMovimiento(MovimientoDaoDto movimiento);
	public Boolean actualizarMovimiento (MovimientoDaoDto movimiento);
	public Boolean eliminaMovimiento(MovimientoDaoDto movimiento);
	void limpiarCache();

}
