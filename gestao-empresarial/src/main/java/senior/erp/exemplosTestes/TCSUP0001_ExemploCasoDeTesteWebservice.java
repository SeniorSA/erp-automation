package senior.erp.exemplosTestes;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import com.senior.framework.testes.SistemaSenior;
import com.senior.framework.testes.SistemaSeniorComTransacao;

import senior.erp.ReexecutarTeste.Reexecutar;
import senior.erp.SystemName;
import senior.erp.SystemUsers;
import senior.erp.MetodosComunsNucleo;
import senior.erp.componentNames.webServices.WsMcmCprOrdemCompraConstants;

/**
 * Cenário que trata o assunto XYZ.
 * 
 * Pré-condições: XYZ.
 */
public class TCSUP0001_ExemploCasoDeTesteWebservice {

    @Rule
    public Reexecutar reexecutar = new Reexecutar(3);

    /**
     * Inicializa sistema.
     */
    @BeforeClass
    public static void setUpClass() {
        try {
            SistemaSenior.iniciarSistema(SystemName.SAPIENS, SystemUsers.SUPRIMENTOS + " -mcdebug");
        } catch (Exception e) {
            if (e.getMessage().contains("Timeout")) {
                SistemaSenior.finalizarSistema();
                SistemaSenior.iniciarSistema(SystemName.SAPIENS, SystemUsers.SUPRIMENTOS + " -mcdebug");
            }
        }
        MetodosComunsNucleo.selecionarEmpresaFilial(170, 1);
    }

    /**
     * Finaliza sistema.
     */
    @AfterClass
    public static void tearDownClass() {
        SistemaSenior.finalizarSistema();
    }

    /**
     * Inicializa transação dos cenários.
     */
    @Before
    public void setUp() {
        SistemaSeniorComTransacao.iniciarTransacao();
    }

    /**
     * Reverte transação dos cenários.
     */
    @After
    public void tearDown() {
        MetodosComunsNucleo.reverterTransacao(SystemName.SAPIENS, SystemUsers.SUPRIMENTOS + " -mcdebug");
    }

    /**
     * Cenário destinado ao assunto XYZ.
     */
    @Test
    public void testScenario0001_ExemploTesteWebservice() {
        MetodosComunsNucleo.selecionarEmpresaFilial(170, 1);
        SistemaSenior.executarWebServices(WsMcmCprOrdemCompraConstants.SERVICE_NAME, WsMcmCprOrdemCompraConstants.PORT_GRAVAR_ORDENS_COMPRA, "TC-SUP-0019 - Ent_Cen0001", "TC-SUP-0019 - Res_Cen0001", 10000);
        //Validações
    }

}
