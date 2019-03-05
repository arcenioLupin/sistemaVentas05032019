package mx.rafex.blog.back.servicios.usuarios;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pe.ventas.back.daos.usuarios.IUsuarioDao;
import com.pe.ventas.back.dtos.daos.usuarios.UsuarioDaoDto;
import com.pe.ventas.back.dtos.servicios.usuarios.UsuarioServicioDto;
import com.pe.ventas.back.servicios.usuarios.IUsuarioServicio;
import com.pe.ventas.back.utilidades.mapeos.usuarios.UsuarioDtoMaper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml" })
public class UsuarioServicioTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    @InjectMocks
    private IUsuarioServicio usuarioServicio;

    @Mock
    private IUsuarioDao usarioDao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void validarCreacionDeBean() {
        final IUsuarioServicio bean = (IUsuarioServicio) applicationContext.getBean("usuarioServicio");
        Assert.assertNotNull("Error al crear el contexto y el bean es nulo", bean);
    }

    @Test
    public void autenticarUsuarioCorrectamente() {
        final UsuarioServicioDto usuario = new UsuarioServicioDto();
        usuario.setAlias("rafex");
        usuario.setContrasenya("12345");
        usuario.setCorreo("r@gmail.com");
        final UsuarioDaoDto usuarioDaoDto = UsuarioDtoMaper.INSTANCE.usuarioServicioDtoAUsuarioDaoDto(usuario);

        final UsuarioDaoDto usuarioDaoDtoRespuesta = new UsuarioDaoDto();
        usuarioDaoDtoRespuesta.setIdentificador(1);

        Mockito.when(usarioDao.obtenerUnUsuario(usuarioDaoDto)).thenReturn(usuarioDaoDtoRespuesta);

        final Boolean autenticar = usuarioServicio.autenticar(usuario);

        Assert.assertTrue("Erro al autenticar", autenticar);
    }

    @Test
    public void autenticarUsuarioIncorrectamente() {
        final UsuarioServicioDto usuario = new UsuarioServicioDto();
        usuario.setAlias("rafex");
        usuario.setContrasenya("12345");
        usuario.setCorreo("r@gmail.com");
        final UsuarioDaoDto usuarioDaoDto = UsuarioDtoMaper.INSTANCE.usuarioServicioDtoAUsuarioDaoDto(usuario);

        final UsuarioDaoDto usuarioDaoDtoRespuesta = null;

        Mockito.when(usarioDao.obtenerUnUsuario(usuarioDaoDto)).thenReturn(usuarioDaoDtoRespuesta);

        final Boolean autenticar = usuarioServicio.autenticar(usuario);

        Assert.assertFalse("Test login incorrecto falla", autenticar);
    }

}
