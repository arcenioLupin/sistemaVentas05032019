package com.pe.ventas.back.dtos.daos.sql.vendedores;

import java.io.Serializable;
import java.util.Date;



import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VendedorSqlDto implements Serializable {
	
	private static final long serialVersionUID = 7589960520933360214L;
	
	private Integer vendedorCodigo;
	private String  vendedorNombre1;
	private String  vendedorNombre2;
	private String  vendedorApepat;
	private String  vendedorApemat;
	private String  vendedorDocid;
	private String  vendedorDireccion;
	private String  vendedorCelular1;
	private String  vendedorCelular2;
	private String  usuarioCodigo;
	private String  vendedorComentado;
	private String  vendedorEstado;
	private Date    vendedorFecre;
	private Date    vendedorHocre;
	private String  vendedorUscre;
	private Date    vendedorFemod;
	private Date    vendedorHomod;
	private String  vendedorUsmod;
	
	
	

}
