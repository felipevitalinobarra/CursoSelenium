package page;

import core.BasePage;
import org.openqa.selenium.By;

public class CampoTreinamentoPage extends BasePage {

    public void setNome(String nome){
        dsl.escrever("elementosForm:nome",nome);
    }

    public void setSobrenome(String sobrenome){
        dsl.escrever("elementosForm:sobrenome",sobrenome);
    }

    public void setSexoMasculino(){
        dsl.clicarRadio("elementosForm:sexo:0");
    }

    public void setSexoFeminino(){
        dsl.clicarRadio("elementosForm:sexo:1");
    }

    public void setComidaCarne(){
        dsl.clicarRadio("elementosForm:comidaFavorita:0");
    }

    public void setComidaFrango(){
        dsl.clicarRadio("elementosForm:comidaFavorita:1");
    }

    public void setComidaPizza(){
        dsl.clicarRadio("elementosForm:comidaFavorita:2");
    }

    public void setComidaVegetariana(){
        dsl.clicarRadio("elementosForm:comidaFavorita:3");
    }

    public void setEscolaridade(String valor){
        dsl.selecionarCombo("elementosForm:escolaridade",valor);
    }

    public void setEsporte(String... valores){
        for (String valor:valores) {
            dsl.selecionarCombo("elementosForm:esportes", valor);
        }
    }

    public void cadastrar(){
        dsl.clicarBotao("elementosForm:cadastrar");
    }

    public String getResultadoCadastro(){
        return dsl.obterTexto(By.xpath("//*[@id='resultado']/span"));
    }

    public String getNomeCadastro(){
        return dsl.obterTexto(By.xpath("//*[@id='descNome']/span"));
    }

    public String getSobrenomeCadastro(){
        return dsl.obterTexto(By.xpath("//*[@id='descSobrenome']/span"));
    }

    public String getSexoCadastro(){
        return dsl.obterTexto(By.xpath("//*[@id='descSexo']/span"));
    }

    public String getComidaCadastro(){
        return dsl.obterTexto(By.xpath("//*[@id='descComida']/span"));
    }

    public String getEscolaridadeCadastro(){
        return dsl.obterTexto(By.xpath("//*[@id='descEscolaridade']/span"));
    }

    public String getEsporteCadastro(){
        return dsl.obterTexto(By.xpath("//*[@id='descEsportes']/span"));
    }
}
