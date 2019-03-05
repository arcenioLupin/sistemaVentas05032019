package com.pe.ventas.back.daos.productos.impl;

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
import com.pe.ventas.back.daos.productos.IProductoDao;
import com.pe.ventas.back.daos.sql.mapeos.productos.ProductoSqlMaper;
import com.pe.ventas.back.dtos.daos.productos.ProductoDaoDto;
import com.pe.ventas.back.dtos.daos.sql.productos.ProductoSqlDto;
import com.pe.ventas.back.utilidades.mapeos.productos.ProductoDtoMaper;


@Repository("productoDao")
public class ProductoDao implements IProductoDao {
	
	private static final Logger LOG = LogManager.getLogger(ProductoDao.class);
	
	@Autowired
	private ProductoSqlMaper productoSqlMaper;

	@Override
    @Cacheable(value = "productos")
    @Transactional(readOnly = true)
	public List<ProductoDaoDto> obtenerTodosProductos() {
		final List<ProductoSqlDto> listaProductos = productoSqlMaper.selectTodosProductos();
		return ProductoDtoMaper.INSTANCE.convertirListaProductoSqlDtoAProductoDaoDto(listaProductos);
	}

	@Override
    @Cacheable(value = "productos")
    @Transactional(readOnly = true)
	public ProductoDaoDto obtenerUnProducto(ProductoDaoDto producto) {
		LOG.debug("Obteniendo obj ProductoDaoDto: "+producto);
		ProductoSqlDto productoSqlDto = ProductoDtoMaper.INSTANCE.productoDaoDtoAproductoSqlDto(producto);
		productoSqlDto = productoSqlMaper.selectUnProducto(productoSqlDto);
		return ProductoDtoMaper.INSTANCE.productoSqlDtoAproductoDaoDTo(productoSqlDto);
	}

	@Override
    @CachePut(value = "productos")
    @Transactional
	public ProductoDaoDto crearProducto(ProductoDaoDto producto) {
		LOG.debug("Obteniendo obj ProductoDaoDto en crearProducto: "+producto);
		ProductoSqlDto productoSqlDto = ProductoDtoMaper.INSTANCE.productoDaoDtoAproductoSqlDto(producto);
		final Integer resultado = productoSqlMaper.insert(productoSqlDto);       
		if ((resultado != null) && BooleanUtils.toBoolean(resultado)) {
            return ProductoDtoMaper.INSTANCE.productoSqlDtoAproductoDaoDTo(productoSqlDto);
        }
			
		return null;
	}

	@Override
    @CachePut(value = "productos")
    @Transactional
	public Boolean actualizarProducto(ProductoDaoDto producto) {
		LOG.debug("Obteniendo obj ProductoDaoDto en actualizarProducto: "+producto);
		ProductoSqlDto productoSqlDto = ProductoDtoMaper.INSTANCE.productoDaoDtoAproductoSqlDto(producto);
		final Integer resultado = productoSqlMaper.update(productoSqlDto);
		return (resultado !=null) && (resultado >0) ? true :false;
	}

	@Override
    @CacheEvict(value = "productos", key = "#producto.codigo")
    @Transactional
	public Boolean eliminaProducto(ProductoDaoDto producto) {
		LOG.debug("Obteniendo obj ProductoDaoDto en eliminaProducto: "+producto);
		ProductoSqlDto productoSqlDto = ProductoDtoMaper.INSTANCE.productoDaoDtoAproductoSqlDto(producto);
		final Integer resultado = productoSqlMaper.delete(productoSqlDto);
		return  (resultado !=null) && (resultado >0) ? true :false;
	}

	@Override
	@CacheEvict(value = "productos", allEntries = true)
	public void limpiarCache() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Se ejecuto la limpieza del cache productos");
        }
		
	}

}
