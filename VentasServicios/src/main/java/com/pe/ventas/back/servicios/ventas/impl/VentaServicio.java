package com.pe.ventas.back.servicios.ventas.impl;

import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pe.ventas.back.daos.ventas.IVentaDao;
import com.pe.ventas.back.dtos.daos.ventas.VentaDaoDto;
import com.pe.ventas.back.dtos.servicios.ventas.VentaServicioDto;
import com.pe.ventas.back.servicios.ventas.impl.VentaServicio;
import com.pe.ventas.back.servicios.ventas.IVentaServicio;
import com.pe.ventas.back.utilidades.mapeos.ventas.VentaDtoMaper;

@Service("ventaServicio")
public class VentaServicio implements IVentaServicio {

private static final Logger LOG = LogManager.getLogger(VentaServicio.class);
	
	@Autowired
	IVentaDao ventaDao;

	@Override
	public List<VentaServicioDto> obtenerTodasLasVentas() {
		final List<VentaDaoDto> listaVentasDao = ventaDao.obtenerTodosVentas();
		
		if(listaVentasDao.size() >0) {
			return   VentaDtoMaper.INSTANCE.convertirListaVentaDaoDtoAVentaServicioDto(listaVentasDao);
		}
		return null;
	}

	@Override
	public VentaServicioDto obtenerUnaVenta(VentaServicioDto venta) {
		VentaDaoDto ventaDaoDto = VentaDtoMaper.INSTANCE.VentaServicioDtoAVentaDaoDto(venta);
		ventaDaoDto = ventaDao.obtenerUnaVenta(ventaDaoDto);
		VentaServicioDto ventaServicioDto= VentaDtoMaper.INSTANCE.VentaDaoDtoAVentaServicioDto(ventaDaoDto);
		return ventaServicioDto;
	}

	@Override
	public Boolean crearVenta(VentaServicioDto venta) {
		LOG.debug("Insertar VentaServicioDto: "+venta);
		VentaDaoDto ventaDaoDto = VentaDtoMaper.INSTANCE.VentaServicioDtoAVentaDaoDto(venta);
		ventaDaoDto.setVentaFecre(new Date());
		ventaDaoDto = ventaDao.crearVenta(ventaDaoDto);
		LOG.debug("Resultado VentaDaoDto: "+ventaDaoDto);
		
		if((ventaDaoDto !=null) && (ventaDaoDto.getCodVenta() !=null)
				&& (ventaDaoDto.getCodVenta()>0))
		 {
			return true;
		 }
		return null;
	}

	@Override
	public Boolean actualizarVenta(VentaServicioDto venta) {
		final VentaDaoDto ventaDaoDto = VentaDtoMaper.INSTANCE.VentaServicioDtoAVentaDaoDto(venta);
		return ventaDao.actualizarVenta(ventaDaoDto);
	}

	@Override
	public Boolean eliminaVenta(VentaServicioDto venta) {
		LOG.debug("Eliminar Venta: "+venta);
		final VentaDaoDto ventaDaoDto = VentaDtoMaper.INSTANCE.VentaServicioDtoAVentaDaoDto(venta);
		return ventaDao.eliminaVenta(ventaDaoDto);
	}

	@Override
	public void limpiarCache() {
		ventaDao.limpiarCache();
		
	}

}
