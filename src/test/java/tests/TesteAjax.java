package tests;

import core.BaseTest;
import core.DSL;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static core.DriverFactory.getDriver;

public class TesteAjax extends BaseTest {
    private DSL dsl;

    @Before
    public void setUp() {
        getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml");
        dsl = new DSL();
    }

    @Test
    public void deveInteragirAjax(){
        dsl.escrever("j_idt248:name","Teste");
        dsl.clicarBotao("j_idt248:j_idt252");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.textToBe(By.id("j_idt248:display"), "Teste"));
        //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("j_idt85")));
        Assert.assertEquals("Teste",dsl.obterTexto("j_idt248:display"));
    }
}
