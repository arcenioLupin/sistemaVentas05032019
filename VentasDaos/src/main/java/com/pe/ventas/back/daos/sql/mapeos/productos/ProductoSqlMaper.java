package com.pe.ventas.back.daos.sql.mapeos.productos;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.pe.ventas.back.dtos.daos.sql.productos.ProductoSqlDto;



@Mapper
public interface ProductoSqlMaper {
	
	public List<ProductoSqlDto> selectTodosProductos(); 
	public ProductoSqlDto selectUnProducto(ProductoSqlDto producto);
	public Integer insert (ProductoSqlDto producto);
	public Integer update (ProductoSqlDto producto);
	public Integer delete (ProductoSqlDto producto);

}
