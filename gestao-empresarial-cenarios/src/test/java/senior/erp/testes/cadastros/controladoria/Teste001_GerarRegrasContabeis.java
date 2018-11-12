package senior.erp.testes.cadastros.controladoria;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.senior.framework.testes.SistemaSenior;
import com.senior.framework.testes.SistemaSeniorComTransacao;

import senior.erp.MetodosComunsNucleo;
import senior.erp.SystemName;
import senior.erp.SystemUsers;

public class Teste001_GerarRegrasContabeis {

	/** Inicializa o sistema. */
	@BeforeClass
	public static void setUpClass() {
		SistemaSenior.iniciarSistema(SystemName.SAPIENS, SystemUsers.SUPORTE);
	}

	/** Finaliza o sistema. */
	@AfterClass
	public static void tearDownClass() {
		SistemaSenior.finalizarSistema();
	}

	/** Inicializa transação para cada cenário de teste. */
	@Before
	public void setUp() {
		SistemaSeniorComTransacao.iniciarTransacao();
	}

	/** Reverte transação para cada cenário de teste. */
	@After
	public void tearDown() {
		MetodosComunsNucleo.reverterTransacao();
	}

	@Test
	public void testScenario01() {
		SistemaSenior.abrirTela("F048FCT");
		SistemaSenior.selecionarGuia("Tabulador", "Itens");
		SistemaSenior.clicar("BtnGerar");// &Gerar Regra
		SistemaSenior.clicar("Processar");// &Processar
		SistemaSenior.clicar("button1");// Este e um campo de mensagem que foi clicado em 'Sim' ou 'Nao'.
		SistemaSenior.fecharTela("F048FCT");
	}

}