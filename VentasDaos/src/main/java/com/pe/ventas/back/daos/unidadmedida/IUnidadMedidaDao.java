package com.pe.ventas.back.daos.unidadmedida;

import java.util.List;
import com.pe.ventas.back.dtos.daos.unidadmedida.UnidadMedidaDaoDto;

public interface IUnidadMedidaDao {
	
	public List<UnidadMedidaDaoDto> obtenerTodasUnidadMedida();
	public UnidadMedidaDaoDto obtenerUnaUnidadMedida(UnidadMedidaDaoDto unidadMedida);
	public UnidadMedidaDaoDto crearUnidadMedida(UnidadMedidaDaoDto unidadMedida);
	public Boolean actualizarUnidadMedida (UnidadMedidaDaoDto unidadMedida);
	public Boolean eliminaUnidadMedida(UnidadMedidaDaoDto unidadMedida);
	void limpiarCache();

}
