package com.pe.ventas.back.rest.usuarios.impl;

import static com.pe.ventas.back.dtos.base.Constantes.CONTENT_TYPE;
import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.path;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.delete;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pe.ventas.back.dtos.base.JsonDto;
import com.pe.ventas.back.dtos.rest.ResultResponse;
import com.pe.ventas.back.dtos.rest.usuarios.UsuarioRestDto;
import com.pe.ventas.back.dtos.servicios.usuarios.UsuarioServicioDto;
import com.pe.ventas.back.rest.usuarios.IVentaRest;
import com.pe.ventas.back.servicios.usuarios.IUsuarioServicio;
import com.pe.ventas.back.utilidades.mapeos.usuarios.UsuarioDtoMaper;

import spark.Request;
import spark.Response;

@Component("usuarioRest")
public class UsuarioRest implements IVentaRest {

    private static final Logger LOG = LogManager.getLogger(UsuarioRest.class);

    @Autowired
    private IUsuarioServicio usuarioServicio;

    @Override
    public void routers() {

        path("/api", () -> {
            path("/v1", () -> {
                get("/usuarios/estadoDelServicio", CONTENT_TYPE,
                        (request, response) -> new ResultResponse.Builder().code(200).message("Funcionando").build());
                post("/usuarios/autenticar", CONTENT_TYPE, (request, response) -> autenticacion(request, response));
                get("/usuarios", CONTENT_TYPE, (request, response) -> todosLosUsuarios(request, response));
                get("/usuarios/usuario/:id", CONTENT_TYPE, (request, response) -> obtenerUnUsuario(request, response));
                put("/usuarios", CONTENT_TYPE, (request, response) -> insertarUsuario(request, response));
                put("/usuarios/actualizar", CONTENT_TYPE, (request, response) -> actualizarUsuario(request, response));
                delete("/usuarios/delete/:id", CONTENT_TYPE, (request, response) -> eliminarUsuario(request, response));
                get("/usuarios/clear", CONTENT_TYPE, (request, response) -> limpiarCache(request, response));
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

        usuarioServicio.limpiarCache();
        response.type(CONTENT_TYPE);
        return new ResultResponse.Builder().code(200).message("Cache limpio").build();

    }

    private ResultResponse autenticacion(final Request request, final Response response) {
        validarContentType(request, response);
        final UsuarioRestDto usuario = JsonDto.aJson(request.body(), UsuarioRestDto.class);
        final UsuarioServicioDto usuarioServicioDto = UsuarioDtoMaper.INSTANCE
                .usuarioRestDtoAUsuarioServicioDto(usuario);
        final Boolean autenticar = usuarioServicio.autenticar(usuarioServicioDto);

        response.type(CONTENT_TYPE);
        if (autenticar) {
            return new ResultResponse.Builder().build();
        }

        response.status(500);
        return new ResultResponse.Builder().code(500).message("Fallo al autenticar").build();
    }

    private ResultResponse todosLosUsuarios(final Request request, final Response response) {
        validarContentType(request, response);

        final List<UsuarioServicioDto> todosLosUsuarios = usuarioServicio.todosLosUsuarios();

        response.type(CONTENT_TYPE);
        if (CollectionUtils.isNotEmpty(todosLosUsuarios)) {
            return new ResultResponse.Builder()
                    .object(UsuarioDtoMaper.INSTANCE.convertirListaUsuarioServicioDtoAUsuarioRestDto(todosLosUsuarios))
                    .build();
        }

        response.status(500);
        return new ResultResponse.Builder().code(500).message("Fallo al obtener todos los usuarios").build();
    }
    
    private ResultResponse insertarUsuario(final Request request, final Response response) {
    	 validarContentType(request,response);
    	 final UsuarioRestDto usuario = JsonDto.aJson(request.body(),UsuarioRestDto.class);
    	 final UsuarioServicioDto usuarioServicioDto = UsuarioDtoMaper.INSTANCE.usuarioRestDtoAUsuarioServicioDto(usuario);
    	 final Boolean isUsuarioInsertado = usuarioServicio.insertarUsuario(usuarioServicioDto);
    	 
    	 response.type(CONTENT_TYPE);
    	 if(isUsuarioInsertado) {
    		 
    		 return new ResultResponse.Builder().build();
    	 }
    	 
    	 response.status(500);
    	 return new ResultResponse.Builder().code(500).message("Fallo al insertar el usuario").build();
    	
    }
    
    private ResultResponse actualizarUsuario(final Request request, final Response response) {
   	 validarContentType(request,response);
   	 final UsuarioRestDto usuario = JsonDto.aJson(request.body(),UsuarioRestDto.class);
   	 final UsuarioServicioDto usuarioServicioDto = UsuarioDtoMaper.INSTANCE.usuarioRestDtoAUsuarioServicioDto(usuario);
   	 final Boolean isUsuarioActualizado = usuarioServicio.actualizar(usuarioServicioDto);
   	 
   	 response.type(CONTENT_TYPE);
   	 if(isUsuarioActualizado) {
   		 
   		 return new ResultResponse.Builder().build();
   	 }
   	 
   	 response.status(500);
   	 return new ResultResponse.Builder().code(500).message("Fallo al actualizar el usuario").build();
   	
   }    
    
  private ResultResponse eliminarUsuario(final Request request, final Response response) {
      	 validarContentType(request,response);
      	 //final UsuarioRestDto usuario = JsonDto.aJson(request.body(),UsuarioRestDto.class);
      	 String id = request.params(":id");
      	 //final UsuarioServicioDto usuarioServicioDto = UsuarioDtoMaper.INSTANCE.usuarioRestDtoAUsuarioServicioDto(usuario);
      	 UsuarioServicioDto usuarioServicioDto = new UsuarioServicioDto();
      	usuarioServicioDto.setIdentificador(Integer.parseInt(id));
      	 final Boolean isUsuarioEliminado = usuarioServicio.eliminar(usuarioServicioDto);
      	 
      	 response.type(CONTENT_TYPE);
      	 if(isUsuarioEliminado) {
      		 
      		 return new ResultResponse.Builder().build();
      	 }
      	 
      	 response.status(500);
      	 return new ResultResponse.Builder().code(500).message("Fallo al eliminar el usuario").build();
      	
      }
  
  private ResultResponse obtenerUnUsuario(final Request request, final Response response) {
  	LOG.info("entro a obtenerUnaUsuario()");
      validarContentType(request, response);
      
      String id = request.params(":id");
  	//CategoriaServicioDto categoriaServicioDto = CategoriaDtoMaper.INSTANCE.categoriaRestDtoAcategoriaServicioDto(categoria);
      UsuarioServicioDto usuarioServicioDto = new UsuarioServicioDto();
      usuarioServicioDto.setIdentificador(Integer.parseInt(id));
      usuarioServicioDto = usuarioServicio.obtenerUnUsuario(usuarioServicioDto);
	   	 
	   	 response.type(CONTENT_TYPE);
	   	 if(usuarioServicioDto != null) {
	   		 
	   	  return new ResultResponse.Builder()
                  .object(UsuarioDtoMaper.INSTANCE.usuarioServicioDtoAUsuarioRestDto(usuarioServicioDto))
                  .build();
	   	 }
	   	 
	   	 response.status(500);
	   	 return new ResultResponse.Builder().code(500).message("Fallo al obtener el usuario").build();

 }
}
