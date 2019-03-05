package com.pe.ventas.back.dtos.daos.categorias;

import java.io.Serializable;
import java.util.Date;

import com.pe.ventas.back.dtos.base.JsonDto;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class CategoriasDaoDto implements Serializable {
	
	private static final long serialVersionUID = -8104812660128467251L;
	
	private Integer codigo;
	private String  categoriaNombre;
	private String  categoriaDescripcion;
	private String  categoriaEstado;
	private Date    categoriaFecre;
	private Date    categoriaHocre;
	private String  categoriaUscre;
	private Date    categoriaFemod;
	private Date    categoriaHomod;
	private Date    categoriaUsmod;
	
    public String aJson() {
        return JsonDto.aJson(this);
    }

}
