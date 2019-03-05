package com.pe.ventas.back.dtos.daos.sql.categorias;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class CategoriaSqlDto implements Serializable {
	
  
	private static final long serialVersionUID = 7194584790183329862L;
	
	private Integer categoriaCodigo;
	private String  categoriaNombre;
	private String  categoriaDescripcion;
	private String  categoriaEstado;
	private Date    categoriaFecre;
	private Date    categoriaHocre;
	private String  categoriaUscre;
	private Date    categoriaFemod;
	private Date    categoriaHomod;
	private Date    categoriaUsmod;
	
	

}
