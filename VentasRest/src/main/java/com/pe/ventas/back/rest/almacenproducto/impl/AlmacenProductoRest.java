package com.pe.ventas.back.rest.almacenproducto.impl;

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
import com.pe.ventas.back.dtos.rest.almacenproducto.AlmacenProductoRestDto;
import com.pe.ventas.back.dtos.servicios.almacenproducto.AlmacenProductoServicioDto;
import com.pe.ventas.back.rest.usuarios.IVentaRest;
import com.pe.ventas.back.servicios.almacenproducto.IAlmacenProductoServicio;
import com.pe.ventas.back.utilidades.mapeos.almacenproducto.AlmacenProductoDtoMaper;
import spark.Request;
import spark.Response;

@Component("almacenProductoRest")
public class AlmacenProductoRest implements IVentaRest {
	
	 private static final Logger LOG = LogManager.getLogger(AlmacenProductoRest.class);

	    @Autowired
	    private IAlmacenProductoServicio almacenProductoServicio;

	    @Override
	    public void routers() {

	        path("/api", () -> {
	            path("/v2", () -> {
	                get("/almacenproductos/estadoDelServicio", CONTENT_TYPE,
	                        (request, response) -> new ResultResponse.Builder().code(200).message("Funcionando").build());
	               
	                get("/almacenproductos", CONTENT_TYPE, (request, response) -> todosAlmacenProducto(request, response));
	                get("/almacenproductos/almacenproducto/:idAlmacen/:idProducto", CONTENT_TYPE, (request, response) -> obtenerUnAlmacenProducto(request, response));
	                put("/almacenproductos", CONTENT_TYPE, (request, response) -> insertarAlmacenProducto(request, response));
	                put("/almacenproductos/actualizar", CONTENT_TYPE, (request, response) -> actualizarAlmacenProducto(request, response));
	                delete("/almacenproductos/delete/:idAlmacen/:idProducto", CONTENT_TYPE, (request, response) -> eliminarAlmacenProducto(request, response));
	                get("/almacenproductos/clear", CONTENT_TYPE, (request, response) -> limpiarCache(request, response));
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

	        almacenProductoServicio.limpiarCache();
	        response.type(CONTENT_TYPE);
	        return new ResultResponse.Builder().code(200).message("Cache limpio").build();

	    }



	    private ResultResponse todosAlmacenProducto(final Request request, final Response response) {
	    	LOG.info("entro a todosAlmacenProducto()");
	        validarContentType(request, response);

	        final List<AlmacenProductoServicioDto> todosLosAlmacenProducto = almacenProductoServicio.obtenerTodosAlmacenesProductos();

	        response.type(CONTENT_TYPE);
	        if (CollectionUtils.isNotEmpty(todosLosAlmacenProducto)) {
	            return new ResultResponse.Builder()
	            		   .object(AlmacenProductoDtoMaper.INSTANCE.convertirListaAlmacenProductoServicioDtoAlmacenProductoRestDto(todosLosAlmacenProducto))
	            		   .build();
	        }

	        response.status(500);
	        return new ResultResponse.Builder().code(500).message("Fallo al obtener todos las almacen producto").build();
	    }
	    
	    private ResultResponse insertarAlmacenProducto(final Request request, final Response response) {
	    	 validarContentType(request,response);
	    	 final AlmacenProductoRestDto almacenProducto = JsonDto.aJson(request.body(),AlmacenProductoRestDto.class);
	    	 final AlmacenProductoServicioDto almacenProductoServicioDto = AlmacenProductoDtoMaper.INSTANCE.almacenProductoRestDtoAalmacenProductoServicioDto(almacenProducto);
	    	 final Boolean isAlmacenProductoInsertado = almacenProductoServicio.crearAlmacenProducto(almacenProductoServicioDto);
	    	 
	    	 response.type(CONTENT_TYPE);
	    	 if(isAlmacenProductoInsertado) {
	    		 
	    		 return new ResultResponse.Builder().build();
	    	 }
	    	 
	    	 response.status(500);
	    	 return new ResultResponse.Builder().code(500).message("Fallo al insertar el Almacen Producto").build();
	    	
	    }
	    
	 private ResultResponse actualizarAlmacenProducto(final Request request, final Response response) {
	   	 validarContentType(request,response);
 	 final AlmacenProductoRestDto almacenProducto = JsonDto.aJson(request.body(),AlmacenProductoRestDto.class);
 	 final AlmacenProductoServicioDto almacenProductoServicioDto = AlmacenProductoDtoMaper.INSTANCE.almacenProductoRestDtoAalmacenProductoServicioDto(almacenProducto);
	   	 final Boolean isAlmacenProductoActualizado = almacenProductoServicio.actualizarAlmacenProducto(almacenProductoServicioDto);
	   	 
	   	 response.type(CONTENT_TYPE);
	   	 if(isAlmacenProductoActualizado) {
	   		 
	   		 return new ResultResponse.Builder().build();
	   	 }
	   	 
	   	 response.status(500);
	   	 return new ResultResponse.Builder().code(500).message("Fallo al actualizar el Almacen Producto").build();
	   	
	   }    
	    
	  private ResultResponse eliminarAlmacenProducto(final Request request, final Response response) {
	      	 validarContentType(request,response);
	      	 //final UsuarioRestDto usuario = JsonDto.aJson(request.body(),UsuarioRestDto.class);
	      	 String idAlmacen = request.params(":idAlmacen");
	      	 String idProducto = request.params(":idProducto");
	      	 //final UsuarioServicioDto usuarioServicioDto = UsuarioDtoMaper.INSTANCE.usuarioRestDtoAUsuarioServicioDto(usuario);
	      	AlmacenProductoServicioDto almacenProductoServicioDto = new AlmacenProductoServicioDto();
	      	almacenProductoServicioDto.setCodAlmacen(Integer.parseInt(idAlmacen));
	      	almacenProductoServicioDto.setCodProducto(Integer.parseInt(idProducto));
	      	final Boolean isAlmacenProductoEliminado = almacenProductoServicio.eliminaAlmacenProducto(almacenProductoServicioDto);
	 
	      	 
	      	 response.type(CONTENT_TYPE);
	      	 if(isAlmacenProductoEliminado) {
	      		 
	      		 return new ResultResponse.Builder().build();
	      	 }
	      	 
	      	 response.status(500);
	      	 return new ResultResponse.Builder().code(500).message("Fallo al eliminar el Almacen Producto").build();
	      	
	      }    
	  
	 private ResultResponse obtenerUnAlmacenProducto(final Request request, final Response response) {
	    	LOG.info("entro a obtenerUnAlmacenProducto()");
	        validarContentType(request, response);
	        
	        String idAlmacen = request.params(":idAlmacen");
	        String idProducto = request.params(":idProducto");
	    	AlmacenProductoServicioDto almacenProductoServicioDto = new AlmacenProductoServicioDto();
	      	almacenProductoServicioDto.setCodAlmacen(Integer.parseInt(idAlmacen));
	      	almacenProductoServicioDto.setCodProducto(Integer.parseInt(idProducto));
	      	almacenProductoServicioDto = almacenProductoServicio.obtenerUnAlmacenProducto(almacenProductoServicioDto);
		   	 
		   	 response.type(CONTENT_TYPE);
		   	 if(almacenProductoServicioDto != null) {
		   		 
		   	  return new ResultResponse.Builder()
	                    .object(AlmacenProductoDtoMaper.INSTANCE.almacenProductoServicioDtoAalmacenProductoRestDto(almacenProductoServicioDto))
	                    .build();
		   	 }
		   	 
		   	 response.status(500);
		   	 return new ResultResponse.Builder().code(500).message("Fallo al obtener el Almacen Producto").build();

    }

}
