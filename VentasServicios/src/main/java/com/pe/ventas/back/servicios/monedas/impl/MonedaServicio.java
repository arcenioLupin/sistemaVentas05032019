package com.pe.ventas.back.servicios.monedas.impl;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pe.ventas.back.daos.monedas.IMonedaDao;
import com.pe.ventas.back.dtos.daos.monedas.MonedaDaoDto;
import com.pe.ventas.back.dtos.servicios.monedas.MonedaServicioDto;
import com.pe.ventas.back.servicios.monedas.IMonedaServicio;
import com.pe.ventas.back.utilidades.mapeos.monedas.MonedaDtoMaper;

@Service("monedaServicio")
public class MonedaServicio implements IMonedaServicio {
	
	private static final Logger LOG = LogManager.getLogger(MonedaServicio.class);
	
	@Autowired
	IMonedaDao monedaDao;

	@Override
	public List<MonedaServicioDto> obtenerTodasLasMonedas() {
		final List<MonedaDaoDto> listaMonedasDao = monedaDao.obtenerTodasMonedas();
		
		if(listaMonedasDao.size() >0) {
			return   MonedaDtoMaper.INSTANCE.convertirListaMonedaDaoDtoAMonedaServicioDto(listaMonedasDao);
		}
		return null;
	}

	@Override
	public MonedaServicioDto obtenerUnaMoneda(MonedaServicioDto moneda) {
		MonedaDaoDto monedaDaoDto = MonedaDtoMaper.INSTANCE.monedaServicioDtoAmonedaDaoDto(moneda);
		monedaDaoDto = monedaDao.obtenerUnaMoneda(monedaDaoDto);
		MonedaServicioDto monedaServicioDto= MonedaDtoMaper.INSTANCE.monedaDaoDtoAmonedaServicioDto(monedaDaoDto);
		return monedaServicioDto;
	}

	@Override
	public Boolean crearMoneda(MonedaServicioDto moneda) {
		LOG.debug("Insertar MonedaServicioDto: "+moneda);
		MonedaDaoDto monedaDaoDto = MonedaDtoMaper.INSTANCE.monedaServicioDtoAmonedaDaoDto(moneda);
		monedaDaoDto = monedaDao.crearMoneda(monedaDaoDto);
		LOG.debug("Resultado monedaDaoDto: "+monedaDaoDto);
		
		if((monedaDaoDto !=null) && (monedaDaoDto.getCodigo() !=null))
		 {
			return true;
		 }
		return null;
	}

	@Override
	public Boolean actualizarMoneda(MonedaServicioDto moneda) {
		final MonedaDaoDto monedaDaoDto = MonedaDtoMaper.INSTANCE.monedaServicioDtoAmonedaDaoDto(moneda);
		return monedaDao.actualizarMoneda(monedaDaoDto);
	}

	@Override
	public Boolean eliminaMoneda(MonedaServicioDto moneda) {
		LOG.debug("Eliminar moneda: "+moneda);
		final MonedaDaoDto monedaDaoDto = MonedaDtoMaper.INSTANCE.monedaServicioDtoAmonedaDaoDto(moneda);
		return monedaDao.eliminaMoneda(monedaDaoDto);
	}

	@Override
	public void limpiarCache() {
		
		monedaDao.limpiarCache();
		
	}

}
