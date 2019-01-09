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


public class TCFPCP0002_BaixasTitulosCP_Dinheiro_Cheque {

	@BeforeClass  /** Inicializa o sistema. */
	public static void setUpClass() {
		SistemaSenior.iniciarSistema(SystemName.SAPIENS, SystemUsers.SUPORTE);
	}
	
	@AfterClass  /** Finaliza o sistema. */
	public static void tearDownClass() {
		SistemaSenior.finalizarSistema();
	}
	
	@Before     /** Inicializa transação para cada cenário de teste. */
	public void setUp() {
		SistemaSeniorComTransacao.iniciarTransacao();
	}
	
	@After     /** Reverte transação para cada cenário de teste. */
	public void tearDown() {
		MetodosComunsNucleo.reverterTransacao();
	}
	
	
	private void criarTituloCP(){
		// CRIA TITULO
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
	}



	/**
	 * Cenário que trata a baixa de titulos por lote automática do contas a pagar - Dinheiro;
	 */
	@Test
	public void teste0001_BaixaLoteAutomaticaCP_Dinheiro() {
		
		MetodosComuns.selecionarEmpresaFilial(1, 1);
	
		criarTituloCP();
		
		// BAIXA TITULO 
		SistemaSenior.abrirTela("F501LOT_FPCP");
		
		SistemaSenior.preencherCampo("ENumCco", "01BB8047-0", Tecla.TAB); 
		SistemaSenior.preencherCampo("ECodTns", "90650", Tecla.TAB); 
		
		SistemaSenior.clicar("Selecao");
		SistemaSenior.preencherCampo("ECodFil", "1"); 
		SistemaSenior.preencherCampo("ECodFor", "116806"); 
		SistemaSenior.preencherCampo("ENumTit", "TESTEF001");
		SistemaSenior.preencherCampo("ECodTpt", "REC");
		SistemaSenior.clicar("Ok");
		SistemaSenior.clicar("Mostrar");

		SistemaSenior.clicar("Processar");
		SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma Baixa de Títulos?", "Sim");
		SistemaSenior.conferirCaixaMensagem("Confirmação", "Baixa(s) processada(s) com sucesso. Lote Gerado: 09012019F501LOT5. Deseja imprimir comprovante?", "&Não");
		 
		SistemaSenior.clicar("Processar");
		SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma o processamento?", "Sim");
		SistemaSenior.conferirCaixaMensagem("Advertência", "Preparações processadas com sucesso!", "OK");		
		SistemaSenior.clicar("Sair");
		
		MetodosComuns.fecharTela("F501LOT_FPCP");

		SistemaSeniorComTransacao.executarSQLQuery("SELECT 1 FROM E501TCP WHERE CODEMP = 1 AND CODFIL = 1 AND NUMTIT = 'TESTEF001' AND CODTPT = 'REC' AND CODFOR = 116806 AND SITTIT = 'LQ' AND " + "ULTPGT = ? AND VLRABE = 0", 1, SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
		SistemaSeniorComTransacao.executarSQLQuery("SELECT 2 FROM E501MCP WHERE CODEMP = 1 AND CODFIL = 1 AND NUMTIT = 'TESTEF001' AND CODTPT = 'REC' AND CODFOR = 116806 AND CODTNS = '90550' AND " + "DATMOV = ? AND DATPGT = ? AND TNSCXB = '90650' AND DATLIB = ? AND VLRLIQ = 1000.00 AND NUMCCO = '01BB8047-0'", 1, SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()), SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()), SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
	
	}
	
	/**
	 * Cenário que trata a baixa de titulos por lote automática do contas a pagar - Cheque;
	 */
	@Test
	public void teste0001_BaixaLoteAutomaticaCP_Cheque() {
		
		MetodosComuns.selecionarEmpresaFilial(1, 1);
	
		criarTituloCP();
		
		// BAIXA TITULO 
		SistemaSenior.abrirTela("F501LOT_FPCP");
		
		SistemaSenior.preencherCampo("ENumCco", "01BB8047-0", Tecla.TAB); 
		SistemaSenior.preencherCampo("ECodTns", "90651", Tecla.TAB); 
		
		SistemaSenior.clicar("Selecao");
		SistemaSenior.preencherCampo("ECodFil", "1"); 
		SistemaSenior.preencherCampo("ECodFor", "116806"); 
		SistemaSenior.preencherCampo("ENumTit", "TESTEF001");
		SistemaSenior.preencherCampo("ECodTpt", "REC");
		SistemaSenior.clicar("Ok");
		SistemaSenior.clicar("Mostrar");

		SistemaSenior.clicar("Processar");
		SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma Baixa de Títulos?", "Sim");
		SistemaSenior.conferirCaixaMensagem("Confirmação", "Baixa(s) processada(s) com sucesso. Lote Gerado: ?. Deseja imprimir comprovante?", "&Não");
		 
		SistemaSenior.clicar("Processar");
		SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma o processamento?", "Sim");
		SistemaSenior.conferirCaixaMensagem("Advertência", "Preparações processadas com sucesso!", "OK");		
		SistemaSenior.clicar("Sair");
		
		MetodosComuns.fecharTela("F501LOT_FPCP");

		SistemaSeniorComTransacao.executarSQLQuery("SELECT 1 FROM E501TCP WHERE CODEMP = 1 AND CODFIL = 1 AND NUMTIT = 'TESTEF001' AND CODTPT = 'REC' AND CODFOR = 116806 AND SITTIT = 'LQ' AND " + "ULTPGT = ? AND VLRABE = 0", 1, SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
		SistemaSeniorComTransacao.executarSQLQuery("SELECT 2 FROM E501MCP WHERE CODEMP = 1 AND CODFIL = 1 AND NUMTIT = 'TESTEF001' AND CODTPT = 'REC' AND CODFOR = 116806 AND CODTNS = '90550' AND " + "DATMOV = ? AND DATPGT = ? AND TNSCXB = '90651' AND DATLIB = ? AND VLRLIQ = 1000.00 AND NUMCCO = '01BB8047-0'", 1, SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()), SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()), SistemaSenior.parametroSql(TipoParametroSQL.DATE, SistemaSenior.dataAtual()));
	}

}