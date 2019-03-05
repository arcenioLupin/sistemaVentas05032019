package com.pe.ventas.back.daos.almacenes.impl;

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
import com.pe.ventas.back.daos.almacenes.IAlmacenDao;
import com.pe.ventas.back.daos.sql.mapeos.almacenes.AlmacenSqlMaper;
import com.pe.ventas.back.dtos.daos.almacenes.AlmacenDaoDto;
import com.pe.ventas.back.dtos.daos.sql.almacenes.AlmacenSqlDto;
import com.pe.ventas.back.utilidades.mapeos.almacenes.AlmacenDtoMaper;

@Repository("almacenDao")
public class AlmacenDao implements IAlmacenDao {

	private static final Logger LOG = LogManager.getLogger(AlmacenDao.class);
	
	@Autowired
	private AlmacenSqlMaper almacenSqlMaper;
	
	@Override
    @Cacheable(value = "almacenes")
    @Transactional(readOnly = true)
	public List<AlmacenDaoDto> obtenerTodosAlmacenes() {
		final List<AlmacenSqlDto> listaAlmacenes = almacenSqlMaper.selectTodosAlmacenes();
		return AlmacenDtoMaper.INSTANCE.convertirListaAlmacenSqlDtoAAlmacenDaoDto(listaAlmacenes);
	}

	@Override
    @Cacheable(value = "almacenes")
    @Transactional(readOnly = true)
	public AlmacenDaoDto obtenerUnAlmacen(AlmacenDaoDto almacen) {
		LOG.debug("Obteniendo obj AlmacenDaoDto: "+almacen);
		AlmacenSqlDto almacenSqlDto = AlmacenDtoMaper.INSTANCE.almacenDaoDtoAalmacenSqlDto(almacen);
		almacenSqlDto = almacenSqlMaper.selectUnAlmacen(almacenSqlDto);
		return AlmacenDtoMaper.INSTANCE.almacenSqlDtoAalmacenDaoDTo(almacenSqlDto);
	}

	@Override
    @CachePut(value = "almacenes")
    @Transactional
	public AlmacenDaoDto crearAlmacen(AlmacenDaoDto almacen) {
		LOG.debug("Obteniendo obj AlmacenDaoDto en crearAlmacen: "+almacen);
		AlmacenSqlDto almacenSqlDto = AlmacenDtoMaper.INSTANCE.almacenDaoDtoAalmacenSqlDto(almacen);
		final Integer resultado = almacenSqlMaper.insert(almacenSqlDto);       
		if ((resultado != null) && BooleanUtils.toBoolean(resultado)) {
            return AlmacenDtoMaper.INSTANCE.almacenSqlDtoAalmacenDaoDTo(almacenSqlDto);
        }
			
		return null;
	}

	@Override
    @CachePut(value = "almacenes")
    @Transactional
	public Boolean actualizarAlmacen(AlmacenDaoDto almacen) {
		LOG.debug("Obteniendo obj AlmacenDaoDto en actualizarAlmacen: "+almacen);
		AlmacenSqlDto almacenSqlDto = AlmacenDtoMaper.INSTANCE.almacenDaoDtoAalmacenSqlDto(almacen);
		final Integer resultado = almacenSqlMaper.update(almacenSqlDto);
		return (resultado !=null) && (resultado >0) ? true :false;
	}

	@Override
    @CacheEvict(value = "almacenes", key = "#almacen.codigo")
    @Transactional
	public Boolean eliminaAlmacen(AlmacenDaoDto almacen) {
		LOG.debug("Obteniendo obj AlmacenDaoDto en eliminaAlmacen: "+almacen);
		AlmacenSqlDto almacenSqlDto = AlmacenDtoMaper.INSTANCE.almacenDaoDtoAalmacenSqlDto(almacen);
		final Integer resultado = almacenSqlMaper.delete(almacenSqlDto);
		return  (resultado !=null) && (resultado >0) ? true :false;
	}

	@Override
	@CacheEvict(value = "almacenes", allEntries = true)
	public void limpiarCache() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Se ejecuto la limpieza del cache almacenes");
        }
		
	}

}
