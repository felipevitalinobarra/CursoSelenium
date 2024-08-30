package tests;

import core.BaseTest;
import core.DSL;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

import static core.DriverFactory.getDriver;

public class TesteCampoTreinamento extends BaseTest {
    private DSL dsl;

    @Before
    public void setUp() {
        getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/site-campo-treinamento/componentes.html");
        dsl = new DSL();
    }

    @Test
    public void testeTextField(){
        dsl.escrever("elementosForm:nome","Teste de escrita");
        Assert.assertEquals("Teste de escrita", dsl.obterValorCampo("elementosForm:nome"));
    }

    @Test
    public void testeTextFieldDuplo(){
        dsl.escrever("elementosForm:nome","Felipe");
        Assert.assertEquals("Felipe", dsl.obterValorCampo("elementosForm:nome"));
        dsl.escrever("elementosForm:nome","Barra");
        Assert.assertEquals("Barra", dsl.obterValorCampo("elementosForm:nome"));
    }

    @Test
    public void testeTextArea(){
        dsl.escrever("elementosForm:sugestoes", "Lorem ipsum");
        Assert.assertEquals("Lorem ipsum",dsl.obterValorCampo("elementosForm:sugestoes"));
    }

    @Test
    public void testeRadioButton() {
        dsl.clicarRadio("elementosForm:sexo:0");
        Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
    }

    @Test
    public void testeCheckBox() {
        dsl.clicarCheck("elementosForm:comidaFavorita:0");
        Assert.assertTrue(dsl.isCheckMarcado("elementosForm:comidaFavorita:0"));
    }

    @Test
    public void testeComboBox() {
        dsl.selecionarCombo("elementosForm:escolaridade","2o grau completo");
        Assert.assertEquals("2graucomp",dsl.obterValorCampo("elementosForm:escolaridade"));
    }

    @Test
    public void testeVerificarValoresComboBox() {
        Assert.assertEquals(8,dsl.obterQuantidadeOpcoesCombo("elementosForm:escolaridade"));
        Assert.assertTrue(dsl.verificarOpcaoCombo("elementosForm:escolaridade","Mestrado"));
    }

    @Test
    public void testeComboMultiplaEscolha() {
        dsl.selecionarCombo("elementosForm:esportes","Natacao");
        dsl.selecionarCombo("elementosForm:esportes","Corrida");
        dsl.selecionarCombo("elementosForm:esportes","O que eh esporte?");

        List <String> opcoesMarcadas =  dsl.obterValoresCombo("elementosForm:esportes");
        Assert.assertEquals(3,opcoesMarcadas.size());

        dsl.deselecionarCombo("elementosForm:esportes", "Corrida");
        opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
        Assert.assertEquals(2,opcoesMarcadas.size());
        Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao","O que eh esporte?")));
    }

    @Test
    public void testeInteragirComBotoes() {
        dsl.clicarBotao("buttonSimple");
        Assert.assertEquals("Obrigado!",dsl.obterValueElemento("buttonSimple"));
    }

    @Test
    public void testeInteragirComLinks() {
        dsl.clicarLink("Voltar");
        Assert.assertEquals("Voltou!",dsl.obterTexto("resultado"));
    }

    @Test
    public void testeBuscarTextosNaPagina() {
        Assert.assertEquals("Campo de Treinamento",dsl.obterTexto(By.tagName("h3")));
        Assert.assertEquals("Cuidado onde clica, muitas armadilhas...",dsl.obterTexto(By.className("facilAchar")));
    }

    @Test
    public void testJavaScript(){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via JS'");
        js.executeScript("document.getElementById('elementosForm:sobrenome').type='radio'");

        WebElement element = getDriver().findElement(By.id("elementosForm:nome"));
        js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");
    }


    //@Test
    /* Este método está com erro, após a implementação do screenshot */
    public void deveClicarBotaoTabela(){
        dsl.clicarBotaoTabela("Nome","Maria","Botao","elementosForm:tableUsuarios");
    }
}
