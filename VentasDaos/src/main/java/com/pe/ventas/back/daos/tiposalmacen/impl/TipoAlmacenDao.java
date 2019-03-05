package com.pe.ventas.back.daos.tiposalmacen.impl;

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
import com.pe.ventas.back.daos.sql.mapeos.tiposalmacen.TiposAlmacenSqlMaper;
import com.pe.ventas.back.daos.tiposalmacen.ITipoAlmacenDao;
import com.pe.ventas.back.dtos.daos.sql.tiposalmacen.TipoAlmacenSqlDto;
import com.pe.ventas.back.dtos.daos.tiposalmacen.TipoAlmacenDaoDto;
import com.pe.ventas.back.utilidades.mapeos.tiposalmacen.TipoAlmacenDtoMaper;


@Repository("tipoAlmacenDao")
public class TipoAlmacenDao implements ITipoAlmacenDao {
	
	
	private static final Logger LOG = LogManager.getLogger(TipoAlmacenDao.class);
	
	@Autowired
	private TiposAlmacenSqlMaper  tiposAlmacenSqlMaper;

	@Override
    @Cacheable(value = "tiposalmacen")
    @Transactional(readOnly = true)
	public List<TipoAlmacenDaoDto> obtenerTodosTipoAlmacen() {
		final List<TipoAlmacenSqlDto> listaTipoAlmacen = tiposAlmacenSqlMaper.selectTodosTipoAlmacen();
		return TipoAlmacenDtoMaper.INSTANCE.convertirListaTipoAlmacenSqlDtoATipoAlmacenDaoDto(listaTipoAlmacen);
	}

	@Override
    @Cacheable(value = "tiposalmacen")
    @Transactional(readOnly = true)
	public TipoAlmacenDaoDto obtenerUnTipoAlmacen(TipoAlmacenDaoDto tipoAlmacen) {
		LOG.debug("Obteniendo obj TipoAlmacenDaoDto: "+tipoAlmacen);
		TipoAlmacenSqlDto tipoAlmacenSqlDto = TipoAlmacenDtoMaper.INSTANCE.tipoAlmacenDaoDtoAtipoAlmacenSqlDto(tipoAlmacen);
		tipoAlmacenSqlDto = tiposAlmacenSqlMaper.selectUnTipoAlmacen(tipoAlmacenSqlDto);
		return TipoAlmacenDtoMaper.INSTANCE.tipoAlmacenSqlDtoAtipoAlmacenDaoDTo(tipoAlmacenSqlDto);
	}

	@Override
    @CachePut(value = "tiposalmacen")
    @Transactional
	public TipoAlmacenDaoDto crearTipoAlmacen(TipoAlmacenDaoDto tipoAlmacen) {
		LOG.debug("Obteniendo obj TipoAlmacenDaoDto en crearTipoAlmacen: "+tipoAlmacen);
		TipoAlmacenSqlDto tipoAlmacenSqlDto = TipoAlmacenDtoMaper.INSTANCE.tipoAlmacenDaoDtoAtipoAlmacenSqlDto(tipoAlmacen);
		final Integer resultado = tiposAlmacenSqlMaper.insert(tipoAlmacenSqlDto);       
		if ((resultado != null) && BooleanUtils.toBoolean(resultado)) {
            return TipoAlmacenDtoMaper.INSTANCE.tipoAlmacenSqlDtoAtipoAlmacenDaoDTo(tipoAlmacenSqlDto);
        }
			
		return null;
	}

	@Override
    @CachePut(value = "tiposalmacen")
    @Transactional
	public Boolean actualizarTipoAlmacen(TipoAlmacenDaoDto tipoAlmacen) {
		LOG.debug("Obteniendo obj TipoAlmacenDaoDto en actualizarTipoAlmacen: "+tipoAlmacen);
		TipoAlmacenSqlDto tipoAlmacenSqlDto = TipoAlmacenDtoMaper.INSTANCE.tipoAlmacenDaoDtoAtipoAlmacenSqlDto(tipoAlmacen);
		final Integer resultado = tiposAlmacenSqlMaper.update(tipoAlmacenSqlDto);
		return (resultado !=null) && (resultado >0) ? true :false;
	}

	@Override
    @CacheEvict(value = "tiposalmacen", key = "#tipoAlmacen.codigo")
    @Transactional
	public Boolean eliminaTipoAlmacen(TipoAlmacenDaoDto tipoAlmacen) {
		LOG.debug("Obteniendo obj TipoAlmacenDaoDto en eliminaTipoAlmacen: "+tipoAlmacen);
		TipoAlmacenSqlDto tipoAlmacenSqlDto = TipoAlmacenDtoMaper.INSTANCE.tipoAlmacenDaoDtoAtipoAlmacenSqlDto(tipoAlmacen);
		final Integer resultado = tiposAlmacenSqlMaper.delete(tipoAlmacenSqlDto);
		return  (resultado !=null) && (resultado >0) ? true :false;
	}

	@Override
	@CacheEvict(value = "tiposalmacen", allEntries = true)
	public void limpiarCache() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Se ejecuto la limpieza del cache tiposalmacen");
        }
		
	}
	

}
