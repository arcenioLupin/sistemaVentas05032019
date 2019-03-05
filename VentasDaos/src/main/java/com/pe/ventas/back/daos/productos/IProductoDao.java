package com.pe.ventas.back.daos.productos;

import java.util.List;
import com.pe.ventas.back.dtos.daos.productos.ProductoDaoDto;



public interface IProductoDao {
	
	
	public List<ProductoDaoDto> obtenerTodosProductos();
	public ProductoDaoDto obtenerUnProducto(ProductoDaoDto producto);
	public ProductoDaoDto crearProducto(ProductoDaoDto producto);
	public Boolean actualizarProducto (ProductoDaoDto producto);
	public Boolean eliminaProducto(ProductoDaoDto producto);
	void limpiarCache();

}
