package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginFormPage extends BasePage {

    public LoginFormPage(WebDriver navegador) {
        super(navegador);
    }

    public LoginFormPage typeLogin(String login) {
        navegador.findElement(By.id("signinbox")).findElement(By.name("login")).sendKeys(login);
        return this;
    }

    public LoginFormPage typePassword(String password) {
        navegador.findElement(By.id("signinbox")).findElement(By.name("password")).sendKeys(password);
        return this;
    }

    public SecretPage clickSignIn() {
        navegador.findElement(By.linkText("SIGN IN")).click();
        return new SecretPage(navegador);
    }

    public SecretPage makeLogin(String login, String password) {
        typeLogin(login);
        typePassword(password);
        clickSignIn();
        return new SecretPage(navegador);
    }
}
