package com.pe.ventas.back.dtos.rest.categorias;



import java.io.Serializable;

import com.pe.ventas.back.dtos.base.JsonDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoriaRestDto  implements Serializable{
	
	

	private static final long serialVersionUID = -4720449775869202817L;
	
	private Integer categoriaCodigo;
	private String  categoriaNombre;
	private String  categoriaDescripcion;
	private String  categoriaEstado;
	
    public String aJson() {
        return JsonDto.aJson(this);
    }


}
