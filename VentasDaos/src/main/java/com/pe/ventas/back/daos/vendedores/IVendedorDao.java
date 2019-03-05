package com.pe.ventas.back.daos.vendedores;

import java.util.List;
import com.pe.ventas.back.dtos.daos.vendedores.VendedorDaoDto;

public interface IVendedorDao {
	
	public List<VendedorDaoDto> obtenerTodosVendedores();
	public VendedorDaoDto obtenerUnVendedor(VendedorDaoDto vendedor);
	public VendedorDaoDto crearVendedor(VendedorDaoDto vendedor);
	public Boolean actualizarVendedor (VendedorDaoDto vendedor);
	public Boolean eliminaVendedor(VendedorDaoDto vendedor);
	void limpiarCache();

}
