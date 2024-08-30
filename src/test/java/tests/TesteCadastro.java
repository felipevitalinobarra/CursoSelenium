package tests;

import core.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import page.CampoTreinamentoPage;

import static core.DriverFactory.getDriver;

public class TesteCadastro extends BaseTest {
    private CampoTreinamentoPage page;

    @Before
    public void setUp() {
        getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/site-campo-treinamento/componentes.html");
        page = new CampoTreinamentoPage();
    }

    @Test
    public void cadastroComSucesso(){
        page.setNome("Felipe");
        page.setSobrenome("Barra");
        page.setSexoMasculino();
        page.setComidaPizza();
        page.setEscolaridade("Mestrado");
        page.setEsporte("Natacao");
        page.cadastrar();

        Assert.assertEquals("Cadastrado!", page.getResultadoCadastro());
        Assert.assertEquals("Felipe", page.getNomeCadastro());
        Assert.assertEquals("Barra", page.getSobrenomeCadastro());
        Assert.assertEquals("Masculino", page.getSexoCadastro());
        Assert.assertEquals("Pizza", page.getComidaCadastro());
        Assert.assertEquals("mestrado", page.getEscolaridadeCadastro());
        Assert.assertEquals("Natacao", page.getEsporteCadastro());
    }
}