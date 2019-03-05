package com.pe.ventas.back.rest.venta.impl;

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
import com.pe.ventas.back.dtos.rest.ventas.VentaRestDto;
import com.pe.ventas.back.dtos.servicios.ventas.VentaServicioDto;
import com.pe.ventas.back.rest.venta.impl.VentaRest;
import com.pe.ventas.back.rest.usuarios.IVentaRest;
import com.pe.ventas.back.servicios.ventas.IVentaServicio;
import com.pe.ventas.back.utilidades.mapeos.ventas.VentaDtoMaper;

import spark.Request;
import spark.Response;

@Component("ventaRest")
public class VentaRest implements IVentaRest {
	
	
	private static final Logger LOG = LogManager.getLogger(VentaRest.class);
	
	 @Autowired
	 private IVentaServicio ventaServicio;
	 
	 @Override
	 public void routers() {

	        path("/api", () -> {
	            path("/v2", () -> {
	                get("/Ventas/estadoDelServicio", CONTENT_TYPE,
	                        (request, response) -> new ResultResponse.Builder().code(200).message("Funcionando").build());
	               
	                get("/ventas", CONTENT_TYPE, (request, response) -> todosLasVentas(request, response));
	                get("/ventas/venta/:id", CONTENT_TYPE, (request, response) -> obtenerUnaVenta(request, response));
	                put("/ventas", CONTENT_TYPE, (request, response) -> insertarVenta(request, response));
	                put("/ventas/actualizar", CONTENT_TYPE, (request, response) -> actualizarVenta(request, response));
	                delete("/ventas/delete/:id", CONTENT_TYPE, (request, response) -> eliminarVenta(request, response));
	                get("/ventas/clear", CONTENT_TYPE, (request, response) -> limpiarCache(request, response));
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

	        ventaServicio.limpiarCache();
	        response.type(CONTENT_TYPE);
	        return new ResultResponse.Builder().code(200).message("Cache limpio").build();

	    }
	    
	private ResultResponse todosLasVentas(final Request request, final Response response) {
	    	LOG.info("entro a todosLasVentas()");
	        validarContentType(request, response);

	        final List<VentaServicioDto> todosLasVentas = ventaServicio.obtenerTodasLasVentas();

	        response.type(CONTENT_TYPE);
	        if (CollectionUtils.isNotEmpty(todosLasVentas)) {
	            return new ResultResponse.Builder()
	                    .object(VentaDtoMaper.INSTANCE.convertirListaVentaServicioDtoAVentaRestDto(todosLasVentas))
	                    .build();
	        }

	        response.status(500);
	        return new ResultResponse.Builder().code(500).message("Fallo al obtener todas los Ventas").build();
	    }	
	    
	 private ResultResponse insertarVenta(final Request request, final Response response) {
	    	 validarContentType(request,response);
	    	 final VentaRestDto venta = JsonDto.aJson(request.body(),VentaRestDto.class);
	    	 final VentaServicioDto ventaServicioDto = VentaDtoMaper.INSTANCE.VentaRestDtoAVentaServicioDto(venta);
	    	 final Boolean isVentaInsertado = ventaServicio.crearVenta(ventaServicioDto);
	    	 
	    	 response.type(CONTENT_TYPE);
	    	 if(isVentaInsertado) {
	    		 
	    		 return new ResultResponse.Builder().build();
	    	 }
	    	 
	    	 response.status(500);
	    	 return new ResultResponse.Builder().code(500).message("Fallo al insertar el Venta").build();
	    	
	    }	  
	 

	 private ResultResponse actualizarVenta(final Request request, final Response response) {
	   	 validarContentType(request,response);
   	 final VentaRestDto venta = JsonDto.aJson(request.body(),VentaRestDto.class);
   	 final VentaServicioDto ventaServicioDto = VentaDtoMaper.INSTANCE.VentaRestDtoAVentaServicioDto(venta);
	   	 final Boolean isVentaActualizado = ventaServicio.actualizarVenta(ventaServicioDto);
	   	 
	   	 response.type(CONTENT_TYPE);
	   	 if(isVentaActualizado) {
	   		 
	   		 return new ResultResponse.Builder().build();
	   	 }
	   	 
	   	 response.status(500);
	   	 return new ResultResponse.Builder().code(500).message("Fallo al actualizar el Venta").build();
	   	
	   } 
	 
	  private ResultResponse eliminarVenta(final Request request, final Response response) {
	      	 validarContentType(request,response);
	      	 String id = request.params(":id");
	      	 
	      	VentaServicioDto ventaServicioDto = new VentaServicioDto();
	      	ventaServicioDto.setCodVenta(Integer.parseInt(id));
	      	 final Boolean isVentaEliminado = ventaServicio.eliminaVenta(ventaServicioDto);
	 
	      	 
	      	 response.type(CONTENT_TYPE);
	      	 if(isVentaEliminado) {
	      		 
	      		 return new ResultResponse.Builder().build();
	      	 }
	      	 
	      	 response.status(500);
	      	 return new ResultResponse.Builder().code(500).message("Fallo al eliminar el Venta").build();
	      	
	      } 

   private ResultResponse obtenerUnaVenta(final Request request, final Response response) {
		    	LOG.info("entro a obtenerUnaVenta()");
		        validarContentType(request, response);
		        
		        String id = request.params(":id");		    
		      	VentaServicioDto ventaServicioDto = new VentaServicioDto();
		      	ventaServicioDto.setCodVenta(Integer.parseInt(id));
		      	ventaServicioDto = ventaServicio.obtenerUnaVenta(ventaServicioDto);
			   	 
			   	 response.type(CONTENT_TYPE);
			   	 if(ventaServicioDto != null) {
			   		 
			   	  return new ResultResponse.Builder()
		                    .object(VentaDtoMaper.INSTANCE.VentaServicioDtoAVentaRestDto(ventaServicioDto))
		                    .build();
			   	 }
			   	 
			   	 response.status(500);
			   	 return new ResultResponse.Builder().code(500).message("Fallo al obtener la venta").build();

	       }


}
