package com.pe.ventas.back.servicios.vendedores;

import java.util.List;
import com.pe.ventas.back.dtos.servicios.vendedores.VendedorServicioDto;

public interface IVendedorServicio {
	
	List<VendedorServicioDto> obtenerTodosLosVendedores();
	VendedorServicioDto obtenerUnVendedor(VendedorServicioDto vendedor);
	Boolean crearVendedor(VendedorServicioDto vendedor);
	Boolean actualizarVendedor(VendedorServicioDto vendedor);
	Boolean eliminaVendedor(VendedorServicioDto vendedor);
	void limpiarCache();

}
