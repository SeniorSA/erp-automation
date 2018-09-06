package senior.erp.testes.financas.cobrancaEscritural;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.senior.framework.testes.SistemaSenior;
import com.senior.framework.testes.SistemaSeniorComTransacao;
import com.senior.framework.testes.Tecla;

import senior.erp.MetodosComuns;
import senior.erp.MetodosComunsNucleo;
import senior.erp.SystemName;
import senior.erp.SystemUsers;
import senior.erp.componentNames.financas.Form310PRBComponentNames;

public class TCFRCRP0001_RemessaCR {

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
	 * Gerar a remessa da cobrança escritural
	 */
	@Test
	public void teste0001_remessa() {
		SistemaSenior.abrirTela(Form310PRBComponentNames.FR_NOME);
		SistemaSenior.preencherCampo(Form310PRBComponentNames.FD_PORTADOR, "001", Tecla.TAB);
		SistemaSenior.preencherCampo(Form310PRBComponentNames.FD_CARTEIRA, "03", Tecla.TAB);
		SistemaSenior.selecionarItem("ESelMod", "Remessa nosso nº já calculado");
    	SistemaSenior.teclar(1, Tecla.TAB);		
		SistemaSenior.clicar(Form310PRBComponentNames.BT_SELECAO);				
		SistemaSenior.preencherCampo("SPRCodFil", "", Tecla.TAB);
		SistemaSenior.clicar(Form310PRBComponentNames.BT_OK);	
		SistemaSenior.clicar(Form310PRBComponentNames.BT_MOSTRAR);			
		SistemaSenior.clicar(Form310PRBComponentNames.BT_PROCESSAR);	
    	SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma processamento?", "&Sim");	
    	SistemaSenior.conferirCaixaMensagem("Advertência", "Títulos selecionados foram processados com Nº Remessa 1189! Deseja visualizar o arquivo ?", "&Não");    	
		MetodosComuns.fecharTela(Form310PRBComponentNames.FR_NOME);
	}
	
	/**
	 * Estornar a remessa da cobrança escritural
	 */
	@Test
	public void teste0002_estornoremessa() {
		SistemaSenior.abrirTela(Form310PRBComponentNames.FR_NOME);
		SistemaSenior.preencherCampo(Form310PRBComponentNames.FD_PORTADOR, "001", Tecla.TAB);
		SistemaSenior.preencherCampo(Form310PRBComponentNames.FD_CARTEIRA, "03", Tecla.TAB);
		SistemaSenior.selecionarItem("ESelMod", "Remessa nosso nº já calculado");
    	SistemaSenior.teclar(1, Tecla.TAB);		
		SistemaSenior.clicar(Form310PRBComponentNames.BT_SELECAO);				
		SistemaSenior.preencherCampo("SPRCodFil", "", Tecla.TAB);
		SistemaSenior.selecionarItem("Mostrar", "&9 - Cancelamento da Remessa (Completo)");
		SistemaSenior.clicar(Form310PRBComponentNames.BT_OK);	
		SistemaSenior.preencherCampo("ENumArb", "1188", Tecla.TAB);		
		SistemaSenior.clicar(Form310PRBComponentNames.BT_MOSTRAR);			
		SistemaSenior.clicar(Form310PRBComponentNames.BT_PROCESSAR);	
    	SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma processamento?", "&Sim");	
    	SistemaSenior.conferirCaixaMensagem("Advertência", "Títulos selecionados foram cancelados com sucesso!", "&OK");    	
		MetodosComuns.fecharTela(Form310PRBComponentNames.FR_NOME);
	}	
	
}
