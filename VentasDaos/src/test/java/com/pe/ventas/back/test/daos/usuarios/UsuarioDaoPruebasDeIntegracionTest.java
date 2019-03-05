package com.pe.ventas.back.test.daos.usuarios;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pe.ventas.back.daos.usuarios.IUsuarioDao;
import com.pe.ventas.back.dtos.daos.usuarios.UsuarioDaoDto;

/**
 *
 *
 * @author rafex
 *
 *         Este test es de integracion real con la base de datos fue meramente
 *         explicativo de preferencia solo debe ser ejecutado una vez por ello
 *         esta con la notacion @Ignore para evitar su ejecución
 *
 */

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-integracion-context.xml" })
public class UsuarioDaoPruebasDeIntegracionTest extends AbstractJUnit4SpringContextTests {

    @Test	
    public void validarQueElBeanNoSeaNulo() {
        final IUsuarioDao bean = (IUsuarioDao) applicationContext.getBean("usuarioDao");
        Assert.assertNotNull("Error al crear el contexto y el bean es nulo", bean);
    }

    @Test
    public void obtenerTodosLosUsuarios() {
        final IUsuarioDao bean = (IUsuarioDao) applicationContext.getBean("usuarioDao");

        final List<UsuarioDaoDto> listaUsuarios = bean.obtenerTodos();

        Assert.assertNotNull(listaUsuarios);
        Assert.assertFalse("Hubo un error al traer los usuarios", listaUsuarios.isEmpty());
        for (final UsuarioDaoDto usuario : listaUsuarios) {
            System.out.println(usuario.aJson());
        }
    }

    @Test
    public void crearUsuario() {
        final UsuarioDaoDto usuarioDaoDto = insert();

        Assert.assertNotNull("Fallo la creacion del usuario", usuarioDaoDto);
        Assert.assertNotNull("No obtuvo el id de insert", usuarioDaoDto.getIdentificador());
        System.out.println(usuarioDaoDto.aJson());
    }

    @Test
    public void obtenerUnUsuario() {
        final IUsuarioDao bean = (IUsuarioDao) applicationContext.getBean("usuarioDao");

        final UsuarioDaoDto usuarioDaoDto = insert();

        usuarioDaoDto.setCorreo(null);
        usuarioDaoDto.setAlias(null);

        final UsuarioDaoDto obtenerUnUsuario = bean.obtenerUnUsuario(usuarioDaoDto);

        Assert.assertEquals("Error al obtener un usuario", usuarioDaoDto.getIdentificador(),
                obtenerUnUsuario.getIdentificador());
        System.out.println(obtenerUnUsuario.aJson());

    }

    @Test
    public void actualizarUsuario() {
        final IUsuarioDao bean = (IUsuarioDao) applicationContext.getBean("usuarioDao");

        final UsuarioDaoDto usuarioDaoDto = insert();

        final Timestamp stamp = new Timestamp(System.currentTimeMillis());
        final Date date = new Date(stamp.getTime());

        final Integer aleatorio = (int) (Math.random() * 100000);
        final UsuarioDaoDto usuario = new UsuarioDaoDto();
        usuario.setIdentificador(usuarioDaoDto.getIdentificador());
        usuario.setCorreo("test" + aleatorio + "@r.com");
        usuario.setAcceso(date);
        usuario.setModificacion(date);

        final Boolean actualizar = bean.actualizar(usuario);
        Assert.assertTrue("Error al actualizar", actualizar);

    }

    @Test
    public void eliminarUsuario() {
        final IUsuarioDao bean = (IUsuarioDao) applicationContext.getBean("usuarioDao");

        final UsuarioDaoDto usuarioDaoDto = insert();

        final Boolean eliminar = bean.eliminar(usuarioDaoDto);
        Assert.assertTrue("Error al eliminar", eliminar);

    }

    @Test
    public void probandoVaciadoDeCache() {
        final IUsuarioDao bean = (IUsuarioDao) applicationContext.getBean("usuarioDao");

        bean.limpiarCache();

    }

    private UsuarioDaoDto insert() {
        final IUsuarioDao bean = (IUsuarioDao) applicationContext.getBean("usuarioDao");

        final Timestamp stamp = new Timestamp(System.currentTimeMillis());
        final Date date = new Date(stamp.getTime());

        final Integer aleatorio = (int) (Math.random() * 100000);
        final UsuarioDaoDto usuario = new UsuarioDaoDto();
        usuario.setAlias("test" + aleatorio);
        usuario.setCorreo("test" + aleatorio + "@r.com");
        usuario.setContrasenya(aleatorio.toString());
        usuario.setAcceso(date);
        usuario.setModificacion(date);

        final UsuarioDaoDto usuarioDaoDto = bean.crear(usuario);

        return usuarioDaoDto;
    }

}
