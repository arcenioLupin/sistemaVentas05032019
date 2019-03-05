package com.pe.ventas.back.rest.vendedores.impl;

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
import com.pe.ventas.back.dtos.rest.vendedores.VendedorRestDto;
import com.pe.ventas.back.dtos.servicios.vendedores.VendedorServicioDto;
import com.pe.ventas.back.rest.usuarios.IVentaRest;
import com.pe.ventas.back.servicios.vendedores.IVendedorServicio;
import com.pe.ventas.back.utilidades.mapeos.vendedores.VendedorDtoMaper;
import spark.Request;
import spark.Response;

@Component("vendedorRest")
public class VendedorRest implements IVentaRest {
	
	private static final Logger LOG = LogManager.getLogger(VendedorRest.class);
	
	@Autowired
	IVendedorServicio vendedorServicio;
	
	
    @Override
    public void routers() {

        path("/api", () -> {
            path("/v2", () -> {
                get("/vendedores/estadoDelServicio", CONTENT_TYPE,
                        (request, response) -> new ResultResponse.Builder().code(200).message("Funcionando").build());
               
                get("/vendedores", CONTENT_TYPE, (request, response) -> todasLosVendedores(request, response));
                get("/vendedores/vendedor/:id", CONTENT_TYPE, (request, response) -> obtenerUnVendedor(request, response));
                put("/vendedores", CONTENT_TYPE, (request, response) -> insertarVendedor(request, response));
                put("/vendedores/actualizar", CONTENT_TYPE, (request, response) -> actualizarVendedor(request, response));
                delete("/vendedores/delete/:id", CONTENT_TYPE, (request, response) -> eliminarVendedor(request, response));
                get("/vendedores/clear", CONTENT_TYPE, (request, response) -> limpiarCache(request, response));
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

        vendedorServicio.limpiarCache();
        response.type(CONTENT_TYPE);
        return new ResultResponse.Builder().code(200).message("Cache limpio").build();

    }  
    
    private ResultResponse todasLosVendedores(final Request request, final Response response) {
    	LOG.info("entro a todasLosVendedores()");
        validarContentType(request, response);

        final List<VendedorServicioDto> todosLosVendedores = vendedorServicio.obtenerTodosLosVendedores();

        response.type(CONTENT_TYPE);
        if (CollectionUtils.isNotEmpty(todosLosVendedores)) {
            return new ResultResponse.Builder()
                    .object(VendedorDtoMaper.INSTANCE.convertirListaVendedorServicioDtoAVendedorRestDto(todosLosVendedores))
                    .build();
        }

        response.status(500);
        return new ResultResponse.Builder().code(500).message("Fallo al obtener todos los vendedores").build();
    }   
    
    private ResultResponse insertarVendedor(final Request request, final Response response) {
	   	 validarContentType(request,response);
	   	 final VendedorRestDto vendedor = JsonDto.aJson(request.body(),VendedorRestDto.class);
	   	 final VendedorServicioDto vendedorServicioDto = VendedorDtoMaper.INSTANCE.vendedorRestDtoAVendedorServicioDto(vendedor);
	   	 final Boolean isVendedorInsertado = vendedorServicio.crearVendedor(vendedorServicioDto);
	   	 
	   	 response.type(CONTENT_TYPE);
	   	 if(isVendedorInsertado) {
	   		 
	   		 return new ResultResponse.Builder().build();
	   	 }
	   	 
	   	 response.status(500);
	   	 return new ResultResponse.Builder().code(500).message("Fallo al insertar el vendedor").build();
   	
   }    
    
	 private ResultResponse actualizarVendedor(final Request request, final Response response) {
	   	 validarContentType(request,response);
	   	 final VendedorRestDto vendedor = JsonDto.aJson(request.body(),VendedorRestDto.class);
	   	 final VendedorServicioDto vendedorServicioDto = VendedorDtoMaper.INSTANCE.vendedorRestDtoAVendedorServicioDto(vendedor);
	   	 final Boolean isVendedorActualizado = vendedorServicio.actualizarVendedor(vendedorServicioDto);
	   	 
	   	 response.type(CONTENT_TYPE);
	   	 if(isVendedorActualizado) {
	   		 
	   		 return new ResultResponse.Builder().build();
	   	 }
	   	 
	   	 response.status(500);
	   	 return new ResultResponse.Builder().code(500).message("Fallo al actualizar el vendedor").build();
	   	
	   }

	 private ResultResponse eliminarVendedor(final Request request, final Response response) {
	      	 validarContentType(request,response);
	      	 //final UsuarioRestDto usuario = JsonDto.aJson(request.body(),UsuarioRestDto.class);
	      	 String id = request.params(":id");
	      	 //final UsuarioServicioDto usuarioServicioDto = UsuarioDtoMaper.INSTANCE.usuarioRestDtoAUsuarioServicioDto(usuario);
	      	VendedorServicioDto vendedorServicioDto = new VendedorServicioDto();
	      	vendedorServicioDto.setCodigo(Integer.parseInt(id));
	      	 final Boolean isVendedorEliminado = vendedorServicio.eliminaVendedor(vendedorServicioDto);
	 
	      	 
	      	 response.type(CONTENT_TYPE);
	      	 if(isVendedorEliminado) {
	      		 
	      		 return new ResultResponse.Builder().build();
	      	 }
	      	 
	      	 response.status(500);
	      	 return new ResultResponse.Builder().code(500).message("Fallo al eliminar el vendedor").build();
	      	
	      }  
	 private ResultResponse obtenerUnVendedor(final Request request, final Response response) {
	    	LOG.info("entro a obtenerUnVendedor()");
	        validarContentType(request, response);
	        
	        String id = request.params(":id");	    	
	        VendedorServicioDto vendedorServicioDto = new VendedorServicioDto();
	        vendedorServicioDto.setCodigo(Integer.parseInt(id));
	        vendedorServicioDto = vendedorServicio.obtenerUnVendedor(vendedorServicioDto);
		   	 
		   	 response.type(CONTENT_TYPE);
		   	 if(vendedorServicioDto != null) {
		   		 
		   	  return new ResultResponse.Builder()
	                    .object(VendedorDtoMaper.INSTANCE.vendedorServicioDtoAVendedorRestDto(vendedorServicioDto))
	                    .build();
		   	 }
		   	 
		   	 response.status(500);
		   	 return new ResultResponse.Builder().code(500).message("Fallo al obtener el vendedor").build();

    }	 

}
