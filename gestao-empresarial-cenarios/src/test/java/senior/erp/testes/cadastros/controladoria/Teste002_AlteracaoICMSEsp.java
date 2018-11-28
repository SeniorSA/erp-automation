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

	/** Inicializa transa��o para cada cen�rio de teste. */
	@Before
	public void setUp() {
		SistemaSeniorComTransacao.iniciarTransacao();
	}

	/** Reverte transa��o para cada cen�rio de teste. */
	@After
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
SistemaSenior.preencherLinhaCorrenteGrade("GridIcm", "ICMS Sai N�o Contrib.","5","ICMS Sai N�o Contrib.","5,00");
SistemaSenior.preencherLinhaCorrenteGrade("GridIcm", "ICMS Ent Contrib.","4,00");
SistemaSenior.preencherLinhaCorrenteGrade("GridIcm", "ICMS Ent N�o Contrib.","0,00");
SistemaSenior.preencherLinhaCorrenteGrade("GridIcm", "Mensagem","85");
SistemaSenior.preencherLinhaCorrenteGrade("GridIcm", "Mensagem 2","85");
SistemaSenior.preencherLinhaCorrenteGrade("GridIcm", "Mensagem 3","85");
SistemaSenior.preencherLinhaCorrenteGrade("GridIcm","Sel.",CaixaAtribuicao.MARCADO);
SistemaSenior.clicar("Processar");//&Processar
SistemaSenior.conferirCaixaMensagem("Confirma��o", "Confirma processamento?", "&Sim");
SistemaSenior.conferirCaixaMensagem("Advert�ncia", "Processado com sucesso!", "&Ok");
SistemaSenior.clicar("Sair");// &Sair
SistemaSenior.conferirCaixaMensagem("Confirma��o", "Deseja realmente sair?", "&Sim");

} 

 
}