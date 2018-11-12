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

public class Teste003_ParametrosParaImpostos {

	/*** Inicializa o sistema. */
	@BeforeClass
	public static void setUpClass() {
		SistemaSenior.iniciarSistema(SystemName.SAPIENS, SystemUsers.SUPORTE);
	}

	/*** Finaliza o sistema. */
	@AfterClass
	public static void tearDownClass() {
		SistemaSenior.finalizarSistema();
	}

	/*** Inicializa transação para cada cenário de teste. */
	@Before
	public void setUp() {
		SistemaSeniorComTransacao.iniciarTransacao();
	}

	/*** Reverte transação para cada cenário de teste. */
	@After
	public void tearDown() {
		MetodosComunsNucleo.reverterTransacao();
	}

	@Test
	public void testScenario01() {
		SistemaSenior.abrirTela("NF661PAP_CIOF");
		SistemaSenior.preencherCampo("ECodEmp", "1");
		SistemaSenior.preencherCampo("ECodFil", "20");
		SistemaSenior.preencherCampo("ECodImp", "ICM");
		SistemaSenior.preencherCampo("EDatApi", "01/2018");
		SistemaSenior.clicar("Mostrar");// &Mostrar
		SistemaSenior.preencherNovaLinhaGrade("GridParametros", "Tipo Ajuste", "5", "Descrição", "", "Cód. Dis. Fis.",
				"68", "Forma Busca Dados", "0", "Regra", "0", "Utiliza RPA", "N");
		SistemaSenior.preencherNovaLinhaGrade("GridParametros", "Tipo Ajuste", "1", "Descrição", "", "Cód. Dis. Fis.",
				"59", "Forma Busca Dados", "10", "Regra", "0", "Utiliza RPA", "N");
		SistemaSenior.fecharTela("F661PAP");

		
		SistemaSeniorComTransacao.executarSQLQuery("select * from e661pap "
				+ "where CodEmp = 1 "
				+ "and CodFil = 20 "
				+ "and codImp = 'ICM' "
				+ "and coddfs = 68 "
				+ "and DatApi = '01/01/2018'",
				1);
		
		SistemaSeniorComTransacao.executarSQLQuery("select * from e661pap "
				+ "where CodEmp = 1 "
				+ "and CodFil = 20 "
				+ "and codImp = 'ICM' "
				+ "and coddfs = 59 "
				+ "and codfbd = 10"
				+ "and DatApi = '01/01/2018'",
				1);
	}

}
