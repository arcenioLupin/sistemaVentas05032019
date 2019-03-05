package com.pe.ventas.back.servicios.almacenvendedor.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pe.ventas.back.daos.almacenvendedor.IAlmacenVendedorDao;
import com.pe.ventas.back.dtos.daos.almacenvendedor.AlmacenVendedorDaoDto;
import com.pe.ventas.back.dtos.servicios.almacenvendedor.AlmacenVendedorServicioDto;
import com.pe.ventas.back.servicios.almacenvendedor.IAlmacenVendedorServicio;
import com.pe.ventas.back.utilidades.mapeos.almacenvendedor.AlmacenVendedorDtoMaper;

@Service("almacenVendedorServicio")
public class AlmacenVendedorServicio implements IAlmacenVendedorServicio {
	
	private static final Logger LOG = LogManager.getLogger(AlmacenVendedorServicio.class);
	
	@Autowired
	IAlmacenVendedorDao almacenVendedorDao;

	@Override
	public List<AlmacenVendedorServicioDto> obtenerTodosAlmacenesVendedores() {
       final List<AlmacenVendedorDaoDto> listaAlmacenVendedorDao = almacenVendedorDao.obtenerTodosAlmacenVendedores();
		
		if(listaAlmacenVendedorDao.size() >0) {
			return   AlmacenVendedorDtoMaper.INSTANCE.convertirListaAlmacenVendedorDaoDtoAAlmacenVendedorServicioDto(listaAlmacenVendedorDao);
		}
		return null;
	}

	@Override
	public AlmacenVendedorServicioDto obtenerUnAlmacenVendedor(AlmacenVendedorServicioDto almacenVendedor) {
		AlmacenVendedorDaoDto almacenVendedorDaoDto = AlmacenVendedorDtoMaper.INSTANCE.AlmacenVendedorServicioDtoAAlmacenVendedorDaoDto(almacenVendedor);
		almacenVendedorDaoDto = almacenVendedorDao.obtenerUnAlmacenVendedor(almacenVendedorDaoDto);
		AlmacenVendedorServicioDto almacenVendedorServicioDto= AlmacenVendedorDtoMaper.INSTANCE.AlmacenVendedorDaoDtoAAlmacenVendedorServicioDto(almacenVendedorDaoDto);
		return almacenVendedorServicioDto;
	}

	@Override
	public Boolean crearAlmacenVendedor(AlmacenVendedorServicioDto almacenVendedor) {
		LOG.debug("Insertar AlmacenVendedorServicioDto: "+almacenVendedor);
		AlmacenVendedorDaoDto almacenVendedorDaoDto = AlmacenVendedorDtoMaper.INSTANCE.AlmacenVendedorServicioDtoAAlmacenVendedorDaoDto(almacenVendedor);
		almacenVendedorDaoDto = almacenVendedorDao.crearAlmacenVendedor(almacenVendedorDaoDto);
		LOG.debug("Resultado almacenVendedorDaoDto: "+almacenVendedorDaoDto);
		
		if((almacenVendedorDaoDto !=null) && (almacenVendedorDaoDto.getCodAlmacen() !=null)
				&& (almacenVendedorDaoDto.getCodAlmacen()>0))
		 {
			return true;
		 }
		return null;
	}

	@Override
	public Boolean actualizarAlmacenVendedor(AlmacenVendedorServicioDto almacenVendedor) {
		AlmacenVendedorDaoDto almacenVendedorDaoDto = AlmacenVendedorDtoMaper.INSTANCE.AlmacenVendedorServicioDtoAAlmacenVendedorDaoDto(almacenVendedor);
		return almacenVendedorDao.actualizarAlmacenVendedor(almacenVendedorDaoDto);
	}

	@Override
	public Boolean eliminaAlmacenVendedor(AlmacenVendedorServicioDto almacenVendedor) {
		LOG.debug("Eliminar Almacen-Vendedor: "+almacenVendedor);
		AlmacenVendedorDaoDto almacenVendedorDaoDto = AlmacenVendedorDtoMaper.INSTANCE.AlmacenVendedorServicioDtoAAlmacenVendedorDaoDto(almacenVendedor);
		return almacenVendedorDao.eliminaAlmacenVendedor(almacenVendedorDaoDto);
	}

	@Override
	public void limpiarCache() {
		almacenVendedorDao.limpiarCache();
	}

}
