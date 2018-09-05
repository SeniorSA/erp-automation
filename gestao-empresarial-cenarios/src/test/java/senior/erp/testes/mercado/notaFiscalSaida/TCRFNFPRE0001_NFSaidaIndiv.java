package senior.erp.testes.mercado.notaFiscalSaida;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.senior.framework.testes.SistemaSenior;
import com.senior.framework.testes.SistemaSeniorComTransacao;

import senior.erp.SystemName;
import senior.erp.SystemUsers;
import senior.erp.MetodosComuns;

public class TCRFNFPRE0001_NFSaidaIndiv {

	/**
	 * Inicializa o sistema.
	 */
	@BeforeClass
	public static void setUpClass() {
		SistemaSenior.iniciarSistema(SystemName.SAPIENS, SystemUsers.WURSCHKE);
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
	}
	
	/**
	 * Teste de devolucao de NFCE
	 */
	
	@Test
	public void testecenario001(){
		
		MetodosComuns.selecionarEmpresaFilial(1, 10);
		SistemaSenior.abrirTela("F140PRE_RFNF");
		
	}

}
