package com.pe.ventas.back.servicios.movimientoproducto.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pe.ventas.back.daos.movimientoproducto.IMovimientoProductoDao;
import com.pe.ventas.back.dtos.daos.movimientoproducto.MovimientoProductoDaoDto;
import com.pe.ventas.back.dtos.servicios.movimientoproducto.MovimientoProductoServicioDto;
import com.pe.ventas.back.servicios.movimientoproducto.impl.MovimientoProductoServicio;
import com.pe.ventas.back.servicios.movimientoproducto.IMovimientoProductoServicio;
import com.pe.ventas.back.utilidades.mapeos.movimientoproducto.MovimientoProductoDtoMaper;

@Service("movimientoProductoServicio")
public class MovimientoProductoServicio implements IMovimientoProductoServicio {
	
private static final Logger LOG = LogManager.getLogger(MovimientoProductoServicio.class);
	
	@Autowired
	IMovimientoProductoDao movimientoProductoDao;

	@Override
	public List<MovimientoProductoServicioDto> obtenerTodosMovimientosProductos() {
		final List<MovimientoProductoDaoDto> listaMovimientoProductoDao = movimientoProductoDao.obtenerTodosMovimientoProductos();
		
		if(listaMovimientoProductoDao.size() >0) {
			return   MovimientoProductoDtoMaper.INSTANCE.convertirListaMovimientoProductoDaoDtoAMovimientoProductoServicioDto(listaMovimientoProductoDao);
		}
		return null;
	}

	@Override
	public MovimientoProductoServicioDto obtenerUnMovimientoProducto(MovimientoProductoServicioDto movimientoProducto) {
		MovimientoProductoDaoDto movimientoProductoDaoDto = MovimientoProductoDtoMaper.INSTANCE.MovimientoProductoServicioDtoAMovimientoProductoDaoDto(movimientoProducto);
		movimientoProductoDaoDto = movimientoProductoDao.obtenerUnMovimientoProducto(movimientoProductoDaoDto);
		MovimientoProductoServicioDto movimientoProductoServicioDto= MovimientoProductoDtoMaper.INSTANCE.MovimientoProductoDaoDtoAMovimientoProductoServicioDto(movimientoProductoDaoDto);
		return movimientoProductoServicioDto;
	}

	@Override
	public Boolean crearMovimientoProducto(MovimientoProductoServicioDto movimientoProducto) {
		LOG.debug("Insertar MovimientoProductoServicioDto: "+movimientoProducto);
		MovimientoProductoDaoDto movimientoProductoDaoDto = MovimientoProductoDtoMaper.INSTANCE.MovimientoProductoServicioDtoAMovimientoProductoDaoDto(movimientoProducto);
		movimientoProductoDaoDto = movimientoProductoDao.crearMovimientoProducto(movimientoProductoDaoDto);
		LOG.debug("Resultado MovimientoProductoDao: "+movimientoProductoDaoDto);
		
		if((movimientoProductoDaoDto !=null) && (movimientoProductoDaoDto.getCodMovimiento() !=null)
				&& (movimientoProductoDaoDto.getCodMovimiento()>0))
		 {
			return true;
		 }
		return null;
	}

	@Override
	public Boolean actualizarMovimientoProducto(MovimientoProductoServicioDto movimientoProducto) {
		final MovimientoProductoDaoDto movimientoProductoDaoDto = MovimientoProductoDtoMaper.INSTANCE.MovimientoProductoServicioDtoAMovimientoProductoDaoDto(movimientoProducto);
		return movimientoProductoDao.actualizarMovimientoProducto(movimientoProductoDaoDto);
	}

	@Override
	public Boolean eliminaMovimientoProducto(MovimientoProductoServicioDto movimientoProducto) {
		LOG.debug("Eliminar Almacen-Producto: "+movimientoProducto);
		final MovimientoProductoDaoDto movimientoProductoDaoDto = MovimientoProductoDtoMaper.INSTANCE.MovimientoProductoServicioDtoAMovimientoProductoDaoDto(movimientoProducto);
		return movimientoProductoDao.eliminaMovimientoProducto(movimientoProductoDaoDto);
	}

	@Override
	public void limpiarCache() {
		
		movimientoProductoDao.limpiarCache();
		
	}


}
