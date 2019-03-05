package com.pe.ventas.back.daos.tiposalmacen;

import java.util.List;
import com.pe.ventas.back.dtos.daos.tiposalmacen.TipoAlmacenDaoDto;

public interface ITipoAlmacenDao {
	
	public List<TipoAlmacenDaoDto> obtenerTodosTipoAlmacen();
	public TipoAlmacenDaoDto obtenerUnTipoAlmacen(TipoAlmacenDaoDto tipoAlmacen);
	public TipoAlmacenDaoDto crearTipoAlmacen(TipoAlmacenDaoDto tipoAlmacen);
	public Boolean actualizarTipoAlmacen (TipoAlmacenDaoDto tipoAlmacen);
	public Boolean eliminaTipoAlmacen(TipoAlmacenDaoDto tipoAlmacen);
	void limpiarCache();


}
