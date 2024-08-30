package tests;

import core.BaseTest;
import core.DSL;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static core.DriverFactory.getDriver;

public class TesteFramesEJanelas extends BaseTest {
    private DSL dsl;

    @Before
    public void setUp() {
        getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/site-campo-treinamento/componentes.html");
        dsl = new DSL();
    }

    @Test
    public void testeInteragirComFrames(){
        dsl.entrarFrame("frame1");
        dsl.clicarBotao("frameButton");
        String msg = dsl.alertaObterTextoEAceita();
        Assert.assertEquals("Frame OK!", msg);

        dsl.sairFrame();
        dsl.escrever("elementosForm:nome",msg);
    }

    @Test
    public void testeInteragirComFrameEscondido(){
        WebElement frame = getDriver().findElement(By.id("frame2"));
        dsl.executarJS("window.scrollBy(0, arguments[0])", frame.getLocation().y);
        dsl.entrarFrame("frame2");
        dsl.clicarBotao("frameButton");
        String msg = dsl.alertaObterTextoEAceita();
        Assert.assertEquals("Frame OK!", msg);
    }

    @Test
    public void testeJanelas(){
        dsl.clicarBotao("buttonPopUpEasy");
        dsl.trocarJanela("Popup");
        dsl.escrever(By.tagName("textarea"),"Deu certo?");
        getDriver().close();
        dsl.trocarJanela("");
        dsl.escrever(By.tagName("textarea"),"e agora?");
    }

    @Test
    public void testeJanelaDoMal(){
        dsl.clicarBotao("buttonPopUpHard");
        dsl.trocarJanela((String) getDriver().getWindowHandles().toArray()[1]);
        dsl.escrever(By.tagName("textarea"),"Deu certo?");
        getDriver().close();
        dsl.trocarJanela((String) getDriver().getWindowHandles().toArray()[0]);
        dsl.escrever(By.tagName("textarea"),"e agora?");
    }
}