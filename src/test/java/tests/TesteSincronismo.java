package tests;

import core.BaseTest;
import core.DSL;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static core.DriverFactory.getDriver;

public class TesteSincronismo extends BaseTest {
    private DSL dsl;

    @Before
    public void setUp() {
        getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/site-campo-treinamento/componentes.html");
        dsl = new DSL();
    }

    @Test
    public void deveUtilizarEsperaFixa() throws InterruptedException{
        dsl.clicarBotao("buttonDelay");
        Thread.sleep(5000);
        dsl.escrever("novoCampo","Deu certo?");
    }

    @Test
    public void deveUtilizarEsperaImplicita() throws InterruptedException{
        dsl.clicarBotao("buttonDelay");
        getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        dsl.escrever("novoCampo","Deu certo?");
        getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    @Test
    public void deveUtilizarEsperaExplicita() throws InterruptedException{
        dsl.clicarBotao("buttonDelay");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("novoCampo")));
        dsl.escrever("novoCampo","Deu certo?");
    }
}
