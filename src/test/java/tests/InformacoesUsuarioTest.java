package tests;

import static org.junit.Assert.*;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import suporte.Generator;
import suporte.Screenshot;
import suporte.Web;


import java.time.Duration;
import java.util.concurrent.TimeUnit;
@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacoesUsuarioTest.csv")

public class InformacoesUsuarioTest {
    private WebDriver navegador;
    @Rule
    public TestName test = new TestName();

    @Before
    public void setUp() {
        navegador = Web.createChrome();
        //Clicar no link que possui o texto "SignIn":
        navegador.findElement(By.linkText("Sign in")).click();

        //Identificando o formulario de login":
        WebElement formularioSignInBox = navegador.findElement(By.id("signinbox"));

        //Digitar no campo com name "login" que está dentro do formulário de id "siginbox" o texto "pr721":
        formularioSignInBox.findElement(By.name("login")).sendKeys("pr721");

        //Digitar no campo com name "password" que está dentro do formulário de id "signinbox" o texto "pr721":
        formularioSignInBox.findElement(By.name("password")).sendKeys("pr721");

        //Clicar no link com o texto "SIGN IN":
        navegador.findElement(By.linkText("SIGN IN")).click();

        //Validar que dentro do elemento com class "me" está o texto "Hi, paulo":
        WebElement me = navegador.findElement(By.className("me"));
        String textoNoElementoMe = me.getText();
        assertEquals("Hi, paulo", textoNoElementoMe);

        //Clicar em um link que possui a class "me":
        navegador.findElement(By.className("me")).click();

        //Clicar em um link que possui o texto "MORE DATA ABOUT YOU":
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
    }
    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario (@Param(name="tipo")String tipo, @Param(name = "contato")String contato, @Param(name = "mensagem")String mensagemEsperada) {

        //Clicar no botão através do seu xpath //button[@data-target="addmoredata"]:
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        //Identificar a popup onde está o formulario addmoredata:
        WebElement popupAddMoreData = navegador.findElement(By.id("addmoredata"));

        //Na combo de nome "type" escolher a opção Phone:
        WebElement campoType = popupAddMoreData.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText(tipo);

        //No campo de name "contact" digitar  "5551999554165":
        popupAddMoreData.findElement(By.name("contact")).sendKeys(contato);

        //Clicar no link de text "SAVE" que está na popup:
        popupAddMoreData.findElement(By.linkText("SAVE")).click();

        //Na mensagem de id "toast-container" validar que o texto é "Your contact has been added!":
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals(mensagemEsperada, mensagem);
    }
    @Test
    public void removerUmContatoDeUmUsuario(){
        // Clicar no elemento pelo seu xpath //span[text()="+5551999554165"]/following-sibling::a
        navegador.findElement(By.xpath("//span[text()=\"5551999554165\"]/following-sibling::a")).click();

        // Confirmar a janela JS:
        navegador.switchTo().alert().accept();

        //Validar que a mensagem apresentada foi "Rest in peace, dear phone!":
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals("Rest in peace, dear phone!", mensagem);

        String screenshotArquivo = "/home/paulo/Documentos/QA/prints/" + Generator.dataHoraParaArquivo() + test.getMethodName() + ".png";
        Screenshot.tirar(navegador, screenshotArquivo);

        //Aguardar até 10 segundos para que a janela desapareça:
        WebDriverWait aguardar = new WebDriverWait(navegador, Duration.ofSeconds(10));
        aguardar.until(ExpectedConditions.stalenessOf(mensagemPop));

        // Clicar no link com texto "Logout":
        navegador.findElement(By.linkText("Logout")).click();
    }
    @After
    public void tearDown() {
        // Fechar o navegador:
        //navegador.quit();
    }
}
