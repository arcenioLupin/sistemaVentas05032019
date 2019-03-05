package com.pe.ventas.back.dtos.daos.almacenproducto;



import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AlmacenProductoDaoDto implements Serializable {
	
	private static final long serialVersionUID = 5351105692562235755L;
	
	private Integer codAlmacen;
	private Integer codProducto;
	private Integer almacenProductoStock;
	private String unidadMedidaCodigo;
	private double almacenProductoPrecio;
	private String monedaCodigo;
	private String almacenProductoEstado;

}
