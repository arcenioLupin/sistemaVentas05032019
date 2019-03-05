package com.pe.ventas.back.servicios.movimientos.impl;

import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pe.ventas.back.daos.movimientos.IMovimientoDao;
import com.pe.ventas.back.dtos.daos.movimientos.MovimientoDaoDto;
import com.pe.ventas.back.dtos.servicios.movimientos.MovimientoServicioDto;
import com.pe.ventas.back.servicios.movimientos.IMovimientoServicio;
import com.pe.ventas.back.utilidades.mapeos.movimientos.MovimientoDtoMaper;

@Service("movimientoServicio")
public class MovimientoServicio implements IMovimientoServicio {
	
	private static final Logger LOG = LogManager.getLogger(MovimientoServicio.class);
	
	@Autowired
	IMovimientoDao movimientoDao;

	@Override
	public List<MovimientoServicioDto> obtenerTodosLosMovimientos() {
		final List<MovimientoDaoDto> listaMovimientosDao = movimientoDao.obtenerTodosMovimientos();
		
		if(listaMovimientosDao.size() >0) {
			return   MovimientoDtoMaper.INSTANCE.convertirListaMovimientoDaoDtoAMovimientoServicioDto(listaMovimientosDao);
		}
		return null;
	}

	@Override
	public MovimientoServicioDto obtenerUnMovimiento(MovimientoServicioDto movimiento) {
		  MovimientoDaoDto movimientoDaoDto = MovimientoDtoMaper.INSTANCE.movimientoServicioDtoAmovimientoDaoDto(movimiento);
		  movimientoDaoDto = movimientoDao.obtenerUnMovimiento(movimientoDaoDto);
		  MovimientoServicioDto movimientoServicioDto= MovimientoDtoMaper.INSTANCE.movimientoDaoDtoAMovimientoServicioDto(movimientoDaoDto);
		return movimientoServicioDto;
	}

	@Override
	public Boolean crearMovimiento(MovimientoServicioDto movimiento) {
		LOG.debug("Insertar MovimientoServicioDto: "+movimiento);
		MovimientoDaoDto movimientoDaoDto = MovimientoDtoMaper.INSTANCE.movimientoServicioDtoAmovimientoDaoDto(movimiento);
		movimientoDaoDto.setMovimientoFecre(new Date());
		movimientoDaoDto = movimientoDao.crearMovimiento(movimientoDaoDto);
		LOG.debug("Resultado movimientoDaoDto: "+movimientoDaoDto);
		
		if((movimientoDaoDto !=null) && (movimientoDaoDto.getCodigo() !=null)
				&& (movimientoDaoDto.getCodigo()>0))
		 {
			return true;
		 }
		return null;
	}

	@Override
	public Boolean actualizarMovimiento(MovimientoServicioDto movimiento) {
		final MovimientoDaoDto movimientoDaoDto = MovimientoDtoMaper.INSTANCE.movimientoServicioDtoAmovimientoDaoDto(movimiento);
		return movimientoDao.actualizarMovimiento(movimientoDaoDto);
	}

	@Override
	public Boolean eliminaMovimiento(MovimientoServicioDto movimiento) {
		LOG.debug("Eliminar movimiento: "+movimiento);
		final  MovimientoDaoDto movimientoDaoDto = MovimientoDtoMaper.INSTANCE.movimientoServicioDtoAmovimientoDaoDto(movimiento);
		return movimientoDao.eliminaMovimiento(movimientoDaoDto);
	}

	@Override
	public void limpiarCache() {		
		movimientoDao.limpiarCache();		
	}

}
