package com.pe.ventas.back.daos.sql.mapeos.unidadmedida;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.pe.ventas.back.dtos.daos.sql.unidadmedida.UnidadMedidaSqlDto;

@Mapper
public interface UnidadMedidaSqlMaper {
	
	public List<UnidadMedidaSqlDto> selectTodasUnidadMedida(); 
	public UnidadMedidaSqlDto selectUnaUnidadMedida(UnidadMedidaSqlDto unidadMedida);
	public Integer insert (UnidadMedidaSqlDto unidadMedida);
	public Integer update (UnidadMedidaSqlDto unidadMedida);
	public Integer delete (UnidadMedidaSqlDto unidadMedida);

}
