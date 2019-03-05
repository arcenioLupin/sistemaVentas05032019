package com.pe.ventas.back.daos.categorias.impl;

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

import com.pe.ventas.back.daos.categorias.ICategoriaDao;
import com.pe.ventas.back.daos.sql.mapeos.categorias.CategoriaSqlMaper;
import com.pe.ventas.back.dtos.daos.categorias.CategoriaDaoDto;
import com.pe.ventas.back.dtos.daos.sql.categorias.CategoriaSqlDto;
import com.pe.ventas.back.utilidades.mapeos.categorias.CategoriaDtoMaper;



@Repository("categoriaDao")
public class CategoriaDao implements ICategoriaDao {

	private static final Logger LOG = LogManager.getLogger(CategoriaDao.class);
	
	@Autowired
	private CategoriaSqlMaper categoriaSqlMaper;
	
	
	@Override
    @Cacheable(value = "categorias")
    @Transactional(readOnly = true)
	public List<CategoriaDaoDto> obtenerTodasCategorias() {
		final List<CategoriaSqlDto> listaCategorias = categoriaSqlMaper.selectTodasCategorias();
		return CategoriaDtoMaper.INSTANCE.convertirListaCategoriaSqlDtoACategoriaDaoDto(listaCategorias);
	}

	@Override
    @Cacheable(value = "categorias")
    @Transactional(readOnly = true)
	public CategoriaDaoDto obtenerUnaCategoria(CategoriaDaoDto categoria) {
		LOG.debug("Obteniendo obj CategoriaDaoDto: "+categoria);
		CategoriaSqlDto categoriaSqlDto = CategoriaDtoMaper.INSTANCE.categoriaDaoDtoAcategoriaSqlDto(categoria);
		categoriaSqlDto = categoriaSqlMaper.selectUnaCategoria(categoriaSqlDto);
		return CategoriaDtoMaper.INSTANCE.categoriaSqlDtoAcategoriaDaoDTo(categoriaSqlDto);
	}

	@Override
    @CachePut(value = "categorias")
    @Transactional
	public CategoriaDaoDto crearCategoria(CategoriaDaoDto categoria) {
		LOG.debug("Obteniendo obj CategoriaDaoDto en crearCategoria: "+categoria);
		CategoriaSqlDto categoriaSqlDto = CategoriaDtoMaper.INSTANCE.categoriaDaoDtoAcategoriaSqlDto(categoria);
		final Integer resultado = categoriaSqlMaper.insert(categoriaSqlDto);       
		if ((resultado != null) && BooleanUtils.toBoolean(resultado)) {
            return CategoriaDtoMaper.INSTANCE.categoriaSqlDtoAcategoriaDaoDTo(categoriaSqlDto);
        }
			
		return null;
	}

	@Override
    @CachePut(value = "categorias")
    @Transactional
	public Boolean actualizarCategoria(CategoriaDaoDto categoria) {
		LOG.debug("Obteniendo obj CategoriaDaoDto en actualizarCategoria: "+categoria);
		CategoriaSqlDto categoriaSqlDto = CategoriaDtoMaper.INSTANCE.categoriaDaoDtoAcategoriaSqlDto(categoria);
		final Integer resultado = categoriaSqlMaper.update(categoriaSqlDto);
		return (resultado !=null) && (resultado >0) ? true :false;
		
	}

	@Override
    @CacheEvict(value = "categorias", key = "#categoria.codigo")
    @Transactional
	public Boolean eliminaCategoria(CategoriaDaoDto categoria) {
		LOG.debug("Obteniendo obj CategoriaDaoDto en eliminaCategoria: "+categoria);
		CategoriaSqlDto categoriaSqlDto = CategoriaDtoMaper.INSTANCE.categoriaDaoDtoAcategoriaSqlDto(categoria);
		final Integer resultado = categoriaSqlMaper.delete(categoriaSqlDto);
		return  (resultado !=null) && (resultado >0) ? true :false;
	}

	@Override
	@CacheEvict(value = "categorias", allEntries = true)
	public void limpiarCache() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Se ejecuto la limpieza del cache categoria");
        }
		
	}
	
	

}
