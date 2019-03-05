package com.pe.ventas.back.daos.usuarios.impl;

import java.util.List;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pe.ventas.back.daos.sql.mapeos.usuarios.UsuarioSqlMaper;
import com.pe.ventas.back.daos.usuarios.IUsuarioDao;
import com.pe.ventas.back.dtos.daos.sql.usuarios.UsuarioSqlDto;
import com.pe.ventas.back.dtos.daos.usuarios.UsuarioDaoDto;
import com.pe.ventas.back.utilidades.mapeos.usuarios.UsuarioDtoMaper;


@Repository("usuarioDao")
public class UsuarioDao implements IUsuarioDao {

    private static final Logger LOG = LogManager.getLogger(UsuarioDao.class);

    @Autowired
    private UsuarioSqlMaper usuarioSqlMaper;

    @Override
    @Cacheable(value = "usuarios")
    @Transactional(readOnly = true)
    public List<UsuarioDaoDto> obtenerTodos() {
        final List<UsuarioSqlDto> listaUsuarios = usuarioSqlMaper.select();
        return UsuarioDtoMaper.INSTANCE.convertirListaUsuarioSqlDtoAUsuarioDaoDto(listaUsuarios);
    }

    @Override
    @CachePut(value = "usuarios")
    @Transactional
    public UsuarioDaoDto crear(final UsuarioDaoDto usuario) {
        final UsuarioSqlDto usuarioSqlDto = UsuarioDtoMaper.INSTANCE.usuarioDaoDtoAUsuarioSqlDto(usuario);
        final Integer resultado = usuarioSqlMaper.insert(usuarioSqlDto);

        if ((resultado != null) && BooleanUtils.toBoolean(resultado)) {
            return UsuarioDtoMaper.INSTANCE.usuarioSqlDtoAUsuarioDaoDto(usuarioSqlDto);
        }

        return null;
    }

    @Override
    @CachePut(value = "usuarios")
    @Transactional
    public Boolean actualizar(final UsuarioDaoDto usuario) {
        final UsuarioSqlDto usuarioSqlDto = UsuarioDtoMaper.INSTANCE.usuarioDaoDtoAUsuarioSqlDto(usuario);
        LOG.debug(usuarioSqlDto);
        final Integer resultado = usuarioSqlMaper.update(usuarioSqlDto);
        return (resultado != null) && (resultado > 0) ? true : false;
    }

    @Override
    @CacheEvict(value = "usuarios", key = "#usuario.identificador")
    @Transactional
    public Boolean eliminar(final UsuarioDaoDto usuario) {
        final UsuarioSqlDto usuarioSqlDto = UsuarioDtoMaper.INSTANCE.usuarioDaoDtoAUsuarioSqlDto(usuario);
        final Integer resultado = usuarioSqlMaper.delete(usuarioSqlDto);
        return (resultado != null) && (resultado > 0) ? true : false;
    }

    @Override
    @Cacheable(value = "usuarios")
    @Transactional(readOnly = true)
    public UsuarioDaoDto obtenerUnUsuario(final UsuarioDaoDto usuario) {
        LOG.debug("Obtener usuario: UsuarioDaoDto: " + usuario);
        UsuarioSqlDto usuarioSqlDto = UsuarioDtoMaper.INSTANCE.usuarioDaoDtoAUsuarioSqlDto(usuario);
        usuarioSqlDto = usuarioSqlMaper.selectUnUsuario(usuarioSqlDto);
        return UsuarioDtoMaper.INSTANCE.usuarioSqlDtoAUsuarioDaoDto(usuarioSqlDto);
    }

    @Override
    @CacheEvict(value = "usuarios", allEntries = true)
    public void limpiarCache() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Se ejecuto la limpieza del cache usuario");
        }
    }

}
