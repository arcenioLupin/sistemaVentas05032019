package com.pe.ventas.back.rest.almacenvendedor.impl;

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
import com.pe.ventas.back.dtos.rest.almacenvendedor.AlmacenVendedorRestDto;
import com.pe.ventas.back.dtos.servicios.almacenvendedor.AlmacenVendedorServicioDto;
import com.pe.ventas.back.rest.usuarios.IVentaRest;
import com.pe.ventas.back.servicios.almacenvendedor.IAlmacenVendedorServicio;
import com.pe.ventas.back.utilidades.mapeos.almacenvendedor.AlmacenVendedorDtoMaper;

import spark.Request;
import spark.Response;

@Component("almacenVendedorRest")
public class AlmacenVendedorRest implements IVentaRest {

	
	 private static final Logger LOG = LogManager.getLogger(AlmacenVendedorRest.class);

	    @Autowired
	    private IAlmacenVendedorServicio almacenVendedorServicio;

	@Override
	public void routers() {
        path("/api", () -> {
            path("/v2", () -> {
                get("/almacenvendedores/estadoDelServicio", CONTENT_TYPE,
                        (request, response) -> new ResultResponse.Builder().code(200).message("Funcionando").build());
               
                get("/almacenvendedores", CONTENT_TYPE, (request, response) -> todosAlmacenVendedor(request, response));
                get("/almacenvendedores/almacenvendedor/:idAlmacen/:idVendedor", CONTENT_TYPE, (request, response) -> obtenerUnAlmacenVendedor(request, response));
                put("/almacenvendedores", CONTENT_TYPE, (request, response) -> insertarAlmacenVendedor(request, response));
                put("/almacenvendedores/actualizar", CONTENT_TYPE, (request, response) -> actualizarAlmacenVendedor(request, response));
                delete("/almacenvendedores/delete/:idAlmacen/:idVendedor", CONTENT_TYPE, (request, response) -> eliminarAlmacenVendedor(request, response));
                get("/almacenvendedores/clear", CONTENT_TYPE, (request, response) -> limpiarCache(request, response));
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

        almacenVendedorServicio.limpiarCache();
        response.type(CONTENT_TYPE);
        return new ResultResponse.Builder().code(200).message("Cache limpio").build();

    }



    private ResultResponse todosAlmacenVendedor(final Request request, final Response response) {
    	LOG.info("entro a todosAlmacenVendedor()");
        validarContentType(request, response);

        final List<AlmacenVendedorServicioDto> todosLosAlmacenVendedor = almacenVendedorServicio.obtenerTodosAlmacenesVendedores();

        response.type(CONTENT_TYPE);
        if (CollectionUtils.isNotEmpty(todosLosAlmacenVendedor)) {
            return new ResultResponse.Builder()
            		   .object(AlmacenVendedorDtoMaper.INSTANCE.convertirListaAlmacenVendedorServicioDtoAlmacenVendedorRestDto(todosLosAlmacenVendedor))
            		   .build();
        }

        response.status(500);
        return new ResultResponse.Builder().code(500).message("Fallo al obtener todos las almacen vendedor").build();
    }
    
    private ResultResponse insertarAlmacenVendedor(final Request request, final Response response) {
    	 validarContentType(request,response);
    	 final AlmacenVendedorRestDto almacenVendedor = JsonDto.aJson(request.body(),AlmacenVendedorRestDto.class);
    	 final AlmacenVendedorServicioDto almacenVendedorServicioDto = AlmacenVendedorDtoMaper.INSTANCE.AlmacenVendedorRestDtoAAlmacenVendedorServicioDto(almacenVendedor);
    	 final Boolean isAlmacenVendedorInsertado = almacenVendedorServicio.crearAlmacenVendedor(almacenVendedorServicioDto);
    	 
    	 response.type(CONTENT_TYPE);
    	 if(isAlmacenVendedorInsertado) {
    		 
    		 return new ResultResponse.Builder().build();
    	 }
    	 
    	 response.status(500);
    	 return new ResultResponse.Builder().code(500).message("Fallo al insertar el Almacen vendedor").build();
    	
    }
    
 private ResultResponse actualizarAlmacenVendedor(final Request request, final Response response) {
   	 validarContentType(request,response);
	 final AlmacenVendedorRestDto almacenVendedor = JsonDto.aJson(request.body(),AlmacenVendedorRestDto.class);
	 final AlmacenVendedorServicioDto almacenVendedorServicioDto = AlmacenVendedorDtoMaper.INSTANCE.AlmacenVendedorRestDtoAAlmacenVendedorServicioDto(almacenVendedor);
   	 final Boolean isAlmacenVendedorActualizado = almacenVendedorServicio.actualizarAlmacenVendedor(almacenVendedorServicioDto);
   	 
   	 response.type(CONTENT_TYPE);
   	 if(isAlmacenVendedorActualizado) {
   		 
   		 return new ResultResponse.Builder().build();
   	 }
   	 
   	 response.status(500);
   	 return new ResultResponse.Builder().code(500).message("Fallo al actualizar el Almacen vendedor").build();
   	
   }    
    
  private ResultResponse eliminarAlmacenVendedor(final Request request, final Response response) {
      	 validarContentType(request,response);
      	 //final UsuarioRestDto usuario = JsonDto.aJson(request.body(),UsuarioRestDto.class);
      	 String idAlmacen = request.params(":idAlmacen");
      	 String idVendedor = request.params(":idVendedor");
      	 //final UsuarioServicioDto usuarioServicioDto = UsuarioDtoMaper.INSTANCE.usuarioRestDtoAUsuarioServicioDto(usuario);
      	AlmacenVendedorServicioDto almacenVendedorServicioDto = new AlmacenVendedorServicioDto();
      	almacenVendedorServicioDto.setCodAlmacen(Integer.parseInt(idAlmacen));
      	almacenVendedorServicioDto.setCodVendedor(Integer.parseInt(idVendedor));
      	final Boolean isAlmacenVendedorEliminado = almacenVendedorServicio.eliminaAlmacenVendedor(almacenVendedorServicioDto);
 
      	 
      	 response.type(CONTENT_TYPE);
      	 if(isAlmacenVendedorEliminado) {
      		 
      		 return new ResultResponse.Builder().build();
      	 }
      	 
      	 response.status(500);
      	 return new ResultResponse.Builder().code(500).message("Fallo al eliminar el Almacen vendedor").build();
      	
      }    
  
 private ResultResponse obtenerUnAlmacenVendedor(final Request request, final Response response) {
    	LOG.info("entro a obtenerUnAlmacenVendedor()");
        validarContentType(request, response);
        
        String idAlmacen = request.params(":idAlmacen");
        String idVendedor = request.params(":idVendedor");
    	AlmacenVendedorServicioDto almacenVendedorServicioDto = new AlmacenVendedorServicioDto();
      	almacenVendedorServicioDto.setCodAlmacen(Integer.parseInt(idAlmacen));
      	almacenVendedorServicioDto.setCodVendedor(Integer.parseInt(idVendedor));
      	almacenVendedorServicioDto = almacenVendedorServicio.obtenerUnAlmacenVendedor(almacenVendedorServicioDto);
	   	 
	   	 response.type(CONTENT_TYPE);
	   	 if(almacenVendedorServicioDto != null) {
	   		 
	   	  return new ResultResponse.Builder()
                    .object(AlmacenVendedorDtoMaper.INSTANCE.AlmacenVendedorServicioDtoAAlmacenVendedorRestDto(almacenVendedorServicioDto))
                    .build();
	   	 }
	   	 
	   	 response.status(500);
	   	 return new ResultResponse.Builder().code(500).message("Fallo al obtener el Almacen vendedor").build();

}

}
