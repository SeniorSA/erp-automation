package senior.erp.testes;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import com.senior.framework.testes.CaixaAtribuicao;
import com.senior.framework.testes.SistemaSenior;
import com.senior.framework.testes.SistemaSeniorComTransacao;
import com.senior.framework.testes.Tecla;
import com.senior.framework.testes.TipoParametroSQL;

import senior.erp.AssertivaERP;
import senior.erp.ReexecutarTeste.Reexecutar;
import senior.erp.SystemMessageTitles;
import senior.erp.SystemName;
import senior.erp.SystemUsers;
import senior.erp.MetodosComuns;
import senior.erp.MetodosComunsNucleo;
import senior.erp.componentNames.mercado.Form140PREComponentNames;
import senior.erp.componentNames.suprimentos.Form435CCCComponentNames;
import senior.erp.utils.FacadeSuprimentos;

/**
 * Cenário que trata o assunto XYZ.
 * 
 * Pré-condições: XYZ.
 *
 */
public class TCSREB0001_ExemploCasoDeTeste {

    @Rule
    public Reexecutar reexecutar = new Reexecutar(3);

    /**
     * Inicializa sistema.
     */
    @BeforeClass
    public static void setUpClass() {
        try {
            SistemaSenior.iniciarSistema(SystemName.SAPIENS, SystemUsers.SUPORTE);
        } catch (Exception e) {
            if (e.getMessage().contains("Timeout")) {
                SistemaSenior.finalizarSistema();
                SistemaSenior.iniciarSistema(SystemName.SAPIENS, SystemUsers.SUPORTE);
            }
        }
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
        MetodosComunsNucleo.reverterTransacao(SystemName.SAPIENS, SystemUsers.SUPORTE);
    }

 

}
