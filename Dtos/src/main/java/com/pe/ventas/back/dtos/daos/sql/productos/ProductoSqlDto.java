package com.pe.ventas.back.dtos.daos.sql.productos;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductoSqlDto implements Serializable{
	 
	private static final long serialVersionUID = 3115448924839802533L;
	
  private Integer productoCodigo;
  private String  productoNombre;
  private String  productoDescripcion;
  private Integer categoriaCodigo;
  private String  unidadMedidaCodigo;
  private double  productoPrecio;
  private String  monedaCodigo;
  private String  productoEstado;
  private Date    productoFecre;
  private Date    productoHocre;
  private String  productoUscre;
  private Date    productoFemod;
  private Date    productoHomod;
  private String  productoUsmod;
  
  
}
