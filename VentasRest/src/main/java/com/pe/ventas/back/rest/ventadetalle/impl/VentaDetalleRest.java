package com.pe.ventas.back.rest.ventadetalle.impl;

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
import com.pe.ventas.back.dtos.rest.ventadetalle.VentaDetalleRestDto;
import com.pe.ventas.back.dtos.servicios.ventadetalle.VentaDetalleServicioDto;
import com.pe.ventas.back.rest.ventadetalle.impl.VentaDetalleRest;
import com.pe.ventas.back.rest.usuarios.IVentaRest;
import com.pe.ventas.back.servicios.ventadetalle.IVentaDetalleServicio;
import com.pe.ventas.back.utilidades.mapeos.ventadetalle.VentaDetalleDtoMaper;

import spark.Request;
import spark.Response;

@Component("ventaDetalleRest")
public class VentaDetalleRest implements IVentaRest {
	
	 private static final Logger LOG = LogManager.getLogger(VentaDetalleRest.class);

	    @Autowired
	    private IVentaDetalleServicio ventaDetalleServicio;

	    @Override
	    public void routers() {

	        path("/api", () -> {
	            path("/v2", () -> {
	                get("/ventadetalles/estadoDelServicio", CONTENT_TYPE,
	                        (request, response) -> new ResultResponse.Builder().code(200).message("Funcionando").build());
	               
	                get("/ventadetalles", CONTENT_TYPE, (request, response) -> todosVentaDetalle(request, response));
	                get("/ventadetalles/ventadetalle/:idVenta/:idPosicion", CONTENT_TYPE, (request, response) -> obtenerUnVentaDetalle(request, response));
	                put("/ventadetalles", CONTENT_TYPE, (request, response) -> insertarVentaDetalle(request, response));
	                put("/ventadetalles/actualizar", CONTENT_TYPE, (request, response) -> actualizarVentaDetalle(request, response));
	                delete("/ventadetalles/delete/:idVenta/:idPosicion", CONTENT_TYPE, (request, response) -> eliminarVentaDetalle(request, response));
	                get("/ventadetalles/clear", CONTENT_TYPE, (request, response) -> limpiarCache(request, response));
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

	        ventaDetalleServicio.limpiarCache();
	        response.type(CONTENT_TYPE);
	        return new ResultResponse.Builder().code(200).message("Cache limpio").build();

	    }



	    private ResultResponse todosVentaDetalle(final Request request, final Response response) {
	    	LOG.info("entro a todosVentaDetalle()");
	        validarContentType(request, response);

	        final List<VentaDetalleServicioDto> todosLosVentaDetalle = ventaDetalleServicio.obtenerTodosVentaDetalle();

	        response.type(CONTENT_TYPE);
	        if (CollectionUtils.isNotEmpty(todosLosVentaDetalle)) {
	            return new ResultResponse.Builder()
	            		   .object(VentaDetalleDtoMaper.INSTANCE.convertirListaVentaDetalleServicioDtoVentaDetalleRestDto(todosLosVentaDetalle))
	            		   .build();
	        }

	        response.status(500);
	        return new ResultResponse.Builder().code(500).message("Fallo al obtener todos las ventas detalles").build();
	    }
	    
	    private ResultResponse insertarVentaDetalle(final Request request, final Response response) {
	    	 validarContentType(request,response);
	    	 final VentaDetalleRestDto ventaDetalle = JsonDto.aJson(request.body(),VentaDetalleRestDto.class);
	    	 final VentaDetalleServicioDto ventaDetalleServicioDto = VentaDetalleDtoMaper.INSTANCE.VentaDetalleRestDtoAVentaDetalleServicioDto(ventaDetalle);
	    	 final Boolean isVentaDetalleInsertado = ventaDetalleServicio.crearVentaDetalle(ventaDetalleServicioDto);
	    	 
	    	 response.type(CONTENT_TYPE);
	    	 if(isVentaDetalleInsertado) {
	    		 
	    		 return new ResultResponse.Builder().build();
	    	 }
	    	 
	    	 response.status(500);
	    	 return new ResultResponse.Builder().code(500).message("Fallo al insertar el  ventas detalles").build();
	    	
	    }
	    
	 private ResultResponse actualizarVentaDetalle(final Request request, final Response response) {
	   	 validarContentType(request,response);
	 final VentaDetalleRestDto VentaDetalle = JsonDto.aJson(request.body(),VentaDetalleRestDto.class);
	 final VentaDetalleServicioDto ventaDetalleServicioDto = VentaDetalleDtoMaper.INSTANCE.VentaDetalleRestDtoAVentaDetalleServicioDto(VentaDetalle);
	   	 final Boolean isVentaDetalleActualizado = ventaDetalleServicio.actualizarVentaDetalle(ventaDetalleServicioDto);
	   	 
	   	 response.type(CONTENT_TYPE);
	   	 if(isVentaDetalleActualizado) {
	   		 
	   		 return new ResultResponse.Builder().build();
	   	 }
	   	 
	   	 response.status(500);
	   	 return new ResultResponse.Builder().code(500).message("Fallo al actualizar el ventas detalles").build();
	   	
	   }    
	    
	  private ResultResponse eliminarVentaDetalle(final Request request, final Response response) {
	      	 validarContentType(request,response);
	      	 String idVenta = request.params(":idVenta");
	      	 String idPosicion = request.params(":idPosicion");
	      	VentaDetalleServicioDto ventaDetalleServicioDto = new VentaDetalleServicioDto();
	      	ventaDetalleServicioDto.setCodVenta(Integer.parseInt(idVenta));
	      	ventaDetalleServicioDto.setPosicionVenta(Integer.parseInt(idPosicion));
	      	final Boolean isVentaDetalleEliminado = ventaDetalleServicio.eliminaVentaDetalle(ventaDetalleServicioDto);
	 
	      	 
	      	 response.type(CONTENT_TYPE);
	      	 if(isVentaDetalleEliminado) {
	      		 
	      		 return new ResultResponse.Builder().build();
	      	 }
	      	 
	      	 response.status(500);
	      	 return new ResultResponse.Builder().code(500).message("Fallo al eliminar el ventas detalles").build();
	      	
	      }    
	  
	 private ResultResponse obtenerUnVentaDetalle(final Request request, final Response response) {
	    	LOG.info("entro a obtenerUnVentaDetalle()");
	        validarContentType(request, response);
	        
	        String idVenta = request.params(":idVenta");
	        String idPosicion = request.params(":idPosicion");
	      	VentaDetalleServicioDto ventaDetalleServicioDto = new VentaDetalleServicioDto();
	      	ventaDetalleServicioDto.setCodVenta(Integer.parseInt(idVenta));
	      	ventaDetalleServicioDto.setPosicionVenta(Integer.parseInt(idPosicion));
	      	ventaDetalleServicioDto = ventaDetalleServicio.obtenerUnaVentaDetalle(ventaDetalleServicioDto);
		   	 
		   	 response.type(CONTENT_TYPE);
		   	 if(ventaDetalleServicioDto != null) {
		   		 
		   	  return new ResultResponse.Builder()
	                    .object(VentaDetalleDtoMaper.INSTANCE.VentaDetalleServicioDtoAVentaDetalleRestDto(ventaDetalleServicioDto))
	                    .build();
		   	 }
		   	 
		   	 response.status(500);
		   	 return new ResultResponse.Builder().code(500).message("Fallo al eliminar el ventas detalles").build();

}
	

}
