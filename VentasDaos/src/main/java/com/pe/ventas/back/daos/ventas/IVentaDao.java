package com.pe.ventas.back.daos.ventas;

import java.util.List;

import com.pe.ventas.back.dtos.daos.ventas.VentaDaoDto;

public interface IVentaDao {
	
	public List<VentaDaoDto> obtenerTodosVentas();
	public VentaDaoDto obtenerUnaVenta(VentaDaoDto venta);
	public VentaDaoDto crearVenta(VentaDaoDto venta);
	public Boolean actualizarVenta (VentaDaoDto venta);
	public Boolean eliminaVenta(VentaDaoDto venta);
	void limpiarCache();

}
