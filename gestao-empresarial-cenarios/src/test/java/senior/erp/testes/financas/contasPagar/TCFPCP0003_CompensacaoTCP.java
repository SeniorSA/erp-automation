package senior.erp.testes.financas.contasPagar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.senior.framework.testes.SistemaSenior;
import com.senior.framework.testes.SistemaSeniorComTransacao;
import com.senior.framework.testes.Tecla;
import com.senior.framework.testes.TipoParametroSQL;

import senior.erp.MetodosComuns;
import senior.erp.MetodosComunsNucleo;
import senior.erp.SystemName;
import senior.erp.SystemUsers;



public class TCFPCP0003_CompensacaoTCP {

    /**
     * Inicializa o sistema.
     */
    @BeforeClass
    public static void setUpClass() {
        SistemaSenior.iniciarSistema(SystemName.SAPIENS, SystemUsers.SUPORTE);
        
    }

    /**
     * Finaliza o sistema.
     */
    @AfterClass
    public static void tearDownClass() {
        SistemaSenior.finalizarSistema();
    }

    /**
     * Inicializa transação para cada cenário de teste.
     */
    @Before
    public void setUp() {
        SistemaSeniorComTransacao.iniciarTransacao();
    }

    /**
     * Reverte transação para cada cenário de teste.
     */
    @After
    public void tearDown() {
        MetodosComunsNucleo.reverterTransacao();
    }

    /**
	 *  Conpensação de título a pagar e receber
	 */
    @Test
    public void testScenario0001() {
    	
    	MetodosComunsNucleo.selecionarEmpresaFilial(1, 1);
    	
    	SistemaSenior.abrirTela("F501BCO_FPCP");
    	
    	SistemaSenior.preencherCampo("EDatBai", SistemaSenior.dataAtual(), Tecla.TAB); 
    	SistemaSenior.selecionarItem("CMod", "Por Pagamento");
 
    	SistemaSenior.teclar(1, Tecla.TAB);
    	
    	// Abrir tela de seleção
    	SistemaSenior.clicar("Selecao");
    	SistemaSenior.preencherCampo("EPagFor", "107863", Tecla.TAB); 
    	SistemaSenior.preencherCampo("EPagTit", "951751-01", Tecla.TAB);    	
    	SistemaSenior.preencherCampo("EPagTpt", "LET", Tecla.TAB);
    	SistemaSenior.preencherCampo("ERecCli", "107863", Tecla.TAB);
    	SistemaSenior.preencherCampo("ERecTit", "933885-01", Tecla.TAB);
    	SistemaSenior.preencherCampo("ERecTpt", "DUP", Tecla.TAB);
    	SistemaSenior.clicar("OK");
    	
    	// exibe os títulos
    	SistemaSenior.clicar("Mostrar");
    	
    	// Confirma tela com observação do cliente
    	SistemaSenior.clicar("OK");
    	
    	// Marca títulos para baixa
    	SistemaSenior.clicar("Marcar");
    	SistemaSenior.clicar("MarcarRec");
    	
    	
    	// título com valores de baixa diferentes ajsutado valor a baixar
    	SistemaSenior.preencherLinhaCorrenteGrade("GridPag", "Líquido", "3460.72");
    	
    	// Faz o processamento da baixa
    	SistemaSenior.clicar("Processar");    	
    	SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma Baixa de Títulos?", "&Sim");
    	SistemaSenior.conferirCaixaMensagem("Advertência", "Baixas processadas com sucesso!", "&OK");
    	
    	// Fecha tela de baixa
    	MetodosComuns.fecharTela("F501BCO_FPCP");

    	
  	// Valida titulo a receber 
        SistemaSeniorComTransacao.executarSQLQuery("SELECT 1 "
        		+ "                                   FROM E301TCR "
        		+ "                                  WHERE CODEMP = 1 "
        		+ "                                    AND CODFIL = 1 "
        		+ "                                    AND NUMTIT = '933885-01' "
        		+ "                                    AND CODTPT = 'DUP' "
        		+ "                                    AND CODCLI = 107863 "
        		+ "                                    AND SITTIT = 'LM' ", 1);    	
    	    	
    	
        SistemaSeniorComTransacao.executarSQLQuery("SELECT 2 "
        		+ "                                   FROM E301MCR "
        		+ "                                  WHERE CODEMP = 1 "
        		+ "                                    AND CODFIL = 1 "
        		+ "                                    AND NUMTIT = '933885-01' "
        		+ "                                    AND CODTPT = 'DUP' "
        		+ "                                    AND CODTNS = '90359' "
        		+ "                                    AND DATMOV = ? ", 1,
        		SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));     	
    }
   
}
