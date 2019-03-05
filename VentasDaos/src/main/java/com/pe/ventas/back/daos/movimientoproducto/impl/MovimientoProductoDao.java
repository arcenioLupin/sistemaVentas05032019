package com.pe.ventas.back.daos.movimientoproducto.impl;

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

import com.pe.ventas.back.daos.movimientoproducto.IMovimientoProductoDao;
import com.pe.ventas.back.daos.sql.mapeos.movimientoproducto.MovimientoProductoSqlMaper;
import com.pe.ventas.back.dtos.daos.movimientoproducto.MovimientoProductoDaoDto;
import com.pe.ventas.back.dtos.daos.sql.movimientoproducto.MovimientoProductoSqlDto;
import com.pe.ventas.back.utilidades.mapeos.movimientoproducto.MovimientoProductoDtoMaper;

@Repository("movimientoProductoDao")
public class MovimientoProductoDao implements IMovimientoProductoDao {
	
	private static final Logger LOG = LogManager.getLogger(MovimientoProductoDao.class);

	@Autowired
	private MovimientoProductoSqlMaper movimientoProductoSqlMaper;

	@Override
    @Cacheable(value = "movimientoproductos")
    @Transactional(readOnly = true)
	public List<MovimientoProductoDaoDto> obtenerTodosMovimientoProductos() {
		final List<MovimientoProductoSqlDto> listaMovimientoProductos = movimientoProductoSqlMaper.selectTodosMovimientoProducto();
		return MovimientoProductoDtoMaper.INSTANCE.convertirListaMovimientoProductoSqlDtoAMovimientoProductoDaoDto(listaMovimientoProductos);
	}

	@Override
    @Cacheable(value = "movimientoproductos")
    @Transactional(readOnly = true)
	public MovimientoProductoDaoDto obtenerUnMovimientoProducto(MovimientoProductoDaoDto movimientoProducto) {
		LOG.debug("Obteniendo obj MovimientoProductoDaoDto: "+movimientoProducto);
		MovimientoProductoSqlDto movimientoProductoSqlDto = MovimientoProductoDtoMaper.INSTANCE.MovimientoProductoDaoDtoAMovimientoProductoSqlDto(movimientoProducto);
		movimientoProductoSqlDto = movimientoProductoSqlMaper.selectUnMovimientoProducto(movimientoProductoSqlDto);
		return MovimientoProductoDtoMaper.INSTANCE.MovimientoProductoSqlDtoAMovimientoProductoDaoDTo(movimientoProductoSqlDto);
	}

	@Override
    @CachePut(value = "movimientoproductos")
    @Transactional
	public MovimientoProductoDaoDto crearMovimientoProducto(MovimientoProductoDaoDto movimientoProducto) {
		LOG.debug("Obteniendo obj MovimientoProductoDaoDto en crearMovimientoProducto: "+movimientoProducto);
		MovimientoProductoSqlDto movimientoProductoSqlDto = MovimientoProductoDtoMaper.INSTANCE.MovimientoProductoDaoDtoAMovimientoProductoSqlDto(movimientoProducto);
		final Integer resultado = movimientoProductoSqlMaper.insert(movimientoProductoSqlDto);       
		if ((resultado != null) && BooleanUtils.toBoolean(resultado)) {
            return MovimientoProductoDtoMaper.INSTANCE.MovimientoProductoSqlDtoAMovimientoProductoDaoDTo(movimientoProductoSqlDto);
        }
			
		return null;
	}

	@Override
    @CachePut(value = "movimientoproductos")
    @Transactional
	public Boolean actualizarMovimientoProducto(MovimientoProductoDaoDto movimientoProducto) {
		LOG.debug("Obteniendo obj MovimientoProductoDaoDto en actualizarMovimientoProducto: "+movimientoProducto);
		MovimientoProductoSqlDto movimientoProductoSqlDto = MovimientoProductoDtoMaper.INSTANCE.MovimientoProductoDaoDtoAMovimientoProductoSqlDto(movimientoProducto);
		final Integer resultado = movimientoProductoSqlMaper.update(movimientoProductoSqlDto);
		return (resultado !=null) && (resultado >0) ? true :false;
	}
	

	@Override
    @CacheEvict(value = "movimientoproductos")
    @Transactional
	public Boolean eliminaMovimientoProducto(MovimientoProductoDaoDto movimientoProducto) {
		LOG.debug("Obteniendo obj MovimientoProductoDaoDto en eliminaMovimientoProducto: "+movimientoProducto);
		MovimientoProductoSqlDto movimientoProductoSqlDto = MovimientoProductoDtoMaper.INSTANCE.MovimientoProductoDaoDtoAMovimientoProductoSqlDto(movimientoProducto);
		final Integer resultado = movimientoProductoSqlMaper.delete(movimientoProductoSqlDto);
		return  (resultado !=null) && (resultado >0) ? true :false;
	}

	@Override
	@CacheEvict(value = "movimientoproductos", allEntries = true)
	public void limpiarCache() {
		if (LOG.isDebugEnabled()) {
            LOG.debug("Se ejecuto la limpieza del cache movimientoproductos");
        }
		
	}

}
