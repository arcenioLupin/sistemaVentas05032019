package com.pe.ventas.back.daos.movimientos.impl;

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

import com.pe.ventas.back.daos.movimientos.IMovimientoDao;
import com.pe.ventas.back.daos.sql.mapeos.movimientos.MovimientoSqlMaper;
import com.pe.ventas.back.dtos.daos.movimientos.MovimientoDaoDto;
import com.pe.ventas.back.dtos.daos.sql.movimientos.MovimientoSqlDto;
import com.pe.ventas.back.utilidades.mapeos.movimientos.MovimientoDtoMaper;

@Repository("movimientoDao")
public class MovimientoDao  implements IMovimientoDao {
	
	private static final Logger LOG = LogManager.getLogger(MovimientoDao.class);
	
	@Autowired
	private MovimientoSqlMaper movimientoSqlMaper;

	@Override
    @Cacheable(value = "movimientos")
    @Transactional(readOnly = true)
	public List<MovimientoDaoDto> obtenerTodosMovimientos() {
		final List<MovimientoSqlDto> listaMovimientos = movimientoSqlMaper.selectTodosMovimientos();
		return MovimientoDtoMaper.INSTANCE.convertirListaMovimientoSqlDtoAMovimientoDaoDto(listaMovimientos);
	}

	@Override
    @Cacheable(value = "movimientos")
    @Transactional(readOnly = true)
	public MovimientoDaoDto obtenerUnMovimiento(MovimientoDaoDto movimiento) {
		LOG.debug("Obteniendo obj MovimientoDaoDto: "+movimiento);
		MovimientoSqlDto movimientoSqlDto = MovimientoDtoMaper.INSTANCE.movimientoDaoDtoAmovimientoSqlDto(movimiento);
		movimientoSqlDto = movimientoSqlMaper.selectUnMovimiento(movimientoSqlDto);
		return MovimientoDtoMaper.INSTANCE.movimientoSqlDtoAmovimientoDaoDTo(movimientoSqlDto);
	}

	@Override
    @CachePut(value = "movimientos")
    @Transactional
	public MovimientoDaoDto crearMovimiento(MovimientoDaoDto movimiento) {
		LOG.debug("Obteniendo obj MovimientoDaoDto en crearMovimiento: "+movimiento);
		MovimientoSqlDto movimientoSqlDto = MovimientoDtoMaper.INSTANCE.movimientoDaoDtoAmovimientoSqlDto(movimiento);
		final Integer resultado = movimientoSqlMaper.insert(movimientoSqlDto);       
		if ((resultado != null) && BooleanUtils.toBoolean(resultado)) {
            return MovimientoDtoMaper.INSTANCE.movimientoSqlDtoAmovimientoDaoDTo(movimientoSqlDto);
        }
			
		return null;
	}

	@Override
    @CachePut(value = "movimientos")
    @Transactional
	public Boolean actualizarMovimiento(MovimientoDaoDto movimiento) {
		LOG.debug("Obteniendo obj MovimientoDaoDto en actualizarMovimiento: "+movimiento);
		MovimientoSqlDto movimientoSqlDto = MovimientoDtoMaper.INSTANCE.movimientoDaoDtoAmovimientoSqlDto(movimiento);
		final Integer resultado = movimientoSqlMaper.update(movimientoSqlDto);
		return (resultado !=null) && (resultado >0) ? true :false;
	}

	@Override
    @CacheEvict(value = "movimientos", key = "#movimiento.codigo")
    @Transactional
	public Boolean eliminaMovimiento(MovimientoDaoDto movimiento) {
		LOG.debug("Obteniendo obj MovimientoDaoDto en eliminaMovimiento: "+movimiento);
		MovimientoSqlDto movimientoSqlDto = MovimientoDtoMaper.INSTANCE.movimientoDaoDtoAmovimientoSqlDto(movimiento);
		final Integer resultado = movimientoSqlMaper.delete(movimientoSqlDto);
		return  (resultado !=null) && (resultado >0) ? true :false;
	}

	@Override
	@CacheEvict(value = "movimientos", allEntries = true)
	public void limpiarCache() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Se ejecuto la limpieza del cache movimientos");
        }
		
	}
	
	

}
