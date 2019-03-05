package com.pe.ventas.back.servicios.vendedores.impl;

import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pe.ventas.back.daos.vendedores.IVendedorDao;
import com.pe.ventas.back.dtos.daos.vendedores.VendedorDaoDto;
import com.pe.ventas.back.dtos.servicios.vendedores.VendedorServicioDto;
import com.pe.ventas.back.servicios.vendedores.IVendedorServicio;
import com.pe.ventas.back.utilidades.mapeos.vendedores.VendedorDtoMaper;

@Service("vendedorServicio")
public class VendedorServicio implements IVendedorServicio {
	
	private static final Logger LOG = LogManager.getLogger(VendedorServicio.class);
	
	@Autowired
	IVendedorDao vendedorDao;

	@Override
	public List<VendedorServicioDto> obtenerTodosLosVendedores() {
		final List<VendedorDaoDto> listaVendedoresDao = vendedorDao.obtenerTodosVendedores();
		
		if(listaVendedoresDao.size() >0) {
			return   VendedorDtoMaper.INSTANCE.convertirListaVendedorDaoDtoAVendedorServicioDto(listaVendedoresDao);
		}
		return null;
	}

	@Override
	public VendedorServicioDto obtenerUnVendedor(VendedorServicioDto vendedor) {
		  VendedorDaoDto vendedorDaoDto = VendedorDtoMaper.INSTANCE.vendedorServicioDtoAVendedorDaoDto(vendedor);
		  vendedorDaoDto = vendedorDao.obtenerUnVendedor(vendedorDaoDto);
		  VendedorServicioDto vendedorServicioDto= VendedorDtoMaper.INSTANCE.vendedorDaoDtoAVendedorServicioDto(vendedorDaoDto);
		return vendedorServicioDto;
	}

	@Override
	public Boolean crearVendedor(VendedorServicioDto vendedor) {
		LOG.debug("Insertar VendedorServicioDto: "+vendedor);
		VendedorDaoDto vendedorDaoDto = VendedorDtoMaper.INSTANCE.vendedorServicioDtoAVendedorDaoDto(vendedor);
		vendedorDaoDto.setVendedorFecre(new Date());
		vendedorDaoDto = vendedorDao.crearVendedor(vendedorDaoDto);
		LOG.debug("Resultado vendedorDaoDto: "+vendedorDaoDto);
		
		if((vendedorDaoDto !=null) && (vendedorDaoDto.getCodigo() !=null)
				&& (vendedorDaoDto.getCodigo()>0))
		 {
			return true;
		 }
		return null;
	}

	@Override
	public Boolean actualizarVendedor(VendedorServicioDto vendedor) {
		final VendedorDaoDto vendedorDaoDto = VendedorDtoMaper.INSTANCE.vendedorServicioDtoAVendedorDaoDto(vendedor);
		return vendedorDao.actualizarVendedor(vendedorDaoDto);
	}

	@Override
	public Boolean eliminaVendedor(VendedorServicioDto vendedor) {
		LOG.debug("Eliminar vendedor: "+vendedor);
		final VendedorDaoDto vendedorDaoDto = VendedorDtoMaper.INSTANCE.vendedorServicioDtoAVendedorDaoDto(vendedor);
		return vendedorDao.eliminaVendedor(vendedorDaoDto);
	}

	@Override
	public void limpiarCache() {
		
		vendedorDao.limpiarCache();
		
	}

}
