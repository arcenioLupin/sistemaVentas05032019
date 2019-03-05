package com.pe.ventas.back.dtos.daos.sql.unidadmedida;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UnidadMedidaSqlDto implements Serializable {
	
	private static final long serialVersionUID = 9168858218221146923L;
	
	private String unidadMedidaCodigo;
	private String unidadMedidaSimbolo;
	private String unidadMedidaNombre;

}
