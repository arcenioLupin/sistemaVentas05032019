package com.pe.ventas.back.daos.almacenvendedor;

import java.util.List;

import com.pe.ventas.back.dtos.daos.almacenvendedor.AlmacenVendedorDaoDto;

public interface IAlmacenVendedorDao {
	
	public List<AlmacenVendedorDaoDto> obtenerTodosAlmacenVendedores();
	public AlmacenVendedorDaoDto obtenerUnAlmacenVendedor(AlmacenVendedorDaoDto almacenVendedor);
	public AlmacenVendedorDaoDto crearAlmacenVendedor(AlmacenVendedorDaoDto almacenVendedor);
	public Boolean actualizarAlmacenVendedor (AlmacenVendedorDaoDto almacenVendedor);
	public Boolean eliminaAlmacenVendedor(AlmacenVendedorDaoDto almacenVendedor);
	void limpiarCache();


}
