package com.pe.ventas.back.dtos.daos.sql.monedas;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class MonedaSqlDto implements Serializable {


	private static final long serialVersionUID = 8300803719962891007L;
	
	private String monedaCodigo;
	private String monedaSimbolo;
	private String monedaNombre;

	

}
