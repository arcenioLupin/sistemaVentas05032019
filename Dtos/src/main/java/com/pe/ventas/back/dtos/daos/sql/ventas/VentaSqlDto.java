package com.pe.ventas.back.dtos.daos.sql.ventas;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VentaSqlDto implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1150619624789833514L;
	
	private Integer ventaCodigo;
	private Date    ventaFecha;
	private Integer almacenCodigo;
	private Integer vendedorCodigo;
	private String  monedaCodigo;
	private double  ventaSubTotal;
	private double  ventaDescuento;
	private double  ventaTotal;
	private double  ventaEfectivo;
	private double  ventaTarjeta;
	private String  ventaReferTarjeta;
	private String  ventaComentario;
	private String  ventaEstado;
	private Date    ventaFecre;
	private Date    ventaHocre;
	private String  ventaUscre;
	private Date    ventaFemod;
	private Date    ventaHomod;
	private String  ventaUsmod;

}
