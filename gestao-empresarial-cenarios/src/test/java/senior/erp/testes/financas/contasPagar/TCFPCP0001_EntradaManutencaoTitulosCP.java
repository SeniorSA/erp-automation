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


public class TCFPCP0001_EntradaManutencaoTitulosCP {

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
	 *  Inclui um titulo no contas a pagar via tela F501TCP_FPCP
	 */
    @Test
    public void testScenario0001() {
    	
    	MetodosComunsNucleo.selecionarEmpresaFilial(1, 10);
        SistemaSenior.abrirTela("F501TCP_FPCP");
        
                    
        
        SistemaSenior.preencherCampo("DENumTit1", "TESTEF001", Tecla.TAB); 
        SistemaSenior.preencherCampo("DECodTpt1", "REC", Tecla.TAB); 
        SistemaSenior.preencherCampo("DECodFor1", "116806", Tecla.TAB); 
        SistemaSenior.teclar(1, Tecla.TAB);
        SistemaSenior.preencherCampo("DMObsTcp1", "Título de teste Eclipse", Tecla.TAB);
        SistemaSenior.teclar(2, Tecla.TAB);
        SistemaSenior.preencherCampo("DECodNtg1", "56", Tecla.TAB);
        SistemaSenior.teclar(3, Tecla.TAB);
        SistemaSenior.preencherCampo("DECodCcu1", "42", Tecla.TAB);
        
        SistemaSenior.teclar(3, Tecla.TAB);
        
        SistemaSenior.preencherCampo("DEDatEmi1", SistemaSenior.dataAtual(), Tecla.TAB); 
        SistemaSenior.preencherCampo("DEDatEnt1", SistemaSenior.dataAtual(), Tecla.TAB); 
        SistemaSenior.preencherCampo("DEVctOri1",SistemaSenior.calcularData("dd/MM/yyyy", "d+10"), Tecla.TAB); 
        SistemaSenior.preencherCampo("DEVlrOri1", "1000.00"); //Valor Original
        SistemaSenior.teclar(26, Tecla.TAB);
        MetodosComuns.fecharTela("F501TCP_FPCP");
        
        
        SistemaSeniorComTransacao.executarSQLQuery("SELECT 1 "
        		+ "                                   FROM E501TCP "
        		+ "                                  WHERE CODEMP = 1 "
        		+ "                                    AND CODFIL = 10 "
        		+ "                                    AND NUMTIT = 'TESTEF001' "
        		+ "                                    AND CODTPT = 'REC' "
        		+ "                                    AND CODFOR = 116806 "
        		+ "                                    AND CODTNS = '90500' "
        		+ "                                    AND SITTIT = 'AB' "
        		+ "                                    AND DATEMI = ? "
        		+ "                                    AND DATENT = ? "
        		+ "                                    AND VCTORI = ? "
        		+ "                                    AND VLRORI = 1000.00 "
        		+ "                                    AND VCTPRO = ? "
        		+ "                                    AND VLRABE = 1000.00   "
        		+ "                                    AND CODPOR = 'UN10' "
        		+ "                                    AND CODCRT = '99' ", 1, 
        		SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()), 
        		SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()), 
        		SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.calcularData("dd/MM/yyyy", "d+10")), 
        		SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.calcularData("dd/MM/yyyy", "d+10")));
        
        
        SistemaSeniorComTransacao.executarSQLQuery("SELECT 2 "
        		+ "                                   FROM E501MCP "
        		+ "                                  WHERE CODEMP = 1 "
        		+ "                                    AND CODFIL = 10 "
        		+ "                                    AND NUMTIT = 'TESTEF001' "
        		+ "                                    AND CODTPT = 'REC' "
        		+ "                                    AND CODFOR = 116806 "
        		+ "                                    AND CODTNS = '90500' "
        		+ "                                    AND DATMOV = ? "
        		+ "                                    AND VCTPRO = ? "
        		+ "                                    AND VLRABE = 1000.00 "
        		+ "                                    AND VLRMOV = 1000.00 "
        		+ "                                    AND VLRLIQ = 1000.00 "
        		+ "                                    AND CODPOR = 'UN10' "
        		+ "                                    AND CODCRT = '99'", 1, 
        		SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()), 
        		SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.calcularData("dd/MM/yyyy", "d+10")));
        
       
    }
}
