package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TesteAjax.class,
        TesteAlert.class,
        TesteCadastro.class,
        TesteCampoTreinamento.class,
        TesteFramesEJanelas.class,
        TestePrime.class,
        TesteRegrasCadastro.class,
        TesteSincronismo.class
})
public class SuiteTeste {

}