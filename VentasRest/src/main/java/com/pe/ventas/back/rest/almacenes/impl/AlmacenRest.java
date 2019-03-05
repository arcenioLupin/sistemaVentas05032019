package com.pe.ventas.back.rest.almacenes.impl;

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
import com.pe.ventas.back.dtos.rest.almacenes.AlmacenRestDto;
import com.pe.ventas.back.dtos.servicios.almacenes.AlmacenServicioDto;
import com.pe.ventas.back.rest.usuarios.IVentaRest;
import com.pe.ventas.back.servicios.almacenes.IAlmacenServicio;
import com.pe.ventas.back.utilidades.mapeos.almacenes.AlmacenDtoMaper;
import spark.Request;
import spark.Response;

@Component("almacenRest")
public class AlmacenRest implements IVentaRest {
	
	private static final Logger LOG = LogManager.getLogger(AlmacenRest.class);
	
	 @Autowired
	 private IAlmacenServicio almacenServicio;
	 
	    @Override
	    public void routers() {

	        path("/api", () -> {
	            path("/v2", () -> {
	                get("/almacenes/estadoDelServicio", CONTENT_TYPE,
	                        (request, response) -> new ResultResponse.Builder().code(200).message("Funcionando").build());
	               
	                get("/almacenes", CONTENT_TYPE, (request, response) -> todosLosAlmacenes(request, response));
	                get("/almacenes/almacen/:id", CONTENT_TYPE, (request, response) -> obtenerUnAlmacen(request, response));
	                put("/almacenes", CONTENT_TYPE, (request, response) -> insertarAlmacen(request, response));
	                put("/almacenes/actualizar", CONTENT_TYPE, (request, response) -> actualizarAlmacen(request, response));
	                delete("/almacenes/delete/:id", CONTENT_TYPE, (request, response) -> eliminarAlmacen(request, response));
	                get("/almacenes/clear", CONTENT_TYPE, (request, response) -> limpiarCache(request, response));
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

	        almacenServicio.limpiarCache();
	        response.type(CONTENT_TYPE);
	        return new ResultResponse.Builder().code(200).message("Cache limpio").build();

	    }



	  private ResultResponse todosLosAlmacenes(final Request request, final Response response) {
	    	LOG.info("entro a todosLosAlmacenes()");
	        validarContentType(request, response);

	        final List<AlmacenServicioDto> todosLosAlmacenes = almacenServicio.obtenerTodosLosAlmacenes();

	        response.type(CONTENT_TYPE);
	        if (CollectionUtils.isNotEmpty(todosLosAlmacenes)) {
	            return new ResultResponse.Builder()
	                    .object(AlmacenDtoMaper.INSTANCE.convertirListaAlmServicioDtoAAlmRestDto(todosLosAlmacenes))
	                    .build();
	        }

	        response.status(500);
	        return new ResultResponse.Builder().code(500).message("Fallo al obtener todos los almacenes").build();
	    }
	    
	  private ResultResponse insertarAlmacen(final Request request, final Response response) {
	    	 validarContentType(request,response);
	    	 final AlmacenRestDto almacen = JsonDto.aJson(request.body(),AlmacenRestDto.class);
	    	 final AlmacenServicioDto almacenServicioDto = AlmacenDtoMaper.INSTANCE.almacenRestDtoAalmacenServicioDto(almacen);
	    	 final Boolean isAlmacenInsertado = almacenServicio.crearAlmacen(almacenServicioDto);
	    	 
	    	 response.type(CONTENT_TYPE);
	    	 if(isAlmacenInsertado) {
	    		 
	    		 return new ResultResponse.Builder().build();
	    	 }
	    	 
	    	 response.status(500);
	    	 return new ResultResponse.Builder().code(500).message("Fallo al insertar el almacen").build();
	    	
	    }
	    
	 private ResultResponse actualizarAlmacen(final Request request, final Response response) {
	   	 validarContentType(request,response);
    	 final AlmacenRestDto almacen = JsonDto.aJson(request.body(),AlmacenRestDto.class);
    	 final AlmacenServicioDto almacenServicioDto = AlmacenDtoMaper.INSTANCE.almacenRestDtoAalmacenServicioDto(almacen);
	   	 final Boolean isAlmacenActualizado = almacenServicio.actualizarAlmacen(almacenServicioDto);
	   	 
	   	 response.type(CONTENT_TYPE);
	   	 if(isAlmacenActualizado) {
	   		 
	   		 return new ResultResponse.Builder().build();
	   	 }
	   	 
	   	 response.status(500);
	   	 return new ResultResponse.Builder().code(500).message("Fallo al actualizar el almacen").build();
	   	
	   }    
	    
	 private ResultResponse eliminarAlmacen(final Request request, final Response response) {
	      	 validarContentType(request,response);
	      	 
	      	 String id = request.params(":id");	      	
	      	 AlmacenServicioDto almacenServicioDto = new AlmacenServicioDto();
	      	 almacenServicioDto.setCodigo(Integer.parseInt(id));
	      	 final Boolean isAlmacenEliminado = almacenServicio.eliminaAlmacen(almacenServicioDto);
	 
	      	 
	      	 response.type(CONTENT_TYPE);
	      	 if(isAlmacenEliminado) {
	      		 
	      		 return new ResultResponse.Builder().build();
	      	 }
	      	 
	      	 response.status(500);
	      	 return new ResultResponse.Builder().code(500).message("Fallo al eliminar el almacen").build();
	      	
	      }    
	  
	 private ResultResponse obtenerUnAlmacen(final Request request, final Response response) {
	    	LOG.info("entro a obtenerUnAlmacen()");
	        validarContentType(request, response);
	        
	        String id = request.params(":id");	    	
	        AlmacenServicioDto almacenServicioDto = new AlmacenServicioDto();
	        almacenServicioDto.setCodigo(Integer.parseInt(id));
	        almacenServicioDto = almacenServicio.obtenerUnAlmacen(almacenServicioDto);
		   	 
		   	 response.type(CONTENT_TYPE);
		   	 if(almacenServicioDto != null) {
		   		 
		   	  return new ResultResponse.Builder()
	                    .object(AlmacenDtoMaper.INSTANCE.almacenServicioDtoAalmacenRestDto(almacenServicioDto))
	                    .build();
		   	 }
		   	 
		   	 response.status(500);
		   	 return new ResultResponse.Builder().code(500).message("Fallo al obtener el almacen").build();

       }


}
