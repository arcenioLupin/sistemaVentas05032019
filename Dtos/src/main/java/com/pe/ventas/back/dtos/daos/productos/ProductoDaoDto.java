package com.pe.ventas.back.dtos.daos.productos;

import java.io.Serializable;
import java.util.Date;

import com.pe.ventas.back.dtos.base.JsonDto;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ProductoDaoDto implements Serializable {

	private static final long serialVersionUID = -6189807596878847013L;
	
	  private Integer codigo;
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
	  
	   public String aJson() {
	        return JsonDto.aJson(this);
	    }
}
