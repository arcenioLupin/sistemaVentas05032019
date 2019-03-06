package com.pe.ventas.back.daos.ventas.impl;

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
import com.pe.ventas.back.daos.sql.mapeos.ventas.VentaSqlMaper;
import com.pe.ventas.back.daos.ventas.impl.VentaDao;
import com.pe.ventas.back.daos.ventas.IVentaDao;
import com.pe.ventas.back.dtos.daos.sql.ventas.VentaSqlDto;
import com.pe.ventas.back.dtos.daos.ventas.VentaDaoDto;
import com.pe.ventas.back.utilidades.mapeos.ventas.VentaDtoMaper;

@Repository("ventaDao")
public class VentaDao implements IVentaDao {

private static final Logger LOG = LogManager.getLogger(VentaDao.class);
	
	@Autowired
	private VentaSqlMaper VentaSqlMaper;

	@Override
    @Cacheable(value = "ventas")
    @Transactional(readOnly = true)
	public List<VentaDaoDto> obtenerTodosVentas() {
		final List<VentaSqlDto> listaVentas = VentaSqlMaper.selectTodosVentas();
		return VentaDtoMaper.INSTANCE.convertirListaVentaSqlDtoAVentaDaoDto(listaVentas);
	}

	@Override
    @Cacheable(value = "ventas")
    @Transactional(readOnly = true)
	public VentaDaoDto obtenerUnaVenta(VentaDaoDto venta) {
		LOG.debug("Obteniendo obj VentaDaoDto: "+venta);
		VentaSqlDto ventaSqlDto = VentaDtoMaper.INSTANCE.VentaDaoDtoAVentaSqlDto(venta);
		ventaSqlDto = VentaSqlMaper.selectUnaVenta(ventaSqlDto);
		return VentaDtoMaper.INSTANCE.VentaSqlDtoAVentaDaoDTo(ventaSqlDto);
	}

	@Override
    @CachePut(value = "ventas")
    @Transactional
	public VentaDaoDto crearVenta(VentaDaoDto venta) {
		LOG.debug("Obteniendo obj VentaDaoDto en crearVenta: "+venta);
		VentaSqlDto ventaSqlDto = VentaDtoMaper.INSTANCE.VentaDaoDtoAVentaSqlDto(venta);
		final Integer resultado = VentaSqlMaper.insert(ventaSqlDto);       
		if ((resultado != null) && BooleanUtils.toBoolean(resultado)) {
            return VentaDtoMaper.INSTANCE.VentaSqlDtoAVentaDaoDTo(ventaSqlDto);
        }
			
		return null;
	}

	@Override
    @CachePut(value = "ventas")
    @Transactional
	public Boolean actualizarVenta(VentaDaoDto venta) {
		LOG.debug("Obteniendo obj VentaDaoDto en actualizarVenta: "+venta);
		VentaSqlDto ventaSqlDto = VentaDtoMaper.INSTANCE.VentaDaoDtoAVentaSqlDto(venta);
		final Integer resultado = VentaSqlMaper.update(ventaSqlDto);
		return (resultado !=null) && (resultado >0) ? true :false;
	}

	@Override
    @CacheEvict(value = "ventas", key = "#venta.codVenta")
    @Transactional
	public Boolean eliminaVenta(VentaDaoDto venta) {
		LOG.debug("Obteniendo obj VentaDaoDto en eliminaVenta: "+venta);
		VentaSqlDto ventaSqlDto = VentaDtoMaper.INSTANCE.VentaDaoDtoAVentaSqlDto(venta);
		final Integer resultado = VentaSqlMaper.delete(ventaSqlDto);
		return  (resultado !=null) && (resultado >0) ? true :false;
	}

	@Override
	@CacheEvict(value = "ventas", allEntries = true)
	public void limpiarCache() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Se ejecuto la limpieza del cache ventas");
        }
		
	}

}
