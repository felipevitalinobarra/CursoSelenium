package tests;

import core.BaseTest;
import core.DSL;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static core.DriverFactory.getDriver;

public class TesteAlert extends BaseTest {
    private DSL dsl;

    @Before
    public void setUp() {
        getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/site-campo-treinamento/componentes.html");
        dsl = new DSL();
    }

    @Test
    public void testeAlertSimples(){
        dsl.clicarBotao("alert");
        String texto = dsl.alertaObterTextoEAceita();
        Assert.assertEquals("Alert Simples",texto);
        dsl.escrever("elementosForm:nome",texto);
    }

    @Test
    public void testeAlertConfirm() {
        dsl.clicarBotao("confirm");
        Assert.assertEquals("Confirm Simples",dsl.alertaObterTextoEAceita());
        Assert.assertEquals("Confirmado",dsl.alertaObterTextoEAceita());

        dsl.clicarBotao("confirm");
        Assert.assertEquals("Confirm Simples",dsl.alertaObterTextoENega());
        Assert.assertEquals("Negado",dsl.alertaObterTextoENega());
    }

    @Test
    public void testeAlertPrompt() {
        dsl.clicarBotao("prompt");
        Assert.assertEquals("Digite um numero",dsl.alertaObterTexto());
        dsl.alertaEscrever("12");
        Assert.assertEquals("Era 12?",dsl.alertaObterTextoEAceita());
        Assert.assertEquals(":D",dsl.alertaObterTextoEAceita());
    }
}