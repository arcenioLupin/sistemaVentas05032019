package com.pe.ventas.back.servicios.productos.impl;

import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pe.ventas.back.daos.productos.IProductoDao;
import com.pe.ventas.back.dtos.daos.productos.ProductoDaoDto;
import com.pe.ventas.back.dtos.servicios.productos.ProductoServicioDto;
import com.pe.ventas.back.servicios.productos.IProductoServicio;
import com.pe.ventas.back.utilidades.mapeos.productos.ProductoDtoMaper;

@Service("productoServicio")
public class ProductoServicio implements IProductoServicio {
	
	private static final Logger LOG = LogManager.getLogger(ProductoServicio.class);
	
	@Autowired
	IProductoDao productoDao;

	@Override
	public List<ProductoServicioDto> obtenerTodasLosProductos() {
		final List<ProductoDaoDto> listaProductosDao = productoDao.obtenerTodosProductos();
		
		if(listaProductosDao.size() >0) {
			return   ProductoDtoMaper.INSTANCE.convertirListaProductoDaoDtoAProductoServicioDto(listaProductosDao);
		}
		return null;
	}

	@Override
	public ProductoServicioDto obtenerUnProducto(ProductoServicioDto producto) {
		ProductoDaoDto productoDaoDto = ProductoDtoMaper.INSTANCE.productoServicioDtoAproductoDaoDto(producto);
		productoDaoDto = productoDao.obtenerUnProducto(productoDaoDto);
		ProductoServicioDto productoServicioDto= ProductoDtoMaper.INSTANCE.productoDaoDtoAproductoServicioDto(productoDaoDto);
		return productoServicioDto;
	}

	@Override
	public Boolean crearProducto(ProductoServicioDto producto) {
		LOG.debug("Insertar ProductoServicioDto: "+producto);
		ProductoDaoDto productoDaoDto = ProductoDtoMaper.INSTANCE.productoServicioDtoAproductoDaoDto(producto);
		productoDaoDto.setProductoFecre(new Date());
		productoDaoDto = productoDao.crearProducto(productoDaoDto);
		LOG.debug("Resultado productoDaoDto: "+productoDaoDto);
		
		if((productoDaoDto !=null) && (productoDaoDto.getCodigo() !=null)
				&& (productoDaoDto.getCodigo()>0))
		 {
			return true;
		 }
		return null;
	}

	@Override
	public Boolean actualizarProducto(ProductoServicioDto producto) {
		final ProductoDaoDto productoDaoDto = ProductoDtoMaper.INSTANCE.productoServicioDtoAproductoDaoDto(producto);
		return productoDao.actualizarProducto(productoDaoDto);
	}

	@Override
	public Boolean eliminaProducto(ProductoServicioDto producto) {
		LOG.debug("Eliminar producto: "+producto);
		final ProductoDaoDto productoDaoDto = ProductoDtoMaper.INSTANCE.productoServicioDtoAproductoDaoDto(producto);
		return productoDao.eliminaProducto(productoDaoDto);
	}

	@Override
	public void limpiarCache() {
		productoDao.limpiarCache();
		
	}

}
