package com.pe.ventas.back.daos.monedas.impl;

import java.util.List;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pe.ventas.back.daos.monedas.IMonedaDao;
import com.pe.ventas.back.daos.sql.mapeos.monedas.MonedaSqlMaper;
import com.pe.ventas.back.dtos.daos.monedas.MonedaDaoDto;
import com.pe.ventas.back.dtos.daos.sql.monedas.MonedaSqlDto;
import com.pe.ventas.back.utilidades.mapeos.monedas.MonedaDtoMaper;

@Repository("monedaDao")
public class MonedaDao implements IMonedaDao  {
	
	private static final Logger LOG = LogManager.getLogger(MonedaDao.class);
	
	@Autowired
	private MonedaSqlMaper monedaSqlMaper;
	

	@Override
    @Cacheable(value = "monedas")
    @Transactional(readOnly = true)
	public List<MonedaDaoDto> obtenerTodasMonedas() {
		final List<MonedaSqlDto> listaMonedas = monedaSqlMaper.selectTodasMonedas();
		return MonedaDtoMaper.INSTANCE.convertirListaMonedaSqlDtoAMonedaDaoDto(listaMonedas);
	}

	@Override
    @Cacheable(value = "monedas")
    @Transactional(readOnly = true)
	public MonedaDaoDto obtenerUnaMoneda(MonedaDaoDto moneda) {
		LOG.debug("Obteniendo obj MonedaDaoDto: "+moneda);
		MonedaSqlDto monedaSqlDto = MonedaDtoMaper.INSTANCE.monedaDaoDtoAmonedaSqlDto(moneda);
		monedaSqlDto = monedaSqlMaper.selectUnaMoneda(monedaSqlDto);
		return MonedaDtoMaper.INSTANCE.monedaSqlDtoAmonedaDaoDTo(monedaSqlDto);
	}

	@Override
    @CachePut(value = "monedas")
    @Transactional
	public MonedaDaoDto crearMoneda(MonedaDaoDto moneda) {
		LOG.debug("Obteniendo obj MonedaDaoDto en crearMoneda: "+moneda);
		MonedaSqlDto monedaSqlDto = MonedaDtoMaper.INSTANCE.monedaDaoDtoAmonedaSqlDto(moneda);
		final Integer resultado = monedaSqlMaper.insert(monedaSqlDto);       
		if ((resultado != null) && BooleanUtils.toBoolean(resultado)) {
            return MonedaDtoMaper.INSTANCE.monedaSqlDtoAmonedaDaoDTo(monedaSqlDto);
        }
			
		return null;
	}

	@Override
    @CachePut(value = "monedas")
    @Transactional
	public Boolean actualizarMoneda(MonedaDaoDto moneda) {
		LOG.debug("Obteniendo obj MonedaDaoDto en actualizarMoneda: "+moneda);
		MonedaSqlDto monedaSqlDto = MonedaDtoMaper.INSTANCE.monedaDaoDtoAmonedaSqlDto(moneda);
		final Integer resultado = monedaSqlMaper.update(monedaSqlDto);
		return (resultado !=null) && (resultado >0) ? true :false;
	}

	@Override
    @CacheEvict(value = "monedas", key = "#moneda.codigo")
    @Transactional
	public Boolean eliminaMoneda(MonedaDaoDto moneda) {
		LOG.debug("Obteniendo obj MonedaDaoDto en eliminaMoneda: "+moneda);
		MonedaSqlDto monedaSqlDto = MonedaDtoMaper.INSTANCE.monedaDaoDtoAmonedaSqlDto(moneda);
		final Integer resultado = monedaSqlMaper.delete(monedaSqlDto);
		return  (resultado !=null) && (resultado >0) ? true :false;
	}

	@Override
	@CacheEvict(value = "monedas", allEntries = true)
	public void limpiarCache() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Se ejecuto la limpieza del cache monedas");
        }
		
	}
}
