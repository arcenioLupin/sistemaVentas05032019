package com.pe.ventas.back.daos.unidadmedida.impl;

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
import com.pe.ventas.back.daos.sql.mapeos.unidadmedida.UnidadMedidaSqlMaper;
import com.pe.ventas.back.daos.unidadmedida.IUnidadMedidaDao;
import com.pe.ventas.back.dtos.daos.sql.unidadmedida.UnidadMedidaSqlDto;
import com.pe.ventas.back.dtos.daos.unidadmedida.UnidadMedidaDaoDto;
import com.pe.ventas.back.utilidades.mapeos.unidadmedida.UnidadMedidaDtoMaper;


@Repository("unidadMedidaDao")
public class UnidadMedidaDao implements IUnidadMedidaDao{
	
	private static final Logger LOG = LogManager.getLogger(UnidadMedidaDao.class);
	
	@Autowired
	private UnidadMedidaSqlMaper  unidadMedidaSqlMaper;

	@Override
    @Cacheable(value = "unidadMedida")
    @Transactional(readOnly = true)
	public List<UnidadMedidaDaoDto> obtenerTodasUnidadMedida() {
		final List<UnidadMedidaSqlDto> listaUnidadMedida = unidadMedidaSqlMaper.selectTodasUnidadMedida();
		return UnidadMedidaDtoMaper.INSTANCE.convertirListaunidadMedidaSqlDtoAunidadMedidaDaoDto(listaUnidadMedida);
	}

	@Override
    @Cacheable(value = "unidadMedida")
    @Transactional(readOnly = true)
	public UnidadMedidaDaoDto obtenerUnaUnidadMedida(UnidadMedidaDaoDto unidadMedida) {
		LOG.debug("Obteniendo obj UnidadMedidaDaoDto: "+unidadMedida);
		UnidadMedidaSqlDto unidadMedidaSqlDto = UnidadMedidaDtoMaper.INSTANCE.unidadMedidaDaoDtoAunidadMedidaSqlDto(unidadMedida);
		unidadMedidaSqlDto = unidadMedidaSqlMaper.selectUnaUnidadMedida(unidadMedidaSqlDto);
		return UnidadMedidaDtoMaper.INSTANCE.unidadMedidaSqlDtoAunidadMedidaDaoDTo(unidadMedidaSqlDto);
	}

	@Override
    @CachePut(value = "unidadMedida")
    @Transactional
	public UnidadMedidaDaoDto crearUnidadMedida(UnidadMedidaDaoDto unidadMedida) {
		LOG.debug("Obteniendo obj UnidadMedidaDaoDto en crearUnidadMedida: "+unidadMedida);
		UnidadMedidaSqlDto unidadMedidaSqlDto = UnidadMedidaDtoMaper.INSTANCE.unidadMedidaDaoDtoAunidadMedidaSqlDto(unidadMedida);
		final Integer resultado = unidadMedidaSqlMaper.insert(unidadMedidaSqlDto);       
		if ((resultado != null) && BooleanUtils.toBoolean(resultado)) {
            return UnidadMedidaDtoMaper.INSTANCE.unidadMedidaSqlDtoAunidadMedidaDaoDTo(unidadMedidaSqlDto);
        }
			
		return null;
	}

	
	@Override
    @CachePut(value = "unidadMedida")
    @Transactional
	public Boolean actualizarUnidadMedida(UnidadMedidaDaoDto unidadMedida) {
		LOG.debug("Obteniendo obj UnidadMedidaDaoDto en actualizarUnidadMedida: "+unidadMedida);
		UnidadMedidaSqlDto unidadMedidaSqlDto = UnidadMedidaDtoMaper.INSTANCE.unidadMedidaDaoDtoAunidadMedidaSqlDto(unidadMedida);
		final Integer resultado = unidadMedidaSqlMaper.update(unidadMedidaSqlDto);
		return (resultado !=null) && (resultado >0) ? true :false;
	}

	@Override
    @CacheEvict(value = "unidadMedida", key = "#unidadMedida.codigo")
    @Transactional
	public Boolean eliminaUnidadMedida(UnidadMedidaDaoDto unidadMedida) {
		LOG.debug("Obteniendo obj UnidadMedidaDaoDto en eliminaUnidadMedida: "+unidadMedida);
		UnidadMedidaSqlDto unidadMedidaSqlDto = UnidadMedidaDtoMaper.INSTANCE.unidadMedidaDaoDtoAunidadMedidaSqlDto(unidadMedida);
		final Integer resultado = unidadMedidaSqlMaper.delete(unidadMedidaSqlDto);
		return  (resultado !=null) && (resultado >0) ? true :false;
	}

	@Override
	@CacheEvict(value = "unidadMedida", allEntries = true)
	public void limpiarCache() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Se ejecuto la limpieza del cache unidadMedida");
        }
		
	}

}
