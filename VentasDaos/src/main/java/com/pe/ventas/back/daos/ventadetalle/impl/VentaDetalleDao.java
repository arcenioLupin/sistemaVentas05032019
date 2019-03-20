package com.pe.ventas.back.daos.ventadetalle.impl;

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

import com.pe.ventas.back.daos.sql.mapeos.ventadetalle.VentaDetalleSqlMaper;
import com.pe.ventas.back.daos.ventadetalle.IVentaDetalleDao;
import com.pe.ventas.back.daos.ventadetalle.impl.VentaDetalleDao;
import com.pe.ventas.back.dtos.daos.sql.movimientoproducto.MovimientoProductoSqlDto;
import com.pe.ventas.back.dtos.daos.sql.ventadetalle.VentaDetalleSqlDto;
import com.pe.ventas.back.dtos.daos.sql.ventas.VentaSqlDto;
import com.pe.ventas.back.dtos.daos.ventadetalle.VentaDetalleDaoDto;
import com.pe.ventas.back.utilidades.mapeos.movimientoproducto.MovimientoProductoDtoMaper;
import com.pe.ventas.back.utilidades.mapeos.ventadetalle.VentaDetalleDtoMaper;
import com.pe.ventas.back.utilidades.mapeos.ventas.VentaDtoMaper;


@Repository("ventaDetalleDao")
public class VentaDetalleDao implements IVentaDetalleDao{
	
	
	private static final Logger LOG = LogManager.getLogger(VentaDetalleDao.class);	
	
	@Autowired
	private VentaDetalleSqlMaper ventaDetalleSqlMaper;

	@Override
    @Cacheable(value = "ventadetalle")
    @Transactional(readOnly = true)
	public List<VentaDetalleDaoDto> obtenerTodosVentaDetalles() {
		final List<VentaDetalleSqlDto> listaVentaDetalle = ventaDetalleSqlMaper.selectTodosVentaDetalle();
		return VentaDetalleDtoMaper.INSTANCE.convertirListaVentaDetalleSqlDtoAVentaDetalleDaoDto(listaVentaDetalle);

	}

	@Override
    @Cacheable(value = "ventadetalle")
    @Transactional(readOnly = true)
	public VentaDetalleDaoDto obtenerUnVentaDetalle(VentaDetalleDaoDto ventaDetalle) {
		LOG.debug("Obteniendo obj VentaDetalleDaoDto: "+ventaDetalle);
		VentaDetalleSqlDto ventaDetalleSqlDto = VentaDetalleDtoMaper.INSTANCE.VentaDetalleDaoDtoAVentaDetalleSqlDto(ventaDetalle);
		ventaDetalleSqlDto = ventaDetalleSqlMaper.selectUnVentaDetalle(ventaDetalleSqlDto);
		return VentaDetalleDtoMaper.INSTANCE.VentaDetalleSqlDtoAVentaDetalleDaoDTo(ventaDetalleSqlDto);

	}

	@Override
    @CachePut(value = "ventadetalle")
    @Transactional
	public VentaDetalleDaoDto crearVentaDetalle(VentaDetalleDaoDto ventaDetalle) {
		LOG.debug("Obteniendo obj VentaDetalleDaoDto en crearVentaDetalle: "+ventaDetalle);
		VentaDetalleSqlDto ventaDetalleSqlDto = VentaDetalleDtoMaper.INSTANCE.VentaDetalleDaoDtoAVentaDetalleSqlDto(ventaDetalle);
		final Integer resultado = ventaDetalleSqlMaper.insert(ventaDetalleSqlDto);       
		if ((resultado != null) && BooleanUtils.toBoolean(resultado)) {
            return VentaDetalleDtoMaper.INSTANCE.VentaDetalleSqlDtoAVentaDetalleDaoDTo(ventaDetalleSqlDto);
        }
			
       return null;
	}

	@Override
    @CachePut(value = "ventadetalle")
    @Transactional
	public Boolean actualizarVentaDetalle(VentaDetalleDaoDto ventaDetalle) {
		LOG.debug("Obteniendo obj VentaDetalleDaoDto en actualizarVentaDetalle: "+ventaDetalle);
		VentaDetalleSqlDto ventaDetalleSqlDto = VentaDetalleDtoMaper.INSTANCE.VentaDetalleDaoDtoAVentaDetalleSqlDto(ventaDetalle);
		final Integer resultado = ventaDetalleSqlMaper.update(ventaDetalleSqlDto);
		return (resultado !=null) && (resultado >0) ? true :false;
	}

	@Override
    @CacheEvict(value = "ventadetalle")
    @Transactional
	public Boolean eliminaVentaDetalle(VentaDetalleDaoDto ventaDetalle) {
		LOG.debug("Obteniendo obj VentaDetalleDaoDto en eliminaVentaDetalle: "+ventaDetalle);
		VentaDetalleSqlDto ventaDetalleSqlDto = VentaDetalleDtoMaper.INSTANCE.VentaDetalleDaoDtoAVentaDetalleSqlDto(ventaDetalle);
		final Integer resultado = ventaDetalleSqlMaper.delete(ventaDetalleSqlDto);
		return  (resultado !=null) && (resultado >0) ? true :false;
	}

	@Override
	@CacheEvict(value = "ventadetalle", allEntries = true)
	public void limpiarCache() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Se ejecuto la limpieza del cache ventadetalle");
        }
		
	}

}
