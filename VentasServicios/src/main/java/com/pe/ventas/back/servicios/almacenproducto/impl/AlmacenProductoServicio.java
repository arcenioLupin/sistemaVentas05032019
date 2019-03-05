package com.pe.ventas.back.servicios.almacenproducto.impl;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pe.ventas.back.daos.almacenproducto.IAlmacenProductoDao;
import com.pe.ventas.back.dtos.daos.almacenproducto.AlmacenProductoDaoDto;
import com.pe.ventas.back.dtos.servicios.almacenproducto.AlmacenProductoServicioDto;
import com.pe.ventas.back.servicios.almacenproducto.IAlmacenProductoServicio;
import com.pe.ventas.back.utilidades.mapeos.almacenproducto.AlmacenProductoDtoMaper;

@Service("almacenProductoServicio")
public class AlmacenProductoServicio implements IAlmacenProductoServicio {
	
	private static final Logger LOG = LogManager.getLogger(AlmacenProductoServicio.class);
	
	@Autowired
	IAlmacenProductoDao almacenProductoDao;

	@Override
	public List<AlmacenProductoServicioDto> obtenerTodosAlmacenesProductos() {
		final List<AlmacenProductoDaoDto> listaAlmacenProductoDao = almacenProductoDao.obtenerTodosAlmacenProductos();
		
		if(listaAlmacenProductoDao.size() >0) {
			return   AlmacenProductoDtoMaper.INSTANCE.convertirListaAlmacenProductoDaoDtoAAlmacenProductoServicioDto(listaAlmacenProductoDao);
		}
		return null;
	}

	@Override
	public AlmacenProductoServicioDto obtenerUnAlmacenProducto(AlmacenProductoServicioDto almacenproducto) {
		AlmacenProductoDaoDto almacenProductoDaoDto = AlmacenProductoDtoMaper.INSTANCE.almacenProductoServicioDtoAalmacenProductoDaoDto(almacenproducto);
		almacenProductoDaoDto = almacenProductoDao.obtenerUnAlmacenProducto(almacenProductoDaoDto);
		AlmacenProductoServicioDto almacenProductoServicioDto= AlmacenProductoDtoMaper.INSTANCE.almacenProductoDaoDtoAalmacenProductoServicioDto(almacenProductoDaoDto);
		return almacenProductoServicioDto;
	}

	@Override
	public Boolean crearAlmacenProducto(AlmacenProductoServicioDto almacenproducto) {
		LOG.debug("Insertar AlmacenProductoServicioDto: "+almacenproducto);
		AlmacenProductoDaoDto almacenProductoDaoDto = AlmacenProductoDtoMaper.INSTANCE.almacenProductoServicioDtoAalmacenProductoDaoDto(almacenproducto);
		almacenProductoDaoDto = almacenProductoDao.crearAlmacenProducto(almacenProductoDaoDto);
		LOG.debug("Resultado almacenProductoDao: "+almacenProductoDao);
		
		if((almacenProductoDaoDto !=null) && (almacenProductoDaoDto.getCodAlmacen() !=null)
				&& (almacenProductoDaoDto.getCodAlmacen()>0))
		 {
			return true;
		 }
		return null;
	}

	@Override
	public Boolean actualizarAlmacenProducto(AlmacenProductoServicioDto almacenproducto) {
		final AlmacenProductoDaoDto almacenProductoDaoDto = AlmacenProductoDtoMaper.INSTANCE.almacenProductoServicioDtoAalmacenProductoDaoDto(almacenproducto);
		return almacenProductoDao.actualizarAlmacenProducto(almacenProductoDaoDto);
	}

	@Override
	public Boolean eliminaAlmacenProducto(AlmacenProductoServicioDto almacenproducto) {
		LOG.debug("Eliminar Almacen-Producto: "+almacenproducto);
		final AlmacenProductoDaoDto almacenProductoDaoDto = AlmacenProductoDtoMaper.INSTANCE.almacenProductoServicioDtoAalmacenProductoDaoDto(almacenproducto);
		return almacenProductoDao.eliminaAlmacenProducto(almacenProductoDaoDto);
	}

	@Override
	public void limpiarCache() {
		
		almacenProductoDao.limpiarCache();
		
	}

}
