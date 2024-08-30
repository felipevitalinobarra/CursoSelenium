package tests;

import core.BaseTest;
import core.DSL;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import page.CampoTreinamentoPage;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static core.DriverFactory.getDriver;

@RunWith(Parameterized.class)
public class TesteRegrasCadastro extends BaseTest {
    private DSL dsl;
    private CampoTreinamentoPage page;

    @Parameterized.Parameter
    public String nome;
    @Parameterized.Parameter(value=1)
    public String sobrenome;
    @Parameterized.Parameter(value=2)
    public String sexo;
    @Parameterized.Parameter(value=3)
    public List<String> comidas;
    @Parameterized.Parameter(value=4)
    public String[] esportes;
    @Parameterized.Parameter(value=5)
    public String msg;

    @Before
    public void setUp() {
        getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/site-campo-treinamento/componentes.html");
        dsl = new DSL();
        page = new CampoTreinamentoPage();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getCollection(){
        return Arrays.asList(new Object[][]{
                {"","","", Arrays.asList(), new String[]{},"Nome eh obrigatorio"},
                {"Felipe","","", Arrays.asList(), new String[]{},"Sobrenome eh obrigatorio"},
                {"Felipe","Barra","", Arrays.asList(), new String[]{},"Sexo eh obrigatorio"},
                {"Felipe","Barra","Masculino", Arrays.asList("Carne","Vegetariano"), new String[]{},"Tem certeza que voce eh vegetariano?"},
                {"Felipe","Barra","Masculino", Arrays.asList("Carne"), new String[]{"Karate","O que eh esporte?"},"Voce faz esporte ou nao?"},
        });
    }

    @Test
    public void validarRegras(){
        page.setNome(nome);
        page.setSobrenome(sobrenome);
        if (sexo.equals("Masculino")){
            page.setSexoMasculino();
        }if(sexo.equals("Feminino")){
            page.setSexoFeminino();
        }
        if (comidas.contains("Carne")){
            page.setComidaCarne();
        }if (comidas.contains("Frango")){
            page.setComidaFrango();
        }if (comidas.contains("Vegetariano")){
            page.setComidaVegetariana();
        }
        page.setEsporte(esportes);
        page.cadastrar();
        Assert.assertEquals(msg,dsl.alertaObterTextoEAceita());
    }
}
