package senior.erp.testes.cadastros.controladoria;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.senior.framework.testes.CaixaAtribuicao;
import com.senior.framework.testes.SistemaSenior;
import com.senior.framework.testes.SistemaSeniorComTransacao;
import com.senior.framework.testes.Tecla;

import senior.erp.MetodosComunsNucleo;
import senior.erp.SystemName;
import senior.erp.SystemUsers;

public class Teste002_AlteracaoICMSEsp {
 	
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

	@Test 
	public void testScenario01(){ 
		SistemaSenior.abrirTela("NF019TIE");
		SistemaSenior.marcarCaixaAtribuicao("opAlterar");
		SistemaSenior.teclar(Tecla.TAB);
		SistemaSenior.preencherCampo("ECodTic", "001");
		SistemaSenior.preencherCampo("ECodFil", "1");
		SistemaSenior.clicar("Mostrar");//&Mostrar
		SistemaSenior.preencherLinhaCorrenteGrade("GridIcm", "ICMS Sai Contrib.","5","ICMS Sai Contrib.","5,00");
		SistemaSenior.preencherLinhaCorrenteGrade("GridIcm", "ICMS Sai Não Contrib.","5","ICMS Sai Não Contrib.","5,00");
		SistemaSenior.preencherLinhaCorrenteGrade("GridIcm", "ICMS Ent Contrib.","4,00");
		SistemaSenior.preencherLinhaCorrenteGrade("GridIcm", "ICMS Ent Não Contrib.","0,00");
		SistemaSenior.preencherLinhaCorrenteGrade("GridIcm", "Mensagem","85");
		SistemaSenior.preencherLinhaCorrenteGrade("GridIcm", "Mensagem 2","85");
		SistemaSenior.preencherLinhaCorrenteGrade("GridIcm", "Mensagem 3","85");
		SistemaSenior.preencherLinhaCorrenteGrade("GridIcm","Sel.",CaixaAtribuicao.MARCADO);
		SistemaSenior.clicar("Processar");//&Processar
		SistemaSenior.conferirCaixaMensagem("Confirmação", "Confirma processamento?", "&Sim");
		SistemaSenior.conferirCaixaMensagem("Advertência", "Processado com sucesso!", "&Ok");
		SistemaSenior.clicar("Sair");// &Sair
		SistemaSenior.conferirCaixaMensagem("Confirmação", "Deseja realmente sair?", "&Sim");
	}  
}