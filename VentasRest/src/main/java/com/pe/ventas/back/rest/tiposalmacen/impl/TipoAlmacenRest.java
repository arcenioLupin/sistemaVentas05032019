package com.pe.ventas.back.rest.tiposalmacen.impl;

import static com.pe.ventas.back.dtos.base.Constantes.CONTENT_TYPE;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.path;
import static spark.Spark.put;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.pe.ventas.back.dtos.base.JsonDto;
import com.pe.ventas.back.dtos.rest.ResultResponse;
import com.pe.ventas.back.dtos.rest.tiposalmacen.TipoAlmacenRestDto;
import com.pe.ventas.back.dtos.servicios.tiposalmacen.TipoAlmacenServicioDto;
import com.pe.ventas.back.rest.usuarios.IVentaRest;
import com.pe.ventas.back.servicios.tiposalmacen.ITipoAlmacenServicio;
import com.pe.ventas.back.utilidades.mapeos.tiposalmacen.TipoAlmacenDtoMaper;

import spark.Request;
import spark.Response;

@Component("tipoAlmacenRest")
public class TipoAlmacenRest implements IVentaRest {
	
	
	private static final Logger LOG = LogManager.getLogger(TipoAlmacenRest.class);

	@Autowired
	private ITipoAlmacenServicio tipoAlmacenServicio;
	
    @Override
    public void routers() {

        path("/api", () -> {
            path("/v2", () -> {
                get("/tiposAlmacen/estadoDelServicio", CONTENT_TYPE,
                        (request, response) -> new ResultResponse.Builder().code(200).message("Funcionando").build());
               
                get("/tiposAlmacen", CONTENT_TYPE, (request, response) -> todosLosTiposAlmacen(request, response));
                get("/tiposAlmacen/tipoAlmacen/:id", CONTENT_TYPE, (request, response) -> obtenerUnTipoAlmacen(request, response));
                put("/tiposAlmacen", CONTENT_TYPE, (request, response) -> insertarTipoAlmacen(request, response));
                put("/tiposAlmacen/actualizar", CONTENT_TYPE, (request, response) -> actualizarTipoAlmacen(request, response));
                delete("/tiposAlmacen/delete/:id", CONTENT_TYPE, (request, response) -> eliminarTipoAlmacen(request, response));
                get("/tiposAlmacen/clear", CONTENT_TYPE, (request, response) -> limpiarCache(request, response));
            });
        });

    }
    
    
    private void validarContentType(final Request request, final Response response) {
        if (!StringUtils.equals(request.contentType(), CONTENT_TYPE)) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Fallo en el tipo de content-type en el request");
            }
            response.type(CONTENT_TYPE);
            halt(500, new ResultResponse.Builder().code(500).message("Error en el Content-Type").build().aJson());
        }
    }

    private ResultResponse limpiarCache(final Request request, final Response response) {
        validarContentType(request, response);

        tipoAlmacenServicio.limpiarCache();
        response.type(CONTENT_TYPE);
        return new ResultResponse.Builder().code(200).message("Cache limpio").build();

    }



    private ResultResponse todosLosTiposAlmacen(final Request request, final Response response) {
    	LOG.info("entro a todosLosTiposAlmacen()");
        validarContentType(request, response);

        final List<TipoAlmacenServicioDto> todosLosTiposAlmacen = tipoAlmacenServicio.obtenerTodasLosTiposAlmacen();

        response.type(CONTENT_TYPE);
        if (CollectionUtils.isNotEmpty(todosLosTiposAlmacen)) {
            return new ResultResponse.Builder()
                    .object(TipoAlmacenDtoMaper.INSTANCE.convertirListaTipoAlmacenServicioDtoATipoAlmacenRestDto(todosLosTiposAlmacen))
                    .build();
        }

        response.status(500);
        return new ResultResponse.Builder().code(500).message("Fallo al obtener todos los tipos de almacen").build();
    }
    
    private ResultResponse insertarTipoAlmacen(final Request request, final Response response) {
    	 validarContentType(request,response);
    	 final TipoAlmacenRestDto tipoAlmacen = JsonDto.aJson(request.body(),TipoAlmacenRestDto.class);
    	 final TipoAlmacenServicioDto tipoAlmacenServicioDto = TipoAlmacenDtoMaper.INSTANCE.tipoAlmacenRestDtoAtipoAlmacenServicioDto(tipoAlmacen);
    	 final Boolean isCategoriaInsertada = tipoAlmacenServicio.crearTipoAlmacen(tipoAlmacenServicioDto);
    	 
    	 response.type(CONTENT_TYPE);
    	 if(isCategoriaInsertada) {
    		 
    		 return new ResultResponse.Builder().build();
    	 }
    	 
    	 response.status(500);
    	 return new ResultResponse.Builder().code(500).message("Fallo al insertar el usuario").build();
    	
    }
    
 private ResultResponse actualizarTipoAlmacen(final Request request, final Response response) {
   	 validarContentType(request,response);
   	 final TipoAlmacenRestDto tipoAlmacen = JsonDto.aJson(request.body(),TipoAlmacenRestDto.class);
   	 final TipoAlmacenServicioDto tipoAlmacenServicioDto = TipoAlmacenDtoMaper.INSTANCE.tipoAlmacenRestDtoAtipoAlmacenServicioDto(tipoAlmacen);
   	 final Boolean isTipoAlmacenActualizada = tipoAlmacenServicio.actualizarTipoAlmacen(tipoAlmacenServicioDto);
   	 
   	 response.type(CONTENT_TYPE);
   	 if(isTipoAlmacenActualizada) {
   		 
   		 return new ResultResponse.Builder().build();
   	 }
   	 
   	 response.status(500);
   	 return new ResultResponse.Builder().code(500).message("Fallo al actualizar el tipo de almacen").build();
   	
   }    
    
  private ResultResponse eliminarTipoAlmacen(final Request request, final Response response) {
      	 validarContentType(request,response);
      	 String id = request.params(":id");
      	 TipoAlmacenServicioDto tipoAlmacenServicioDto = new TipoAlmacenServicioDto();
      	 tipoAlmacenServicioDto.setCodigo(Integer.parseInt(id));
      	 final Boolean isTipoAlmacenEliminada = tipoAlmacenServicio.eliminaTipoAlmacen(tipoAlmacenServicioDto);
 
      	 
      	 response.type(CONTENT_TYPE);
      	 if(isTipoAlmacenEliminada) {
      		 
      		 return new ResultResponse.Builder().build();
      	 }
      	 
      	 response.status(500);
      	 return new ResultResponse.Builder().code(500).message("Fallo al eliminar el tipo de almacen").build();
      	
      }    
  
 private ResultResponse obtenerUnTipoAlmacen(final Request request, final Response response) {
    	LOG.info("entro a obtenerUnTipoAlmacen()");
        validarContentType(request, response);
        
        String id = request.params(":id");
        TipoAlmacenServicioDto tipoAlmacenServicioDto = new TipoAlmacenServicioDto();
        tipoAlmacenServicioDto.setCodigo(Integer.parseInt(id));
        tipoAlmacenServicioDto = tipoAlmacenServicio.obtenerUnTipoAlmacen(tipoAlmacenServicioDto);
	   	 
	   	 response.type(CONTENT_TYPE);
	   	 if(tipoAlmacenServicioDto != null) {
	   		 
	   	  return new ResultResponse.Builder()
                    .object(TipoAlmacenDtoMaper.INSTANCE.tipoAlmacenServicioDtoAtipoAlmacenRestDto(tipoAlmacenServicioDto))
                    .build();
	   	 }
	   	 
	   	 response.status(500);
	   	 return new ResultResponse.Builder().code(500).message("Fallo al obtener el tipo de almacen").build();

   }
}
