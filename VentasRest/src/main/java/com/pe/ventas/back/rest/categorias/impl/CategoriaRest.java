package com.pe.ventas.back.rest.categorias.impl;

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
import com.pe.ventas.back.dtos.rest.categorias.CategoriaRestDto;
import com.pe.ventas.back.dtos.servicios.categorias.CategoriaServicioDto;
import com.pe.ventas.back.rest.usuarios.IVentaRest;
import com.pe.ventas.back.servicios.categorias.ICategoriaServicio;
import com.pe.ventas.back.utilidades.mapeos.categorias.CategoriaDtoMaper;
import spark.Request;
import spark.Response;

@Component("categoriaRest")
public class CategoriaRest implements IVentaRest {
	
	
	 private static final Logger LOG = LogManager.getLogger(CategoriaRest.class);

	    @Autowired
	    private ICategoriaServicio categoriaServicio;

	    @Override
	    public void routers() {

	        path("/api", () -> {
	            path("/v2", () -> {
	                get("/categorias/estadoDelServicio", CONTENT_TYPE,
	                        (request, response) -> new ResultResponse.Builder().code(200).message("Funcionando").build());
	               
	                get("/categorias", CONTENT_TYPE, (request, response) -> todasLasCategorias(request, response));
	                get("/categorias/categoria/:id", CONTENT_TYPE, (request, response) -> obtenerUnaCategoria(request, response));
	                put("/categorias", CONTENT_TYPE, (request, response) -> insertarCategoria(request, response));
	                put("/categorias/actualizar", CONTENT_TYPE, (request, response) -> actualizarCategoria(request, response));
	                delete("/categorias/delete/:id", CONTENT_TYPE, (request, response) -> eliminarCategoria(request, response));
	                get("/categorias/clear", CONTENT_TYPE, (request, response) -> limpiarCache(request, response));
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

	        categoriaServicio.limpiarCache();
	        response.type(CONTENT_TYPE);
	        return new ResultResponse.Builder().code(200).message("Cache limpio").build();

	    }



	    private ResultResponse todasLasCategorias(final Request request, final Response response) {
	    	LOG.info("entro a todasLasCategorias()");
	        validarContentType(request, response);

	        final List<CategoriaServicioDto> todosLasCategorias = categoriaServicio.obtenerTodasLasCategorias();

	        response.type(CONTENT_TYPE);
	        if (CollectionUtils.isNotEmpty(todosLasCategorias)) {
	            return new ResultResponse.Builder()
	                    .object(CategoriaDtoMaper.INSTANCE.convertirListaCategoriaServicioDtoACategoriaRestDto(todosLasCategorias))
	                    .build();
	        }

	        response.status(500);
	        return new ResultResponse.Builder().code(500).message("Fallo al obtener todos las categorias").build();
	    }
	    
	    private ResultResponse insertarCategoria(final Request request, final Response response) {
	    	 validarContentType(request,response);
	    	 final CategoriaRestDto categoria = JsonDto.aJson(request.body(),CategoriaRestDto.class);
	    	 final CategoriaServicioDto categoriaServicioDto = CategoriaDtoMaper.INSTANCE.categoriaRestDtoAcategoriaServicioDto(categoria);
	    	 final Boolean isCategoriaInsertada = categoriaServicio.crearCategoria(categoriaServicioDto);
	    	 
	    	 response.type(CONTENT_TYPE);
	    	 if(isCategoriaInsertada) {
	    		 
	    		 return new ResultResponse.Builder().build();
	    	 }
	    	 
	    	 response.status(500);
	    	 return new ResultResponse.Builder().code(500).message("Fallo al insertar la categoria").build();
	    	
	    }
	    
	 private ResultResponse actualizarCategoria(final Request request, final Response response) {
	   	 validarContentType(request,response);
    	 final CategoriaRestDto categoria = JsonDto.aJson(request.body(),CategoriaRestDto.class);
    	 final CategoriaServicioDto categoriaServicioDto = CategoriaDtoMaper.INSTANCE.categoriaRestDtoAcategoriaServicioDto(categoria);
	   	 final Boolean isCategoriaActualizada = categoriaServicio.actualizarCategoria(categoriaServicioDto);
	   	 
	   	 response.type(CONTENT_TYPE);
	   	 if(isCategoriaActualizada) {
	   		 
	   		 return new ResultResponse.Builder().build();
	   	 }
	   	 
	   	 response.status(500);
	   	 return new ResultResponse.Builder().code(500).message("Fallo al actualizar la categoria").build();
	   	
	   }    
	    
	  private ResultResponse eliminarCategoria(final Request request, final Response response) {
	      	 validarContentType(request,response);
	      	 //final UsuarioRestDto usuario = JsonDto.aJson(request.body(),UsuarioRestDto.class);
	      	 String id = request.params(":id");
	      	 //final UsuarioServicioDto usuarioServicioDto = UsuarioDtoMaper.INSTANCE.usuarioRestDtoAUsuarioServicioDto(usuario);
	      	CategoriaServicioDto categoriaServicioDto = new CategoriaServicioDto();
	      	categoriaServicioDto.setCodigo(Integer.parseInt(id));
	      	 final Boolean isCategoriaEliminada = categoriaServicio.eliminaCategoria(categoriaServicioDto);
	 
	      	 
	      	 response.type(CONTENT_TYPE);
	      	 if(isCategoriaEliminada) {
	      		 
	      		 return new ResultResponse.Builder().build();
	      	 }
	      	 
	      	 response.status(500);
	      	 return new ResultResponse.Builder().code(500).message("Fallo al eliminar el usuario").build();
	      	
	      }    
	  
	 private ResultResponse obtenerUnaCategoria(final Request request, final Response response) {
	    	LOG.info("entro a obtenerUnaCategoria()");
	        validarContentType(request, response);
	        
	        String id = request.params(":id");
	    	//CategoriaServicioDto categoriaServicioDto = CategoriaDtoMaper.INSTANCE.categoriaRestDtoAcategoriaServicioDto(categoria);
	        CategoriaServicioDto categoriaServicioDto = new CategoriaServicioDto();
	        categoriaServicioDto.setCodigo(Integer.parseInt(id));
	    	categoriaServicioDto = categoriaServicio.obtenerUnaCategoria(categoriaServicioDto);
		   	 
		   	 response.type(CONTENT_TYPE);
		   	 if(categoriaServicioDto != null) {
		   		 
		   	  return new ResultResponse.Builder()
	                    .object(CategoriaDtoMaper.INSTANCE.categoriaServicioDtoAcategoriaRestDto(categoriaServicioDto))
	                    .build();
		   	 }
		   	 
		   	 response.status(500);
		   	 return new ResultResponse.Builder().code(500).message("Fallo al obtener la categoria").build();

       }
	    
	}
