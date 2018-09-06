package senior.erp.testes.cadastros.financas;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.senior.framework.testes.SistemaSenior;
import com.senior.framework.testes.SistemaSeniorComTransacao;
import com.senior.framework.testes.Tecla;

import senior.erp.MetodosComunsNucleo;
import senior.erp.SystemName;
import senior.erp.SystemUsers;

public class TCDAFI0001_CadastroDeIndice {

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
	 * Atualizar indice da APOP 
	 */
	@Test
	public void TCDAFI0001_0001() {
		SistemaSenior.abrirTela("F031AIM");
		SistemaSenior.preencherCampo("ECodMoe", "009", Tecla.TAB);
    	SistemaSenior.teclar(1, Tecla.TAB);		
		SistemaSenior.clicar("EMesBas");				
		SistemaSenior.preencherCampo("EMesBas", "21/07/2018", Tecla.TAB);
		SistemaSenior.preencherCampo("ENumArb", "1188", Tecla.TAB);		
		SistemaSenior.clicar("BT_MOSTRAR");			
	/*	SistemaSenior.clicar(Form310PRBComponentNames.BT_PROCESSAR);	
    SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma processamento?", "&Sim");	
    	SistemaSenior.conferirCaixaMensagem("Advertência", "Títulos selecionados foram cancelados com sucesso!", "&OK");    	
		TCBaseERP.fecharTela(Form310PRBComponentNames.FR_NOME);	*/
	}
	
	/**
	 *  
	 */
	@Test
	public void testeCriado() {
		SistemaSenior.clicar("BT_MOSTRAR");			
	/*	SistemaSenior.clicar(Form310PRBComponentNames.BT_PROCESSAR);	
    SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma processamento?", "&Sim");	
    	SistemaSenior.conferirCaixaMensagem("Advertência", "Títulos selecionados foram cancelados com sucesso!", "&OK");    	
		TCBaseERP.fecharTela(Form310PRBComponentNames.FR_NOME);	*/
	}
}
	
	
