package com.pe.ventas.back.servicios.tiposalmacen.impl;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pe.ventas.back.daos.tiposalmacen.ITipoAlmacenDao;
import com.pe.ventas.back.dtos.daos.tiposalmacen.TipoAlmacenDaoDto;
import com.pe.ventas.back.dtos.servicios.tiposalmacen.TipoAlmacenServicioDto;
import com.pe.ventas.back.servicios.tiposalmacen.ITipoAlmacenServicio;
import com.pe.ventas.back.utilidades.mapeos.tiposalmacen.TipoAlmacenDtoMaper;


@Service("tipoAlmacenServicio")
public class TipoAlmacenServicio implements ITipoAlmacenServicio {
	
	private static final Logger LOG = LogManager.getLogger(TipoAlmacenServicio.class);

	@Autowired
	ITipoAlmacenDao  tipoAlmacenDao;

	@Override
	public List<TipoAlmacenServicioDto> obtenerTodasLosTiposAlmacen() {
		final List<TipoAlmacenDaoDto> listaTiposAlmacenDao = tipoAlmacenDao.obtenerTodosTipoAlmacen();
		
		if(listaTiposAlmacenDao.size() >0) {
			return   TipoAlmacenDtoMaper.INSTANCE.convertirListaTipoAlmacenDaoDtoATipoAlmacenServicioDto(listaTiposAlmacenDao);
		}
		return null;
	}

	@Override
	public TipoAlmacenServicioDto obtenerUnTipoAlmacen(TipoAlmacenServicioDto tipoAlmacen) {
		  TipoAlmacenDaoDto tipoAlmacenDaoDto = TipoAlmacenDtoMaper.INSTANCE.tipoAlmacenServicioDtoAtipoAlmacenDaoDto(tipoAlmacen);
		  tipoAlmacenDaoDto = tipoAlmacenDao.obtenerUnTipoAlmacen(tipoAlmacenDaoDto);
		  TipoAlmacenServicioDto tipoAlmacenServicioDto= TipoAlmacenDtoMaper.INSTANCE.tipoAlmacenDaoDtoAtipoAlmacenServicioDto(tipoAlmacenDaoDto);
		return tipoAlmacenServicioDto;
	}

	@Override
	public Boolean crearTipoAlmacen(TipoAlmacenServicioDto tipoAlmacen) {
		LOG.debug("Insertar TipoAlmacenServicioDto: "+tipoAlmacen);
		TipoAlmacenDaoDto tipoAlmacenDaoDto = TipoAlmacenDtoMaper.INSTANCE.tipoAlmacenServicioDtoAtipoAlmacenDaoDto(tipoAlmacen);
		tipoAlmacenDaoDto.setTipoAlmacenFecre(new Date());
		tipoAlmacenDaoDto = tipoAlmacenDao.crearTipoAlmacen(tipoAlmacenDaoDto);
		LOG.debug("Resultado tipoAlmacenDaoDto: "+tipoAlmacenDaoDto);
		
		if((tipoAlmacenDaoDto !=null) && (tipoAlmacenDaoDto.getCodigo() !=null)
				&& (tipoAlmacenDaoDto.getCodigo()>0))
		 {
			return true;
		 }
		return null;
	}

	@Override
	public Boolean actualizarTipoAlmacen(TipoAlmacenServicioDto tipoAlmacen) {
		final  TipoAlmacenDaoDto tipoAlmacenDaoDto = TipoAlmacenDtoMaper.INSTANCE.tipoAlmacenServicioDtoAtipoAlmacenDaoDto(tipoAlmacen);
		return tipoAlmacenDao.actualizarTipoAlmacen(tipoAlmacenDaoDto);
	}

	@Override
	public Boolean eliminaTipoAlmacen(TipoAlmacenServicioDto tipoAlmacen) {
		LOG.debug("Eliminar tipo almacen: "+tipoAlmacen);
		final  TipoAlmacenDaoDto tipoAlmacenDaoDto = TipoAlmacenDtoMaper.INSTANCE.tipoAlmacenServicioDtoAtipoAlmacenDaoDto(tipoAlmacen);
		return tipoAlmacenDao.eliminaTipoAlmacen(tipoAlmacenDaoDto);
	}

	@Override
	public void limpiarCache() {
		
		tipoAlmacenDao.limpiarCache();
		
	}
	
	
}
