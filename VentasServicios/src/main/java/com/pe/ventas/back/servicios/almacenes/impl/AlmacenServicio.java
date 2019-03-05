package com.pe.ventas.back.servicios.almacenes.impl;

import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pe.ventas.back.daos.almacenes.IAlmacenDao;
import com.pe.ventas.back.dtos.daos.almacenes.AlmacenDaoDto;
import com.pe.ventas.back.dtos.servicios.almacenes.AlmacenServicioDto;
import com.pe.ventas.back.servicios.almacenes.IAlmacenServicio;
import com.pe.ventas.back.utilidades.mapeos.almacenes.AlmacenDtoMaper;

@Service("almacenServicio")
public class AlmacenServicio implements IAlmacenServicio {

	
	private static final Logger LOG = LogManager.getLogger(AlmacenServicio.class);
	
	@Autowired
	IAlmacenDao almacenDao;
	
	@Override
	public List<AlmacenServicioDto> obtenerTodosLosAlmacenes() {
		final List<AlmacenDaoDto> listaAlmacenDao = almacenDao.obtenerTodosAlmacenes();
		
		if(listaAlmacenDao.size() >0) {
			return   AlmacenDtoMaper.INSTANCE.convertirListaAlmacenDaoDtoAAlmacenServicioDto(listaAlmacenDao);
		}
		return null;
	}

	@Override
	public AlmacenServicioDto obtenerUnAlmacen(AlmacenServicioDto almacen) {
		AlmacenDaoDto almacenDaoDto = AlmacenDtoMaper.INSTANCE.almacenServicioDtoAalmacenDaoDto(almacen);
		  almacenDaoDto = almacenDao.obtenerUnAlmacen(almacenDaoDto);
		  AlmacenServicioDto almacenServicioDto= AlmacenDtoMaper.INSTANCE.almacenDaoDtoAalmacenServicioDto(almacenDaoDto);
		return almacenServicioDto;
	}

	@Override
	public Boolean crearAlmacen(AlmacenServicioDto almacen) {
		LOG.debug("Insertar AlmacenServicioDto: "+almacen);
		AlmacenDaoDto almacenDaoDto = AlmacenDtoMaper.INSTANCE.almacenServicioDtoAalmacenDaoDto(almacen);
		almacenDaoDto.setAlmacenFecre(new Date());
		almacenDaoDto = almacenDao.crearAlmacen(almacenDaoDto);
		LOG.debug("Resultado almacenDaoDto: "+almacenDaoDto);
		
		if((almacenDaoDto !=null) && (almacenDaoDto.getCodigo() !=null)
				&& (almacenDaoDto.getCodigo()>0))
		 {
			return true;
		 }
		return null;
	}

	@Override
	public Boolean actualizarAlmacen(AlmacenServicioDto almacen) {
		final AlmacenDaoDto almacenDaoDto = AlmacenDtoMaper.INSTANCE.almacenServicioDtoAalmacenDaoDto(almacen);
		return almacenDao.actualizarAlmacen(almacenDaoDto);
	}

	@Override
	public Boolean eliminaAlmacen(AlmacenServicioDto almacen) {
		LOG.debug("Eliminar almacen: "+almacen);
		final  AlmacenDaoDto almacenDaoDto = AlmacenDtoMaper.INSTANCE.almacenServicioDtoAalmacenDaoDto(almacen);
		return almacenDao.eliminaAlmacen(almacenDaoDto);
	}

	@Override
	public void limpiarCache() {
		almacenDao.limpiarCache();
		
	}

}
