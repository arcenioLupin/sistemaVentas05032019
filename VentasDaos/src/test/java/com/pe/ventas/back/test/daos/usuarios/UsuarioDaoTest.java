package com.pe.ventas.back.test.daos.usuarios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pe.ventas.back.daos.sql.mapeos.usuarios.UsuarioSqlMaper;
import com.pe.ventas.back.daos.usuarios.IUsuarioDao;
import com.pe.ventas.back.daos.usuarios.impl.UsuarioDao;
import com.pe.ventas.back.dtos.daos.sql.usuarios.UsuarioSqlDto;
import com.pe.ventas.back.dtos.daos.usuarios.UsuarioDaoDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml" })
public class UsuarioDaoTest extends AbstractJUnit4SpringContextTests {

    @InjectMocks
    private UsuarioDao usuarioDao;

    @Mock
    private UsuarioSqlMaper usuarioSqlMaper;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void validarQueElBeanNoSeaNulo() {
        final IUsuarioDao bean = (IUsuarioDao) applicationContext.getBean("usuarioDao");
        Assert.assertNotNull("Error al crear el contexto y el bean es nulo", bean);
    }

    @Test
    public void obtenerTodosLosUsuarios() throws ParseException {

        final List<UsuarioSqlDto> listaUsuariosMock = new ArrayList<UsuarioSqlDto>();
        UsuarioSqlDto u = new UsuarioSqlDto();

        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        u.setId(1);
        u.setAlias("rafex");
        u.setContrasenya("123");
        u.setCorreo("pepe.loc@gmail.com");
        u.setModificacion(sdf.parse("21/05/2018"));
        u.setRegistro(sdf.parse("21/12/2012"));
        u.setAcceso(new Date());
        listaUsuariosMock.add(u);
        u = new UsuarioSqlDto();
        u.setId(2);
        u.setAlias("2rafex");
        u.setContrasenya("2123");
        u.setCorreo("2pepe.loc@gmail.com");
        u.setModificacion(sdf.parse("21/05/2018"));
        u.setRegistro(sdf.parse("21/12/2012"));
        u.setAcceso(new Date());
        listaUsuariosMock.add(u);

        Mockito.when(usuarioSqlMaper.select()).thenReturn(listaUsuariosMock);

        final List<UsuarioDaoDto> listaUsuarios = usuarioDao.obtenerTodos();

        Mockito.verify(usuarioSqlMaper, Mockito.times(1)).select();
        Assert.assertNotNull(listaUsuarios);
        Assert.assertFalse("Hubo un error al traer los usuarios", listaUsuarios.isEmpty());
        for (final UsuarioDaoDto usuario : listaUsuarios) {
            System.out.println(usuario.aJson());
        }
    }

    // @Ignore
    // @Test
    // public void crearUsuario() {
    // final UsuarioDaoDto usuarioDaoDto = insert();
    //
    // Assert.assertNotNull("Fallo la creacion del usuario", usuarioDaoDto);
    // Assert.assertNotNull("No obtuvo el id de insert",
    // usuarioDaoDto.getIdentificador());
    // System.out.println(usuarioDaoDto.toJson());
    // }

    // @Ignore
    // @Test
    // public void obtenerUnUsuario() {
    //
    // final UsuarioDaoDto usuarioDaoDto = insert();
    //
    // usuarioDaoDto.setCorreo(null);
    // usuarioDaoDto.setAlias(null);
    //
    // final UsuarioDaoDto obtenerUnUsuario =
    // usuarioDao.obtenerUnUsuario(usuarioDaoDto);
    //
    // Assert.assertEquals("Error al obtener un usuario",
    // usuarioDaoDto.getIdentificador(),
    // obtenerUnUsuario.getIdentificador());
    // System.out.println(obtenerUnUsuario.toJson());
    //
    // }
    //
    // @Ignore
    // @Test
    // public void actualizarUsuario() {
    //
    // final UsuarioDaoDto usuarioDaoDto = insert();
    //
    // final Timestamp stamp = new Timestamp(System.currentTimeMillis());
    // final Date date = new Date(stamp.getTime());
    //
    // final Integer aleatorio = (int) (Math.random() * 100000);
    // final UsuarioDaoDto usuario = new UsuarioDaoDto();
    // usuario.setIdentificador(usuarioDaoDto.getIdentificador());
    // usuario.setCorreo("test" + aleatorio + "@r.com");
    // usuario.setAcceso(date);
    // usuario.setModificacion(date);
    //
    // final Boolean actualizar = usuarioDao.actualizar(usuario);
    // Assert.assertTrue("Error al actualizar", actualizar);
    //
    // }
    //
    // @Ignore
    // @Test
    // public void eliminarUsuario() {
    //
    // final UsuarioDaoDto usuarioDaoDto = insert();
    //
    // final Boolean eliminar = usuarioDao.eliminar(usuarioDaoDto);
    // Assert.assertTrue("Error al eliminar", eliminar);
    //
    // }
    //
    // @Ignore
    // @Test
    // public void probandoVaciadoDeCache() {
    //
    // usuarioDao.limpiarCache();
    //
    // }
    //
    // private UsuarioDaoDto insert() {
    //
    // final Timestamp stamp = new Timestamp(System.currentTimeMillis());
    // final Date date = new Date(stamp.getTime());
    //
    // final Integer aleatorio = (int) (Math.random() * 100000);
    // final UsuarioDaoDto usuario = new UsuarioDaoDto();
    // usuario.setAlias("test" + aleatorio);
    // usuario.setCorreo("test" + aleatorio + "@r.com");
    // usuario.setContrasenya(aleatorio.toString());
    // usuario.setAcceso(date);
    // usuario.setModificacion(date);
    //
    // final UsuarioDaoDto usuarioDaoDto = usuarioDao.crear(usuario);
    //
    // return usuarioDaoDto;
    // }

}
