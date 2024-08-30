package tests;

import core.BaseTest;
import core.DSL;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static core.DriverFactory.getDriver;

public class TestePrime extends BaseTest {
    private DSL dsl;

    @Before
    public void setUp() {
        getDriver();
        dsl = new DSL();
    }

    @Test
    public void interagirComRadioPrime(){
        getDriver().get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");
        dsl.clicarRadio(By.xpath("//input[@id='j_idt249:line:0']/../..//span"));
        Assert.assertTrue(dsl.isRadioMarcado("j_idt249:line:0"));
        dsl.clicarRadio(By.xpath("(//label[.='Option2'])[1]/..//span"));
        Assert.assertTrue(dsl.isRadioMarcado("j_idt249:line:1"));
    }

    @Test
    public void interagirComSelectPrime(){
        getDriver().get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml");
        dsl.clicarRadio(By.xpath("//*[@id=\"j_idt248:advanced\"]/div[2]/span"));
        dsl.escrever("j_idt248:advanced_filter","Bolivia");
        dsl.clicarRadio(By.xpath("//*[@id='j_idt248:advanced_table']//td[.='Bolivia']"));
        Assert.assertEquals("Bolivia",dsl.obterTexto("j_idt248:advanced_label"));
    }
}
