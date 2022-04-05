package suporte;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Web {
    public static WebDriver createChrome() {
        // Abrindo o navegador:
        System.setProperty("webdriver.chrome.driver","/home/paulo/Documentos/QA/drivers/chromedriver");
        WebDriver navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Navegando para a página do Taskit:
        navegador.manage().window().maximize();
        navegador.get("http://www.juliodelima.com.br/taskit");

        return navegador;
    }
}
