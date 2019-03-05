package com.pe.ventas.back.rest.movimientoproducto.impl;

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
import com.pe.ventas.back.dtos.rest.movimientoproducto.MovimientoProductoRestDto;
import com.pe.ventas.back.dtos.servicios.movimientoproducto.MovimientoProductoServicioDto;
import com.pe.ventas.back.rest.movimientoproducto.impl.MovimientoProductoRest;
import com.pe.ventas.back.rest.usuarios.IVentaRest;
import com.pe.ventas.back.servicios.movimientoproducto.IMovimientoProductoServicio;
import com.pe.ventas.back.utilidades.mapeos.movimientoproducto.MovimientoProductoDtoMaper;

import spark.Request;
import spark.Response;

@Component("movimientoProductoRest")
public class MovimientoProductoRest implements IVentaRest {
	
	 private static final Logger LOG = LogManager.getLogger(MovimientoProductoRest.class);

	    @Autowired
	    private IMovimientoProductoServicio movimientoProductoServicio;

	    @Override
	    public void routers() {

	        path("/api", () -> {
	            path("/v2", () -> {
	                get("/movimientoproductos/estadoDelServicio", CONTENT_TYPE,
	                        (request, response) -> new ResultResponse.Builder().code(200).message("Funcionando").build());
	               
	                get("/movimientoproductos", CONTENT_TYPE, (request, response) -> todosMovimientoProducto(request, response));
	                get("/movimientoproductos/movimientoproducto/:idMovimiento/:idProducto", CONTENT_TYPE, (request, response) -> obtenerUnMovimientoProducto(request, response));
	                put("/movimientoproductos", CONTENT_TYPE, (request, response) -> insertarMovimientoProducto(request, response));
	                put("/movimientoproductos/actualizar", CONTENT_TYPE, (request, response) -> actualizarMovimientoProducto(request, response));
	                delete("/movimientoproductos/delete/:idMovimiento/:idProducto", CONTENT_TYPE, (request, response) -> eliminarMovimientoProducto(request, response));
	                get("/movimientoproductos/clear", CONTENT_TYPE, (request, response) -> limpiarCache(request, response));
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

	        movimientoProductoServicio.limpiarCache();
	        response.type(CONTENT_TYPE);
	        return new ResultResponse.Builder().code(200).message("Cache limpio").build();

	    }



	    private ResultResponse todosMovimientoProducto(final Request request, final Response response) {
	    	LOG.info("entro a todosMovimientoProducto()");
	        validarContentType(request, response);

	        final List<MovimientoProductoServicioDto> todosLosMovimientoProducto = movimientoProductoServicio.obtenerTodosMovimientosProductos();

	        response.type(CONTENT_TYPE);
	        if (CollectionUtils.isNotEmpty(todosLosMovimientoProducto)) {
	            return new ResultResponse.Builder()
	            		   .object(MovimientoProductoDtoMaper.INSTANCE.convertirListaMovimientoProductoServicioDtoMovimientoProductoRestDto(todosLosMovimientoProducto))
	            		   .build();
	        }

	        response.status(500);
	        return new ResultResponse.Builder().code(500).message("Fallo al obtener todos las almacen producto").build();
	    }
	    
	    private ResultResponse insertarMovimientoProducto(final Request request, final Response response) {
	    	 validarContentType(request,response);
	    	 final MovimientoProductoRestDto movimientoProducto = JsonDto.aJson(request.body(),MovimientoProductoRestDto.class);
	    	 final MovimientoProductoServicioDto movimientoProductoServicioDto = MovimientoProductoDtoMaper.INSTANCE.MovimientoProductoRestDtoAMovimientoProductoServicioDto(movimientoProducto);
	    	 final Boolean isMovimientoProductoInsertado = movimientoProductoServicio.crearMovimientoProducto(movimientoProductoServicioDto);
	    	 
	    	 response.type(CONTENT_TYPE);
	    	 if(isMovimientoProductoInsertado) {
	    		 
	    		 return new ResultResponse.Builder().build();
	    	 }
	    	 
	    	 response.status(500);
	    	 return new ResultResponse.Builder().code(500).message("Fallo al insertar el Movimiento Producto").build();
	    	
	    }
	    
	 private ResultResponse actualizarMovimientoProducto(final Request request, final Response response) {
	   	 validarContentType(request,response);
	 final MovimientoProductoRestDto movimientoProducto = JsonDto.aJson(request.body(),MovimientoProductoRestDto.class);
	 final MovimientoProductoServicioDto movimientoProductoServicioDto = MovimientoProductoDtoMaper.INSTANCE.MovimientoProductoRestDtoAMovimientoProductoServicioDto(movimientoProducto);
	   	 final Boolean isMovimientoProductoActualizado = movimientoProductoServicio.actualizarMovimientoProducto(movimientoProductoServicioDto);
	   	 
	   	 response.type(CONTENT_TYPE);
	   	 if(isMovimientoProductoActualizado) {
	   		 
	   		 return new ResultResponse.Builder().build();
	   	 }
	   	 
	   	 response.status(500);
	   	 return new ResultResponse.Builder().code(500).message("Fallo al actualizar el Movimiento Producto").build();
	   	
	   }    
	    
	  private ResultResponse eliminarMovimientoProducto(final Request request, final Response response) {
	      	 validarContentType(request,response);
	      	 String idMovimiento = request.params(":idMovimiento");
	      	 String idProducto = request.params(":idProducto");
	      	MovimientoProductoServicioDto movimientoProductoServicioDto = new MovimientoProductoServicioDto();
	      	movimientoProductoServicioDto.setCodMovimiento(Integer.parseInt(idMovimiento));
	      	movimientoProductoServicioDto.setCodProducto(Integer.parseInt(idProducto));
	      	final Boolean isMovimientoProductoEliminado = movimientoProductoServicio.eliminaMovimientoProducto(movimientoProductoServicioDto);
	 
	      	 
	      	 response.type(CONTENT_TYPE);
	      	 if(isMovimientoProductoEliminado) {
	      		 
	      		 return new ResultResponse.Builder().build();
	      	 }
	      	 
	      	 response.status(500);
	      	 return new ResultResponse.Builder().code(500).message("Fallo al eliminar el Movimiento Producto").build();
	      	
	      }    
	  
	 private ResultResponse obtenerUnMovimientoProducto(final Request request, final Response response) {
	    	LOG.info("entro a obtenerUnMovimientoProducto()");
	        validarContentType(request, response);
	        
	        String idMovimiento = request.params(":idMovimiento");
	        String idProducto = request.params(":idProducto");
	    	MovimientoProductoServicioDto movimientoProductoServicioDto = new MovimientoProductoServicioDto();
	      	movimientoProductoServicioDto.setCodMovimiento(Integer.parseInt(idMovimiento));
	      	movimientoProductoServicioDto.setCodProducto(Integer.parseInt(idProducto));
	      	movimientoProductoServicioDto = movimientoProductoServicio.obtenerUnMovimientoProducto(movimientoProductoServicioDto);
		   	 
		   	 response.type(CONTENT_TYPE);
		   	 if(movimientoProductoServicioDto != null) {
		   		 
		   	  return new ResultResponse.Builder()
	                    .object(MovimientoProductoDtoMaper.INSTANCE.MovimientoProductoServicioDtoAMovimientoProductoRestDto(movimientoProductoServicioDto))
	                    .build();
		   	 }
		   	 
		   	 response.status(500);
		   	 return new ResultResponse.Builder().code(500).message("Fallo al obtener el Movimiento Producto").build();

 }

}
