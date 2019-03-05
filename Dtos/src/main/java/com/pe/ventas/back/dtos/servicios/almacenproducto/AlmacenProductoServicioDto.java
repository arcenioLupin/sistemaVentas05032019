package com.pe.ventas.back.dtos.servicios.almacenproducto;



import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AlmacenProductoServicioDto implements Serializable {
	
	private static final long serialVersionUID = -8053598173118269147L;
		
	private Integer codAlmacen;
	private Integer codProducto;
	private Integer almacenProductoStock;
	private String unidadMedidaCodigo;
	private double almacenProductoPrecio;
	private String monedaCodigo;
	private String almacenProductoEstado;

}
