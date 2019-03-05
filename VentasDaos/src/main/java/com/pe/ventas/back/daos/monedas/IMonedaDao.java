package com.pe.ventas.back.daos.monedas;

import java.util.List;

import com.pe.ventas.back.dtos.daos.monedas.MonedaDaoDto;

public interface IMonedaDao {
	
	public List<MonedaDaoDto> obtenerTodasMonedas();
	public MonedaDaoDto obtenerUnaMoneda(MonedaDaoDto moneda);
	public MonedaDaoDto crearMoneda(MonedaDaoDto moneda);
	public Boolean actualizarMoneda (MonedaDaoDto moneda);
	public Boolean eliminaMoneda(MonedaDaoDto moneda);
	void limpiarCache();

}
