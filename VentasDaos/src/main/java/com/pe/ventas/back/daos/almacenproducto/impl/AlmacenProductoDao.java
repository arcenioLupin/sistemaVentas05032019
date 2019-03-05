package com.pe.ventas.back.daos.almacenproducto.impl;

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
import com.pe.ventas.back.daos.almacenproducto.IAlmacenProductoDao;
import com.pe.ventas.back.daos.sql.mapeos.almacenproducto.AlmacenProductoSqlMaper;
import com.pe.ventas.back.dtos.daos.almacenproducto.AlmacenProductoDaoDto;
import com.pe.ventas.back.dtos.daos.sql.almacenproducto.AlmacenProductoSqlDto;
import com.pe.ventas.back.utilidades.mapeos.almacenproducto.AlmacenProductoDtoMaper;

@Repository("almacenProductoDao")
public class AlmacenProductoDao implements IAlmacenProductoDao {
	
	private static final Logger LOG = LogManager.getLogger(AlmacenProductoDao.class);
	
	@Autowired
	private AlmacenProductoSqlMaper almacenProductoSqlMaper;

	@Override
    @Cacheable(value = "almacenproductos")
    @Transactional(readOnly = true)
	public List<AlmacenProductoDaoDto> obtenerTodosAlmacenProductos() {
		final List<AlmacenProductoSqlDto> listaAlmacenProductos = almacenProductoSqlMaper.selectTodosAlmacenProducto();
		return AlmacenProductoDtoMaper.INSTANCE.convertirListaAlmacenProductoSqlDtoAAlmacenProductoDaoDto(listaAlmacenProductos);
	}

	@Override
    @Cacheable(value = "almacenproductos")
    @Transactional(readOnly = true)
	public AlmacenProductoDaoDto obtenerUnAlmacenProducto(AlmacenProductoDaoDto almacenProducto) {
		LOG.debug("Obteniendo obj AlmacenProductoDaoDto: "+almacenProducto);
		AlmacenProductoSqlDto almacenProductoSqlDto = AlmacenProductoDtoMaper.INSTANCE.almacenProductoDaoDtoAalmacenProductoSqlDto(almacenProducto);
		almacenProductoSqlDto = almacenProductoSqlMaper.selectUnaAlmacenProducto(almacenProductoSqlDto);
		return AlmacenProductoDtoMaper.INSTANCE.almacenProductoSqlDtoAalmacenProductoDaoDTo(almacenProductoSqlDto);
	}

	@Override
    @CachePut(value = "almacenproductos")
    @Transactional
	public AlmacenProductoDaoDto crearAlmacenProducto(AlmacenProductoDaoDto almacenProducto) {
		LOG.debug("Obteniendo obj AlmacenProductoDaoDto en crearAlmacenProducto: "+almacenProducto);
		AlmacenProductoSqlDto almacenProductoSqlDto = AlmacenProductoDtoMaper.INSTANCE.almacenProductoDaoDtoAalmacenProductoSqlDto(almacenProducto);
		final Integer resultado = almacenProductoSqlMaper.insert(almacenProductoSqlDto);       
		if ((resultado != null) && BooleanUtils.toBoolean(resultado)) {
            return AlmacenProductoDtoMaper.INSTANCE.almacenProductoSqlDtoAalmacenProductoDaoDTo(almacenProductoSqlDto);
        }
			
		return null;
	}

	@Override
    @CachePut(value = "almacenproductos")
    @Transactional
	public Boolean actualizarAlmacenProducto(AlmacenProductoDaoDto almacenProducto) {
		LOG.debug("Obteniendo obj AlmacenProductoDaoDto en actualizarAlmacenProducto: "+almacenProducto);
		AlmacenProductoSqlDto almacenProductoSqlDto = AlmacenProductoDtoMaper.INSTANCE.almacenProductoDaoDtoAalmacenProductoSqlDto(almacenProducto);
		final Integer resultado = almacenProductoSqlMaper.update(almacenProductoSqlDto);
		return (resultado !=null) && (resultado >0) ? true :false;
	}
	

	@Override
    @CacheEvict(value = "almacenproductos")
    @Transactional
	public Boolean eliminaAlmacenProducto(AlmacenProductoDaoDto almacenProducto) {
		LOG.debug("Obteniendo obj AlmacenProductoDaoDto en eliminaAlmacenProducto: "+almacenProducto);
		AlmacenProductoSqlDto almacenProductoSqlDto = AlmacenProductoDtoMaper.INSTANCE.almacenProductoDaoDtoAalmacenProductoSqlDto(almacenProducto);
		final Integer resultado = almacenProductoSqlMaper.delete(almacenProductoSqlDto);
		return  (resultado !=null) && (resultado >0) ? true :false;
	}

	@Override
	@CacheEvict(value = "almacenproductos", allEntries = true)
	public void limpiarCache() {
		if (LOG.isDebugEnabled()) {
            LOG.debug("Se ejecuto la limpieza del cache almacenproductos");
        }
		
	}

}
