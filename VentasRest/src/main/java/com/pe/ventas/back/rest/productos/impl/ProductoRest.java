package com.pe.ventas.back.rest.productos.impl;

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
import com.pe.ventas.back.dtos.rest.productos.ProductoRestDto;
import com.pe.ventas.back.dtos.servicios.productos.ProductoServicioDto;
import com.pe.ventas.back.rest.usuarios.IVentaRest;
import com.pe.ventas.back.servicios.productos.IProductoServicio;
import com.pe.ventas.back.utilidades.mapeos.productos.ProductoDtoMaper;

import spark.Request;
import spark.Response;

@Component("productoRest")
public class ProductoRest implements IVentaRest {
	
	private static final Logger LOG = LogManager.getLogger(ProductoRest.class);
	
	 @Autowired
	 private IProductoServicio productoServicio;
	 
	 @Override
	 public void routers() {

	        path("/api", () -> {
	            path("/v2", () -> {
	                get("/productos/estadoDelServicio", CONTENT_TYPE,
	                        (request, response) -> new ResultResponse.Builder().code(200).message("Funcionando").build());
	               
	                get("/productos", CONTENT_TYPE, (request, response) -> todosLosProductos(request, response));
	                get("/productos/producto/:id", CONTENT_TYPE, (request, response) -> obtenerUnProducto(request, response));
	                put("/productos", CONTENT_TYPE, (request, response) -> insertarProducto(request, response));
	                put("/productos/actualizar", CONTENT_TYPE, (request, response) -> actualizarProducto(request, response));
	                delete("/productos/delete/:id", CONTENT_TYPE, (request, response) -> eliminarProducto(request, response));
	                get("/productos/clear", CONTENT_TYPE, (request, response) -> limpiarCache(request, response));
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

	        productoServicio.limpiarCache();
	        response.type(CONTENT_TYPE);
	        return new ResultResponse.Builder().code(200).message("Cache limpio").build();

	    }
	    
	private ResultResponse todosLosProductos(final Request request, final Response response) {
	    	LOG.info("entro a todosLosProductos()");
	        validarContentType(request, response);

	        final List<ProductoServicioDto> todosLosProductos = productoServicio.obtenerTodasLosProductos();

	        response.type(CONTENT_TYPE);
	        if (CollectionUtils.isNotEmpty(todosLosProductos)) {
	            return new ResultResponse.Builder()
	                    .object(ProductoDtoMaper.INSTANCE.convertirListaProductoServicioDtoAProductoRestDto(todosLosProductos))
	                    .build();
	        }

	        response.status(500);
	        return new ResultResponse.Builder().code(500).message("Fallo al obtener todos los productos").build();
	    }	
	    
	 private ResultResponse insertarProducto(final Request request, final Response response) {
	    	 validarContentType(request,response);
	    	 final ProductoRestDto producto = JsonDto.aJson(request.body(),ProductoRestDto.class);
	    	 final ProductoServicioDto productoServicioDto = ProductoDtoMaper.INSTANCE.productoRestDtoAproductoServicioDto(producto);
	    	 final Boolean isProductoInsertado = productoServicio.crearProducto(productoServicioDto);
	    	 
	    	 response.type(CONTENT_TYPE);
	    	 if(isProductoInsertado) {
	    		 
	    		 return new ResultResponse.Builder().build();
	    	 }
	    	 
	    	 response.status(500);
	    	 return new ResultResponse.Builder().code(500).message("Fallo al insertar el producto").build();
	    	
	    }	  
	 

	 private ResultResponse actualizarProducto(final Request request, final Response response) {
	   	 validarContentType(request,response);
    	 final ProductoRestDto producto = JsonDto.aJson(request.body(),ProductoRestDto.class);
    	 final ProductoServicioDto productoServicioDto = ProductoDtoMaper.INSTANCE.productoRestDtoAproductoServicioDto(producto);
	   	 final Boolean isProductoActualizado = productoServicio.actualizarProducto(productoServicioDto);
	   	 
	   	 response.type(CONTENT_TYPE);
	   	 if(isProductoActualizado) {
	   		 
	   		 return new ResultResponse.Builder().build();
	   	 }
	   	 
	   	 response.status(500);
	   	 return new ResultResponse.Builder().code(500).message("Fallo al actualizar el producto").build();
	   	
	   } 
	 
	  private ResultResponse eliminarProducto(final Request request, final Response response) {
	      	 validarContentType(request,response);
	      	 String id = request.params(":id");
	      	 
	      	ProductoServicioDto productoServicioDto = new ProductoServicioDto();
	      	productoServicioDto.setCodigo(Integer.parseInt(id));
	      	 final Boolean isProductoEliminado = productoServicio.eliminaProducto(productoServicioDto);
	 
	      	 
	      	 response.type(CONTENT_TYPE);
	      	 if(isProductoEliminado) {
	      		 
	      		 return new ResultResponse.Builder().build();
	      	 }
	      	 
	      	 response.status(500);
	      	 return new ResultResponse.Builder().code(500).message("Fallo al eliminar el producto").build();
	      	
	      } 

    private ResultResponse obtenerUnProducto(final Request request, final Response response) {
		    	LOG.info("entro a obtenerUnProducto()");
		        validarContentType(request, response);
		        
		        String id = request.params(":id");		    
		      	ProductoServicioDto productoServicioDto = new ProductoServicioDto();
		      	productoServicioDto.setCodigo(Integer.parseInt(id));
		      	productoServicioDto = productoServicio.obtenerUnProducto(productoServicioDto);
			   	 
			   	 response.type(CONTENT_TYPE);
			   	 if(productoServicioDto != null) {
			   		 
			   	  return new ResultResponse.Builder()
		                    .object(ProductoDtoMaper.INSTANCE.productoServicioDtoAproductoRestDto(productoServicioDto))
		                    .build();
			   	 }
			   	 
			   	 response.status(500);
			   	 return new ResultResponse.Builder().code(500).message("Fallo al obtener la categoria").build();

	       }

}
