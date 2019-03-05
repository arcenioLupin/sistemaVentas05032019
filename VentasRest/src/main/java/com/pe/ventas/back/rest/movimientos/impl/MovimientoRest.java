package com.pe.ventas.back.rest.movimientos.impl;

import static com.pe.ventas.back.dtos.base.Constantes.CONTENT_TYPE;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.path;
import static spark.Spark.put;
import java.text.ParseException;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pe.ventas.back.dtos.rest.ResultResponse;
import com.pe.ventas.back.dtos.rest.movimientos.MovimientoRestDto;
import com.pe.ventas.back.dtos.servicios.movimientos.MovimientoServicioDto;
import com.pe.ventas.back.rest.usuarios.IVentaRest;
import com.pe.ventas.back.servicios.movimientos.IMovimientoServicio;
import com.pe.ventas.back.utilidades.mapeos.movimientos.MovimientoDtoMaper;


import spark.Request;
import spark.Response;


@Component("movimientoRest")
public class MovimientoRest implements IVentaRest {
	
	private static final Logger LOG = LogManager.getLogger(MovimientoRest.class);
	
	@Autowired
	IMovimientoServicio movimientoServicio;
	
	  @Override
	    public void routers() {

	        path("/api", () -> {
	            path("/v2", () -> {
	                get("/movimientos/estadoDelServicio", CONTENT_TYPE,
	                        (request, response) -> new ResultResponse.Builder().code(200).message("Funcionando").build());
	               
	                get("/movimientos", CONTENT_TYPE, (request, response) -> todasLosMovimientos(request, response));
	                get("/movimientos/movimiento/:id", CONTENT_TYPE, (request, response) -> obtenerUnMovimiento(request, response));
	                put("/movimientos", CONTENT_TYPE, (request, response) -> insertarMovimiento(request, response));
	                put("/movimientos/actualizar", CONTENT_TYPE, (request, response) -> actualizarMovimiento(request, response));
	                delete("/movimientos/delete/:id", CONTENT_TYPE, (request, response) -> eliminarMovimiento(request, response));
	                get("/movimientos/clear", CONTENT_TYPE, (request, response) -> limpiarCache(request, response));
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

	        movimientoServicio.limpiarCache();
	        response.type(CONTENT_TYPE);
	        return new ResultResponse.Builder().code(200).message("Cache limpio").build();

	    }  
	    
	    private ResultResponse todasLosMovimientos(final Request request, final Response response) {
	    	LOG.info("entro a todasLosMovimientos()");
	        validarContentType(request, response);

	        final List<MovimientoServicioDto> todosLosMovimientos = movimientoServicio.obtenerTodosLosMovimientos();

	        response.type(CONTENT_TYPE);
	        if (CollectionUtils.isNotEmpty(todosLosMovimientos)) {
	            return new ResultResponse.Builder()
	                    .object(MovimientoDtoMaper.INSTANCE.convertirListaMovimientoServicioDtoAMovimientoRestDto(todosLosMovimientos))
	                    .build();
	        }

	        response.status(500);
	        return new ResultResponse.Builder().code(500).message("Fallo al obtener todos los movimientos").build();
	    }   
	    
	    private ResultResponse insertarMovimiento(final Request request, final Response response) throws ParseException {
		   	 validarContentType(request,response);
		   	 final String FORMATO_FECHA = "dd/MM/yyyy";
		   	 final String vacacionesJSON = request.body();
		   	 final Gson gson = new GsonBuilder().setDateFormat(FORMATO_FECHA).create();
		   	 final MovimientoRestDto movimiento = gson.fromJson(vacacionesJSON, MovimientoRestDto.class);
		   	 final MovimientoServicioDto movimientoServicioDto = MovimientoDtoMaper.INSTANCE.movimientoRestDtoAmovimientoServicioDto(movimiento);
		   	 final Boolean isMovimientoInsertado = movimientoServicio.crearMovimiento(movimientoServicioDto);
		   	 
		   	 response.type(CONTENT_TYPE);
		   	 if(isMovimientoInsertado) {
		   		 
		   		 return new ResultResponse.Builder().build();
		   	 }
		   	 
		   	 response.status(500);
		   	 return new ResultResponse.Builder().code(500).message("Fallo al insertar el movimiento").build();
	   	
	   }    
	    
		 private ResultResponse actualizarMovimiento(final Request request, final Response response) {
		   	 validarContentType(request,response);
		   	 final String FORMATO_FECHA = "dd/MM/yyyy";
		   	 final String vacacionesJSON = request.body();
		   	 final Gson gson = new GsonBuilder().setDateFormat(FORMATO_FECHA).create();
		   	 final MovimientoRestDto movimiento = gson.fromJson(vacacionesJSON, MovimientoRestDto.class);
		    final MovimientoServicioDto movimientoServicioDto = MovimientoDtoMaper.INSTANCE.movimientoRestDtoAmovimientoServicioDto(movimiento);
		    final Boolean isMovimientoActualizado = movimientoServicio.actualizarMovimiento(movimientoServicioDto);
		   	 
		   	 response.type(CONTENT_TYPE);
		   	 if(isMovimientoActualizado) {
		   		 
		   		 return new ResultResponse.Builder().build();
		   	 }
		   	 
		   	 response.status(500);
		   	 return new ResultResponse.Builder().code(500).message("Fallo al actualizar el movimiento").build();
		   	
		   }

		 private ResultResponse eliminarMovimiento(final Request request, final Response response) {
		      	 validarContentType(request,response);		      	
		      	 String id = request.params(":id");
		      	 MovimientoServicioDto movimientoServicioDto = new MovimientoServicioDto();
		      	movimientoServicioDto.setCodigo(Integer.parseInt(id));
		      	 final Boolean isMovimientoEliminado = movimientoServicio.eliminaMovimiento(movimientoServicioDto);
		      	 
		      	 response.type(CONTENT_TYPE);
		      	 if(isMovimientoEliminado) {
		      		 
		      		 return new ResultResponse.Builder().build();
		      	 }
		      	 
		      	 response.status(500);
		      	 return new ResultResponse.Builder().code(500).message("Fallo al eliminar el movimiento").build();
		      	
		      }  
		 private ResultResponse obtenerUnMovimiento(final Request request, final Response response) {
		    	LOG.info("entro a obtenerUnMovimiento()");
		        validarContentType(request, response);
		        
		        String id = request.params(":id");	    	
		        MovimientoServicioDto movimientoServicioDto = new MovimientoServicioDto();
		        movimientoServicioDto.setCodigo(Integer.parseInt(id));
		        movimientoServicioDto = movimientoServicio.obtenerUnMovimiento(movimientoServicioDto);
			   	 
			   	 response.type(CONTENT_TYPE);
			   	 if(movimientoServicioDto != null) {
			   		 
			   	  return new ResultResponse.Builder()
		                    .object(MovimientoDtoMaper.INSTANCE.movimientoServicioDtoAmovimientoRestDto(movimientoServicioDto))
		                    .build();
			   	 }
			   	 
			   	 response.status(500);
			   	 return new ResultResponse.Builder().code(500).message("Fallo al obtener el movimiento").build();

	    }	 

}
