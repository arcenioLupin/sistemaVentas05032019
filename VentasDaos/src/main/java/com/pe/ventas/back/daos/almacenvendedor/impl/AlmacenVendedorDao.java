package com.pe.ventas.back.daos.almacenvendedor.impl;

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
import com.pe.ventas.back.daos.almacenvendedor.IAlmacenVendedorDao;
import com.pe.ventas.back.daos.sql.mapeos.almacenvendedor.AlmacenVendedorSqlMaper;
import com.pe.ventas.back.dtos.daos.almacenvendedor.AlmacenVendedorDaoDto;
import com.pe.ventas.back.dtos.daos.sql.almacenvendedor.AlmacenVendedorSqlDto;
import com.pe.ventas.back.utilidades.mapeos.almacenvendedor.AlmacenVendedorDtoMaper;

@Repository("almacenVendedorDao")
public class AlmacenVendedorDao implements IAlmacenVendedorDao {
	
	private static final Logger LOG = LogManager.getLogger(AlmacenVendedorDao.class);
	
	@Autowired
	private AlmacenVendedorSqlMaper almacenVendedorSqlMaper;

	@Override
    @Cacheable(value = "almacenvendedores" )
    @Transactional(readOnly = true)
	public List<AlmacenVendedorDaoDto> obtenerTodosAlmacenVendedores() {
		final List<AlmacenVendedorSqlDto> listaAlmacenVendedores = almacenVendedorSqlMaper.selectTodosAlmacenVendedor();
		return AlmacenVendedorDtoMaper.INSTANCE.convertirListaAlmacenVendedorSqlDtoAAlmacenVendedorDaoDto(listaAlmacenVendedores);
	}

	@Override
    @Cacheable(value = "almacenvendedores")
    @Transactional(readOnly = true)
	public AlmacenVendedorDaoDto obtenerUnAlmacenVendedor(AlmacenVendedorDaoDto almacenVendedor) {
		LOG.debug("Obteniendo obj AlmacenVendedorDaoDto: "+almacenVendedor);
		AlmacenVendedorSqlDto almacenVendedorSqlDto = AlmacenVendedorDtoMaper.INSTANCE.AlmacenVendedorDaoDtoAAlmacenVendedorSqlDto(almacenVendedor);
		almacenVendedorSqlDto = almacenVendedorSqlMaper.selectUnAlmacenVendedor(almacenVendedorSqlDto);
		return AlmacenVendedorDtoMaper.INSTANCE.AlmacenVendedorSqlDtoAAlmacenVendedorDaoDTo(almacenVendedorSqlDto);
	}

	@Override
    @CachePut(value = "almacenvendedores")
    @Transactional
	public AlmacenVendedorDaoDto crearAlmacenVendedor(AlmacenVendedorDaoDto almacenVendedor) {
		LOG.debug("Obteniendo obj AlmacenVendedorDaoDto en crearAlmacenVendedor: "+almacenVendedor);
		AlmacenVendedorSqlDto almacenVendedorSqlDto = AlmacenVendedorDtoMaper.INSTANCE.AlmacenVendedorDaoDtoAAlmacenVendedorSqlDto(almacenVendedor);
		final Integer resultado = almacenVendedorSqlMaper.insert(almacenVendedorSqlDto);       
		if ((resultado != null) && BooleanUtils.toBoolean(resultado)) {
            return AlmacenVendedorDtoMaper.INSTANCE.AlmacenVendedorSqlDtoAAlmacenVendedorDaoDTo(almacenVendedorSqlDto);
        }
			
		return null;
	}

	@Override
    @CachePut(value = "almacenvendedores")
    @Transactional
	public Boolean actualizarAlmacenVendedor(AlmacenVendedorDaoDto almacenVendedor) {
		LOG.debug("Obteniendo obj AlmacenVendedorDaoDto en actualizarAlmacenVendedor: "+almacenVendedor);
		AlmacenVendedorSqlDto almacenVendedorSqlDto = AlmacenVendedorDtoMaper.INSTANCE.AlmacenVendedorDaoDtoAAlmacenVendedorSqlDto(almacenVendedor);
		final Integer resultado = almacenVendedorSqlMaper.update(almacenVendedorSqlDto);
		return (resultado !=null) && (resultado >0) ? true :false;
	}

	@Override
    @CacheEvict(value = "almacenvendedores")
    @Transactional
	public Boolean eliminaAlmacenVendedor(AlmacenVendedorDaoDto almacenVendedor) {
		LOG.debug("Obteniendo obj AlmacenVendedorDaoDto en eliminaAlmacenVendedor: "+almacenVendedor);
		AlmacenVendedorSqlDto almacenVendedorSqlDto = AlmacenVendedorDtoMaper.INSTANCE.AlmacenVendedorDaoDtoAAlmacenVendedorSqlDto(almacenVendedor);
		final Integer resultado = almacenVendedorSqlMaper.delete(almacenVendedorSqlDto);
		return  (resultado !=null) && (resultado >0) ? true :false;
	}

	@Override
	@CacheEvict(value = "almacenvendedores", allEntries = true)
	public void limpiarCache() {
		if (LOG.isDebugEnabled()) {
            LOG.debug("Se ejecuto la limpieza del cache almacenvendedores");
        }
		
	}

}
