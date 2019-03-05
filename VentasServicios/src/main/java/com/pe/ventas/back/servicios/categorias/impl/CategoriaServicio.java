package com.pe.ventas.back.servicios.categorias.impl;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pe.ventas.back.daos.categorias.ICategoriaDao;
import com.pe.ventas.back.dtos.daos.categorias.CategoriaDaoDto;
import com.pe.ventas.back.dtos.servicios.categorias.CategoriaServicioDto;
import com.pe.ventas.back.servicios.categorias.ICategoriaServicio;
import com.pe.ventas.back.utilidades.mapeos.categorias.CategoriaDtoMaper;



@Service("categoriaServicio")
public class CategoriaServicio implements ICategoriaServicio {
	
	private static final Logger LOG = LogManager.getLogger(CategoriaServicio.class);
	
	@Autowired
	ICategoriaDao categoriaDao;

	@Override
	public List<CategoriaServicioDto> obtenerTodasLasCategorias() {
		final List<CategoriaDaoDto> listaCategoriasDao = categoriaDao.obtenerTodasCategorias();
		
		if(listaCategoriasDao.size() >0) {
			return   CategoriaDtoMaper.INSTANCE.convertirListaCategoriaDaoDtoACategoriaServicioDto(listaCategoriasDao);
		}
		return null;
	}

	@Override
	public CategoriaServicioDto obtenerUnaCategoria(CategoriaServicioDto categoria) {
		  CategoriaDaoDto categoriaDaoDto = CategoriaDtoMaper.INSTANCE.categoriaServicioDtoAcategoriaDaoDto(categoria);
		  categoriaDaoDto = categoriaDao.obtenerUnaCategoria(categoriaDaoDto);
		  CategoriaServicioDto categoriaServicioDto= CategoriaDtoMaper.INSTANCE.categoriaDaoDtoACategoriaServicioDto(categoriaDaoDto);
		return categoriaServicioDto;
	}

	@Override
	public Boolean crearCategoria(CategoriaServicioDto categoria) {
		LOG.debug("Insertar CategoriaServicioDto: "+categoria);
		CategoriaDaoDto categoriaDaoDto = CategoriaDtoMaper.INSTANCE.categoriaServicioDtoAcategoriaDaoDto(categoria);
		categoriaDaoDto.setCategoriaFecre(new Date());
		categoriaDaoDto = categoriaDao.crearCategoria(categoriaDaoDto);
		LOG.debug("Resultado categoriaDaoDto: "+categoriaDaoDto);
		
		if((categoriaDaoDto !=null) && (categoriaDaoDto.getCodigo() !=null)
				&& (categoriaDaoDto.getCodigo()>0))
		 {
			return true;
		 }
		return null;
	}

	@Override
	public Boolean actualizarCategoria(CategoriaServicioDto categoria) {
		final CategoriaDaoDto categoriaDaoDto = CategoriaDtoMaper.INSTANCE.categoriaServicioDtoAcategoriaDaoDto(categoria);
		return categoriaDao.actualizarCategoria(categoriaDaoDto);
	}

	@Override
	public Boolean eliminaCategoria(CategoriaServicioDto categoria) {
		LOG.debug("Eliminar categoria: "+categoria);
		final CategoriaDaoDto categoriaDaoDto = CategoriaDtoMaper.INSTANCE.categoriaServicioDtoAcategoriaDaoDto(categoria);
		return categoriaDao.eliminaCategoria(categoriaDaoDto);
	}

	@Override
	public void limpiarCache() {
		categoriaDao.limpiarCache();
		
	}

}
