package com.pe.ventas.back.rest.monedas.impl;

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
import com.pe.ventas.back.dtos.rest.monedas.MonedaRestDto;
import com.pe.ventas.back.dtos.servicios.monedas.MonedaServicioDto;
import com.pe.ventas.back.rest.usuarios.IVentaRest;
import com.pe.ventas.back.servicios.monedas.IMonedaServicio;
import com.pe.ventas.back.utilidades.mapeos.monedas.MonedaDtoMaper;

import spark.Request;
import spark.Response;

@Component("monedaRest")
public class MonedaRest implements IVentaRest {
	
	private static final Logger LOG = LogManager.getLogger(MonedaRest.class);
	
	@Autowired
	private IMonedaServicio monedaServicio;
	
	
    @Override
	public void routers() {
	
	        path("/api", () -> {
	            path("/v2", () -> {
	                get("/monedas/estadoDelServicio", CONTENT_TYPE,
	                        (request, response) -> new ResultResponse.Builder().code(200).message("Funcionando").build());
	               
	                get("/monedas", CONTENT_TYPE, (request, response) -> todasLasMonedas(request, response));
	                get("/monedas/moneda/:id", CONTENT_TYPE, (request, response) -> obtenerUnaMoneda(request, response));
	                put("/monedas", CONTENT_TYPE, (request, response) -> insertarMoneda(request, response));
	                put("/monedas/actualizar", CONTENT_TYPE, (request, response) -> actualizarMoneda(request, response));
	                delete("/monedas/delete/:id", CONTENT_TYPE, (request, response) -> eliminarMoneda(request, response));
	                get("/monedas/clear", CONTENT_TYPE, (request, response) -> limpiarCache(request, response));
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
	
	        monedaServicio.limpiarCache();
	        response.type(CONTENT_TYPE);
	        return new ResultResponse.Builder().code(200).message("Cache limpio").build();
	
	    }    
    
    private ResultResponse todasLasMonedas(final Request request, final Response response) {
	    	LOG.info("entro a todasLasMonedas()");
	        validarContentType(request, response);
	
	        final List<MonedaServicioDto> todasLasMonedas = monedaServicio.obtenerTodasLasMonedas();
	
	        response.type(CONTENT_TYPE);
	        if (CollectionUtils.isNotEmpty(todasLasMonedas)) {
	            return new ResultResponse.Builder()
	                    .object(MonedaDtoMaper.INSTANCE.convertirListaMonedaServicioDtoAMonedaRestDto(todasLasMonedas))
	                    .build();
	        }
	
	        response.status(500);
	        return new ResultResponse.Builder().code(500).message("Fallo al obtener todos las monedas").build();
	    }  
    
	private ResultResponse insertarMoneda(final Request request, final Response response) {
	   	 validarContentType(request,response);
	   	 final MonedaRestDto moneda = JsonDto.aJson(request.body(),MonedaRestDto.class);
	   	 final MonedaServicioDto monedaServicioDto = MonedaDtoMaper.INSTANCE.monedaRestDtoAmonedaServicioDto(moneda);
	   	 final Boolean isMonedaInsertada = monedaServicio.crearMoneda(monedaServicioDto);
	   	 
	   	 response.type(CONTENT_TYPE);
	   	 if(isMonedaInsertada) {
	   		 
	   		 return new ResultResponse.Builder().build();
	   	 }
	   	 
	   	 response.status(500);
	   	 return new ResultResponse.Builder().code(500).message("Fallo al insertar el usuario").build();
	   	
	   }
	    
	private ResultResponse actualizarMoneda(final Request request, final Response response) {
		   	 validarContentType(request,response);
	    	 final MonedaRestDto moneda = JsonDto.aJson(request.body(),MonedaRestDto.class);
	    	 final MonedaServicioDto monedaServicioDto = MonedaDtoMaper.INSTANCE.monedaRestDtoAmonedaServicioDto(moneda);
		   	 final Boolean isMonedaActualizada = monedaServicio.actualizarMoneda(monedaServicioDto);
		   	 
		   	 response.type(CONTENT_TYPE);
		   	 if(isMonedaActualizada) {
		   		 
		   		 return new ResultResponse.Builder().build();
		   	 }
		   	 
		   	 response.status(500);
		   	 return new ResultResponse.Builder().code(500).message("Fallo al actualizar la moneda").build();
		   	
		   }  

   private ResultResponse eliminarMoneda(final Request request, final Response response) {
	      	 validarContentType(request,response);
	      	 String id = request.params(":id");	      	
	      	MonedaServicioDto monedaServicioDto = new MonedaServicioDto();
	      	monedaServicioDto.setCodigo(id);
	      	 final Boolean isMonedaEliminada = monedaServicio.eliminaMoneda(monedaServicioDto);
	 
	      	 
	      	 response.type(CONTENT_TYPE);
	      	 if(isMonedaEliminada) {
	      		 
	      		 return new ResultResponse.Builder().build();
	      	 }
	      	 
	      	 response.status(500);
	      	 return new ResultResponse.Builder().code(500).message("Fallo al eliminar el moneda").build();
	      	
	      } 
	  
   private ResultResponse obtenerUnaMoneda(final Request request, final Response response) {
		    	LOG.info("entro a obtenerUnaMoneda()");
		        validarContentType(request, response);
		        
		        String id = request.params(":id");
		    	
		        MonedaServicioDto monedaServicioDto = new MonedaServicioDto();
		        monedaServicioDto.setCodigo(id);
		        monedaServicioDto = monedaServicio.obtenerUnaMoneda(monedaServicioDto);
			   	 
			   	 response.type(CONTENT_TYPE);
			   	 if(monedaServicioDto != null) {
			   		 
			   	  return new ResultResponse.Builder()
		                    .object(MonedaDtoMaper.INSTANCE.monedaServicioDtoAmonedaRestDto(monedaServicioDto))
		                    .build();
			   	 }
			   	 
			   	 response.status(500);
			   	 return new ResultResponse.Builder().code(500).message("Fallo al obtener la categoria").build();

	       }	  
    

}
