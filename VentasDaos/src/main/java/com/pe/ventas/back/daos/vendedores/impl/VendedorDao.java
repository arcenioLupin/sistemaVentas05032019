package com.pe.ventas.back.daos.vendedores.impl;

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
import com.pe.ventas.back.daos.sql.mapeos.vendedores.VendedorSqlMaper;
import com.pe.ventas.back.daos.vendedores.IVendedorDao;
import com.pe.ventas.back.dtos.daos.sql.vendedores.VendedorSqlDto;
import com.pe.ventas.back.dtos.daos.vendedores.VendedorDaoDto;
import com.pe.ventas.back.utilidades.mapeos.vendedores.VendedorDtoMaper;

@Repository("vendedorDao")
public class VendedorDao implements IVendedorDao {
	
	private static final Logger LOG = LogManager.getLogger(VendedorDao.class);
	
	@Autowired
	private VendedorSqlMaper vendedorSqlMaper;

	@Override
    @Cacheable(value = "vendedores")
    @Transactional(readOnly = true)
	public List<VendedorDaoDto> obtenerTodosVendedores() {
		final List<VendedorSqlDto> listaVendedores = vendedorSqlMaper.selectTodosVendedores();
		return VendedorDtoMaper.INSTANCE.convertirListaVendedorSqlDtoAVendedorDaoDto(listaVendedores);
	}

	@Override
    @Cacheable(value = "vendedores")
    @Transactional(readOnly = true)
	public VendedorDaoDto obtenerUnVendedor(VendedorDaoDto vendedor) {
		LOG.debug("Obteniendo obj VendedorDaoDto: "+vendedor);
		VendedorSqlDto vendedorSqlDto = VendedorDtoMaper.INSTANCE.vendedorDaoDtoAVendedorSqlDto(vendedor);
		vendedorSqlDto = vendedorSqlMaper.selectUnVendedor(vendedorSqlDto);
		return VendedorDtoMaper.INSTANCE.vendedorSqlDtoAVendedorDaoDto(vendedorSqlDto);
	}

	@Override
    @CachePut(value = "vendedores")
    @Transactional
	public VendedorDaoDto crearVendedor(VendedorDaoDto vendedor) {
		LOG.debug("Obteniendo obj VendedorDaoDto en crearVendedor: "+vendedor);
		VendedorSqlDto vendedorSqlDto = VendedorDtoMaper.INSTANCE.vendedorDaoDtoAVendedorSqlDto(vendedor);
		final Integer resultado = vendedorSqlMaper.insert(vendedorSqlDto);       
		if ((resultado != null) && BooleanUtils.toBoolean(resultado)) {
            return VendedorDtoMaper.INSTANCE.vendedorSqlDtoAVendedorDaoDto(vendedorSqlDto);
        }
			
		return null;
	}

	@Override
    @CachePut(value = "vendedores")
    @Transactional
	public Boolean actualizarVendedor(VendedorDaoDto vendedor) {
		LOG.debug("Obteniendo obj VendedorDaoDto en actualizarVendedor: "+vendedor);
		VendedorSqlDto vendedorSqlDto = VendedorDtoMaper.INSTANCE.vendedorDaoDtoAVendedorSqlDto(vendedor);
		final Integer resultado = vendedorSqlMaper.update(vendedorSqlDto);
		return (resultado !=null) && (resultado >0) ? true :false;
	}

	@Override
    @CacheEvict(value = "vendedores", key = "#vendedor.codigo")
    @Transactional
	public Boolean eliminaVendedor(VendedorDaoDto vendedor) {
		LOG.debug("Obteniendo obj VendedorDaoDto en eliminaVendedor: "+vendedor);
		VendedorSqlDto vendedorSqlDto = VendedorDtoMaper.INSTANCE.vendedorDaoDtoAVendedorSqlDto(vendedor);
		final Integer resultado = vendedorSqlMaper.delete(vendedorSqlDto);
		return  (resultado !=null) && (resultado >0) ? true :false;
	}

	@Override
	@CacheEvict(value = "categorias", allEntries = true)
	public void limpiarCache() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Se ejecuto la limpieza del cache vendedores");
        }
		
	}

}
