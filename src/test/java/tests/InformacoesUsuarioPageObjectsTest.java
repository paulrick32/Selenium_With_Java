package tests;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import suporte.Web;

import static org.junit.Assert.assertEquals;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "testAdicionarUmaInformacaoAdicionalDoUsuario.csv")

public class InformacoesUsuarioPageObjectsTest {
    private WebDriver navegador;

    @Before
    public void setUp() {
        navegador = Web.createChrome();
    }

    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario(
            @Param(name="login")String login,
            @Param(name="password")String password,
            @Param(name="type")String type,
            @Param(name="contact")String contact,
            @Param(name="message")String message
        ) {
        String textToast = new LoginPage(navegador)
                .clickSignIn()
                .makeLogin(login,password)
                .clickMe()
                .clickTabMoreDataAboutYou()
                .clickButtonAddMoreDataAboutYou()
                .addContact(type,contact)
                .catchTextToast();
        assertEquals(message, textToast);
    }

    @After
    public void tearDown() {
        navegador.quit();
    }
}
