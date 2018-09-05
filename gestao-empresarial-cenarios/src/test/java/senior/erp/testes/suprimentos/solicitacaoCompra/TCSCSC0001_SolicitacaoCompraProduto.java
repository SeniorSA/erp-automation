package senior.erp.testes.suprimentos.solicitacaoCompra;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.senior.framework.testes.SistemaSenior;
import com.senior.framework.testes.SistemaSeniorComTransacao;
import com.senior.framework.testes.Tecla;
import com.senior.framework.testes.TipoParametroSQL;

import senior.erp.SystemName;
import senior.erp.SystemUsers;
import senior.erp.MetodosComunsNucleo;
import senior.erp.componentNames.suprimentos.Form405SOLComponentNames;

/**
 * Cenário para geração da solicitação de compras: Solicitação de Compras de Produto.
 */

public class TCSCSC0001_SolicitacaoCompraProduto {

    /**
     * Inicializa sistema.
     */
    @BeforeClass
    public static void setUpClass() {
        SistemaSenior.iniciarSistema(SystemName.SAPIENS, SystemUsers.SUPORTE);
    }

    /**
     * Finaliza sistema.
     */
    @AfterClass
    public static void tearDownClass() {
        SistemaSenior.finalizarSistema();
    }

    /**
     * Inicializa transação.
     */
    @Before
    public void setUp() {
        SistemaSeniorComTransacao.iniciarTransacao();
    }

    /**
     * Reverte transação.
     */
    @After
    public void tearDown() {
        MetodosComunsNucleo.reverterTransacao();
    }

    /**
     * 
     */
    @Test
    public void testScenario0001() {
        MetodosComunsNucleo.selecionarEmpresaFilial(1,1);

        SistemaSenior.abrirTela(Form405SOLComponentNames.FR_NOME);
        SistemaSenior.preencherCampo(Form405SOLComponentNames.FD_NUM_SOL, "0", Tecla.TAB);
        SistemaSenior.preencherCampo(Form405SOLComponentNames.FD_SEQ_SOL, "0", Tecla.TAB); 
        SistemaSenior.teclar(Tecla.TAB);
        SistemaSenior.preencherCampo(Form405SOLComponentNames.FD_TRANSACAO, "91400", Tecla.TAB);
        SistemaSenior.preencherCampo(Form405SOLComponentNames.FD_PRODUTO, "35040.0018", Tecla.TAB);
        SistemaSenior.preencherCampo(Form405SOLComponentNames.FD_DERIVACAO, "001", Tecla.TAB);
        SistemaSenior.teclar(Tecla.TAB);
        SistemaSenior.preencherCampo(Form405SOLComponentNames.FD_QUANTIDADE_SOLICITACAO, "10", Tecla.TAB);
        SistemaSenior.teclar(18, Tecla.TAB);
        
        SistemaSeniorComTransacao.executarSQLQuery("select * "
        		                                 +   "from E405SOL "
        		                                 +  "where CODEMP='1'"
        		                                 +    "and FILSOL='1'"
        		                                 +    "and  DATSOL=? "
        		                                 +    "and  FILSOL='1' "
        		                                 +    "and  CODPRO='35040.0018' "
        		                                 +    "and  CODDER='001' "
        		                                 +    "and  QTDSOL='10' "
        		                                 +    "and  CODTNS='91400'", 1, 
        		                                 SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
        
    }

}
