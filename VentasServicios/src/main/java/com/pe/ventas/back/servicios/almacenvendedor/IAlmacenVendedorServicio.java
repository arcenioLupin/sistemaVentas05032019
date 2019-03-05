package com.pe.ventas.back.servicios.almacenvendedor;

import java.util.List;

import com.pe.ventas.back.dtos.servicios.almacenvendedor.AlmacenVendedorServicioDto;

public interface IAlmacenVendedorServicio {
	
	List<AlmacenVendedorServicioDto> obtenerTodosAlmacenesVendedores();
	AlmacenVendedorServicioDto obtenerUnAlmacenVendedor(AlmacenVendedorServicioDto almacenVendedor);
	Boolean crearAlmacenVendedor(AlmacenVendedorServicioDto almacenVendedor);
	Boolean actualizarAlmacenVendedor(AlmacenVendedorServicioDto almacenVendedor);
	Boolean eliminaAlmacenVendedor(AlmacenVendedorServicioDto almacenVendedor);
	void limpiarCache();

}
