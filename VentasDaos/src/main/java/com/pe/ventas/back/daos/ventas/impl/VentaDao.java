package com.pe.ventas.back.daos.ventas.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.pe.ventas.back.daos.ventas.IVentaDao;
import com.pe.ventas.back.dtos.daos.ventas.VentaDaoDto;

@Repository("ventaDao")
public class VentaDao implements IVentaDao {

	@Override
	public List<VentaDaoDto> obtenerTodosVentas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VentaDaoDto obtenerUnaVenta(VentaDaoDto venta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VentaDaoDto crearVenta(VentaDaoDto venta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean actualizarVenta(VentaDaoDto venta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean eliminaVenta(VentaDaoDto venta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void limpiarCache() {
		// TODO Auto-generated method stub
		
	}

}
