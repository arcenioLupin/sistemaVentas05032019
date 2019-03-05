package com.pe.ventas.back.rest.unidadmedida.impl;

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
import com.pe.ventas.back.dtos.rest.unidadmedida.UnidadMedidaRestDto;
import com.pe.ventas.back.dtos.servicios.unidadmedida.UnidadMedidaServicioDto;
import com.pe.ventas.back.rest.usuarios.IVentaRest;
import com.pe.ventas.back.servicios.unidadmedida.IUnidadMedidaServicio;
import com.pe.ventas.back.utilidades.mapeos.unidadmedida.UnidadMedidaDtoMaper;
import spark.Request;
import spark.Response;


@Component("unidadMedidaRest")
public class UnidadMedidaRest implements IVentaRest {
	
	 private static final Logger LOG = LogManager.getLogger(UnidadMedidaRest.class);
	 
	 @Autowired
	 private IUnidadMedidaServicio unidadMedidaServicio;
	 
	    @Override
		public void routers() {
		
		        path("/api", () -> {
		            path("/v2", () -> {
		                get("/unidadmedidas/estadoDelServicio", CONTENT_TYPE,
		                        (request, response) -> new ResultResponse.Builder().code(200).message("Funcionando").build());
		               
		                get("/unidadmedidas", CONTENT_TYPE, (request, response) -> todasLasUnidades(request, response));
		                get("/unidadmedidas/unidadmedida/:id", CONTENT_TYPE, (request, response) -> obtenerUnaUnidad(request, response));
		                put("/unidadmedidas", CONTENT_TYPE, (request, response) -> insertarUnidad(request, response));
		                put("/unidadmedidas/actualizar", CONTENT_TYPE, (request, response) -> actualizarUnidad(request, response));
		                delete("/unidadmedidas/delete/:id", CONTENT_TYPE, (request, response) -> eliminarUnidadMedida(request, response));
		                get("/unidadmedidas/clear", CONTENT_TYPE, (request, response) -> limpiarCache(request, response));
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
	
	        unidadMedidaServicio.limpiarCache();
	        response.type(CONTENT_TYPE);
	        return new ResultResponse.Builder().code(200).message("Cache limpio").build();
	
	    } 

	    private ResultResponse todasLasUnidades(final Request request, final Response response) {
	    	LOG.info("entro a todasLasUnidades()");
	        validarContentType(request, response);
	
	        final List<UnidadMedidaServicioDto> todasLasUnidades = unidadMedidaServicio.obtenerTodasLasUnidadMedida();
	
	        response.type(CONTENT_TYPE);
	        if (CollectionUtils.isNotEmpty(todasLasUnidades)) {
	            return new ResultResponse.Builder()
	                    .object(UnidadMedidaDtoMaper.INSTANCE.convertirListaunidadMedidaServicioDtoAunidadMedidaRestDto(todasLasUnidades))
	                    .build();
	        }
	
	        response.status(500);
	        return new ResultResponse.Builder().code(500).message("Fallo al obtener todos las unidades de medida").build();
	    } 
	    
	 private ResultResponse insertarUnidad(final Request request, final Response response) {
		   	 validarContentType(request,response);
		   	 final UnidadMedidaRestDto unidadMedida = JsonDto.aJson(request.body(),UnidadMedidaRestDto.class);
		   	 final UnidadMedidaServicioDto unidadMedidaServicioDto = UnidadMedidaDtoMaper.INSTANCE.unidadMedidaRestDtoAunidadMedidaServicioDto(unidadMedida);
		   	 final Boolean isUnidadMedidaInsertada = unidadMedidaServicio.crearUnidadMedida(unidadMedidaServicioDto);
		   	 
		   	 response.type(CONTENT_TYPE);
		   	 if(isUnidadMedidaInsertada) {
		   		 
		   		 return new ResultResponse.Builder().build();
		   	 }
		   	 
		   	 response.status(500);
		   	 return new ResultResponse.Builder().code(500).message("Fallo al insertar el unidad de medida").build();
		   	
		   }	

	  private ResultResponse obtenerUnaUnidad(final Request request, final Response response) {
	    	LOG.info("entro a obtenerUnaUnidad()");
	        validarContentType(request, response);
	        
	        String id = request.params(":id");
	    	
	        UnidadMedidaServicioDto unidadMedidaServicioDto = new UnidadMedidaServicioDto();
	        unidadMedidaServicioDto.setCodigo(id);
	        unidadMedidaServicioDto = unidadMedidaServicio.obtenerUnaUnidadMedida(unidadMedidaServicioDto);
		   	 
		   	 response.type(CONTENT_TYPE);
		   	 if(unidadMedidaServicioDto != null) {
		   		 
		   	  return new ResultResponse.Builder()
	                    .object(UnidadMedidaDtoMaper.INSTANCE.unidadMedidaServicioDtoAunidadMedidaRestDto(unidadMedidaServicioDto))
	                    .build();
		   	 }
		   	 
		   	 response.status(500);
		   	 return new ResultResponse.Builder().code(500).message("Fallo al obtener la unidad de medida").build();

      }
	  
	private ResultResponse actualizarUnidad(final Request request, final Response response) {
		   	 validarContentType(request,response);
		   	 final UnidadMedidaRestDto unidadMedida = JsonDto.aJson(request.body(),UnidadMedidaRestDto.class);
	    	 final UnidadMedidaServicioDto monedaServicioDto = UnidadMedidaDtoMaper.INSTANCE.unidadMedidaRestDtoAunidadMedidaServicioDto(unidadMedida);
		   	 final Boolean isUnidadMedidaActualizada = unidadMedidaServicio.actualizarUnidadMedida(monedaServicioDto);
		   	 
		   	 response.type(CONTENT_TYPE);
		   	 if(isUnidadMedidaActualizada) {
		   		 
		   		 return new ResultResponse.Builder().build();
		   	 }
		   	 
		   	 response.status(500);
		   	 return new ResultResponse.Builder().code(500).message("Fallo al actualizar la unidad de medida").build();
		   	
		   }

	 private ResultResponse eliminarUnidadMedida(final Request request, final Response response) {
		      	 validarContentType(request,response);
		      	 String id = request.params(":id");	      	
		      	 UnidadMedidaServicioDto unidadMedidaServicioDto = new UnidadMedidaServicioDto();
		      	 unidadMedidaServicioDto.setCodigo(id);
		      	 final Boolean isUnidadMedidaEliminada = unidadMedidaServicio.eliminaUnidadMedida(unidadMedidaServicioDto);
		 
		      	 
		      	 response.type(CONTENT_TYPE);
		      	 if(isUnidadMedidaEliminada) {
		      		 
		      		 return new ResultResponse.Builder().build();
		      	 }
		      	 
		      	 response.status(500);
		      	 return new ResultResponse.Builder().code(500).message("Fallo al eliminar el unidad de medida").build();
		      	
		      } 
	 
 	 

}
