package com.pe.ventas.back.dtos.rest.productos;


import java.io.Serializable;

import com.pe.ventas.back.dtos.base.JsonDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductoRestDto implements Serializable {

	private static final long serialVersionUID = 7021809593547811557L;
	
	  private Integer codigo;
	  private String  productoNombre;
	  private String  productoDescripcion;
	  private Integer categoriaCodigo;
	  private String  unidadMedidaCodigo;
	  private double  productoPrecio;
	  private String  monedaCodigo;
	  private String  productoEstado;
	  
	    public String aJson() {
	        return JsonDto.aJson(this);
	    }


}
