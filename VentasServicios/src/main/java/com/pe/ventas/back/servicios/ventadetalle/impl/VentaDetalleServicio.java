package com.pe.ventas.back.servicios.ventadetalle.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pe.ventas.back.daos.ventadetalle.IVentaDetalleDao;
import com.pe.ventas.back.dtos.daos.ventadetalle.VentaDetalleDaoDto;
import com.pe.ventas.back.dtos.servicios.ventadetalle.VentaDetalleServicioDto;
import com.pe.ventas.back.servicios.ventadetalle.impl.VentaDetalleServicio;
import com.pe.ventas.back.servicios.ventadetalle.IVentaDetalleServicio;
import com.pe.ventas.back.utilidades.mapeos.ventadetalle.VentaDetalleDtoMaper;

@Service("ventaDetalleServicio")
public class VentaDetalleServicio implements IVentaDetalleServicio {

private static final Logger LOG = LogManager.getLogger(VentaDetalleServicio.class);
	
	@Autowired
	IVentaDetalleDao ventaDetalleDao;

	@Override
	public List<VentaDetalleServicioDto> obtenerTodosVentaDetalle() {
		final List<VentaDetalleDaoDto> listaventaDetalleDao = ventaDetalleDao.obtenerTodosVentaDetalles();
		
		if(listaventaDetalleDao.size() >0) {
			return   VentaDetalleDtoMaper.INSTANCE.convertirListaVentaDetalleDaoDtoAVentaDetalleServicioDto(listaventaDetalleDao);
		}
		return null;
	}

	@Override
	public VentaDetalleServicioDto obtenerUnaVentaDetalle(VentaDetalleServicioDto ventaDetalle) {
		VentaDetalleDaoDto ventaDetalleDaoDto = VentaDetalleDtoMaper.INSTANCE.VentaDetalleServicioDtoAVentaDetalleDaoDto(ventaDetalle);
		ventaDetalleDaoDto = ventaDetalleDao.obtenerUnVentaDetalle(ventaDetalleDaoDto);
		VentaDetalleServicioDto ventaDetalleServicioDto= VentaDetalleDtoMaper.INSTANCE.VentaDetalleDaoDtoAVentaDetalleServicioDto(ventaDetalleDaoDto);
		return ventaDetalleServicioDto;
	}

	@Override
	public Boolean crearVentaDetalle(VentaDetalleServicioDto ventaDetalle) {
		LOG.debug("Insertar VentaDetalleServicioDto: "+ventaDetalle);
		VentaDetalleDaoDto ventaDetalleDaoDto = VentaDetalleDtoMaper.INSTANCE.VentaDetalleServicioDtoAVentaDetalleDaoDto(ventaDetalle);
		ventaDetalleDaoDto = ventaDetalleDao.crearVentaDetalle(ventaDetalleDaoDto);
		LOG.debug("Resultado ventaDetalleDao: "+ventaDetalleDaoDto);
		
		if((ventaDetalleDaoDto !=null) && (ventaDetalleDaoDto.getCodVenta() !=null)
				&& (ventaDetalleDaoDto.getCodVenta()>0))
		 {
			return true;
		 }
		return null;
	}

	@Override
	public Boolean actualizarVentaDetalle(VentaDetalleServicioDto ventaDetalle) {
		final VentaDetalleDaoDto ventaDetalleDaoDto = VentaDetalleDtoMaper.INSTANCE.VentaDetalleServicioDtoAVentaDetalleDaoDto(ventaDetalle);
		return ventaDetalleDao.actualizarVentaDetalle(ventaDetalleDaoDto);
	}

	@Override
	public Boolean eliminaVentaDetalle(VentaDetalleServicioDto ventaDetalle) {
		LOG.debug("Eliminar venta - detalle: "+ventaDetalle);
		final VentaDetalleDaoDto ventaDetalleDaoDto = VentaDetalleDtoMaper.INSTANCE.VentaDetalleServicioDtoAVentaDetalleDaoDto(ventaDetalle);
		return ventaDetalleDao.eliminaVentaDetalle(ventaDetalleDaoDto);
	}

	@Override
	public void limpiarCache() {
		
		ventaDetalleDao.limpiarCache();
		
	}

}
