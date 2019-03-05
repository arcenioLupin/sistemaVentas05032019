package com.pe.ventas.back.servicios.unidadmedida.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pe.ventas.back.daos.unidadmedida.IUnidadMedidaDao;
import com.pe.ventas.back.dtos.daos.unidadmedida.UnidadMedidaDaoDto;
import com.pe.ventas.back.dtos.servicios.unidadmedida.UnidadMedidaServicioDto;
import com.pe.ventas.back.servicios.unidadmedida.IUnidadMedidaServicio;
import com.pe.ventas.back.utilidades.mapeos.unidadmedida.UnidadMedidaDtoMaper;


@Service("unidadMedidaServicio")
public class UnidadMedidaServicio implements IUnidadMedidaServicio {
	
	private static final Logger LOG = LogManager.getLogger(UnidadMedidaServicio.class);
	
	@Autowired
	IUnidadMedidaDao iUnidadMedidaDao;

	@Override
	public List<UnidadMedidaServicioDto> obtenerTodasLasUnidadMedida() {
		final List<UnidadMedidaDaoDto> listaUnidadMedidaDao = iUnidadMedidaDao.obtenerTodasUnidadMedida();
		
		if(listaUnidadMedidaDao.size() >0) {
			return   UnidadMedidaDtoMaper.INSTANCE.convertirListaunidadMedidaDaoDtoAunidadMedidaServicioDto(listaUnidadMedidaDao);
		}
		return null;
	}

	@Override
	public UnidadMedidaServicioDto obtenerUnaUnidadMedida(UnidadMedidaServicioDto unidadMedida) {
		UnidadMedidaDaoDto unidadMedidaDaoDto = UnidadMedidaDtoMaper.INSTANCE.unidadMedidaServicioDtoAunidadMedidaDaoDto(unidadMedida);
		unidadMedidaDaoDto = iUnidadMedidaDao.obtenerUnaUnidadMedida(unidadMedidaDaoDto);
		UnidadMedidaServicioDto unidadMedidaServicioDto= UnidadMedidaDtoMaper.INSTANCE.unidadMedidaDaoDtoAunidadMedidaServicioDto(unidadMedidaDaoDto);
		return unidadMedidaServicioDto;
	}

	@Override
	public Boolean crearUnidadMedida(UnidadMedidaServicioDto unidadMedida) {
		LOG.debug("Insertar UnidadMedidaServicioDto: "+unidadMedida);
		UnidadMedidaDaoDto unidadMedidaDaoDto = UnidadMedidaDtoMaper.INSTANCE.unidadMedidaServicioDtoAunidadMedidaDaoDto(unidadMedida);
		unidadMedidaDaoDto = iUnidadMedidaDao.crearUnidadMedida(unidadMedidaDaoDto);
		LOG.debug("Resultado unidadMedidaDaoDto: "+unidadMedidaDaoDto);
		
		if((unidadMedidaDaoDto !=null) && (unidadMedidaDaoDto.getCodigo() !=null))
		 {
			return true;
		 }
		return null;
	}

	@Override
	public Boolean actualizarUnidadMedida(UnidadMedidaServicioDto unidadMedida) {
		final UnidadMedidaDaoDto unidadMedidaDaoDto = UnidadMedidaDtoMaper.INSTANCE.unidadMedidaServicioDtoAunidadMedidaDaoDto(unidadMedida);
		return iUnidadMedidaDao.actualizarUnidadMedida(unidadMedidaDaoDto);
	}

	@Override
	public Boolean eliminaUnidadMedida(UnidadMedidaServicioDto unidadMedida) {
		LOG.debug("Eliminar unidadmedida: "+unidadMedida);
		final UnidadMedidaDaoDto unidadMedidaDaoDto = UnidadMedidaDtoMaper.INSTANCE.unidadMedidaServicioDtoAunidadMedidaDaoDto(unidadMedida);
		return iUnidadMedidaDao.eliminaUnidadMedida(unidadMedidaDaoDto);
	}

	@Override
	public void limpiarCache() {
		iUnidadMedidaDao.limpiarCache();
		
	}

}
