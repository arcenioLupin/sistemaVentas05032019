package com.pe.ventas.back.dtos.servicios.vendedores;

import java.io.Serializable;
import java.util.Date;
import com.pe.ventas.back.dtos.base.JsonDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VendedorServicioDto implements Serializable {
	
	private static final long serialVersionUID = -5412052646791313600L;
	
	private Integer codigo;
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
	
    public String aJson() {
        return JsonDto.aJson(this);
    }


}
