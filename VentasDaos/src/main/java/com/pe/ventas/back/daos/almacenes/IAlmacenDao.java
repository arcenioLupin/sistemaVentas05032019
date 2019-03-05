package com.pe.ventas.back.daos.almacenes;

import java.util.List;
import com.pe.ventas.back.dtos.daos.almacenes.AlmacenDaoDto;


public interface IAlmacenDao {
	
	public List<AlmacenDaoDto> obtenerTodosAlmacenes();
	public AlmacenDaoDto obtenerUnAlmacen(AlmacenDaoDto almacen);
	public AlmacenDaoDto crearAlmacen(AlmacenDaoDto almacen);
	public Boolean actualizarAlmacen(AlmacenDaoDto almacen);
	public Boolean eliminaAlmacen(AlmacenDaoDto almacen);
	void limpiarCache();


}
