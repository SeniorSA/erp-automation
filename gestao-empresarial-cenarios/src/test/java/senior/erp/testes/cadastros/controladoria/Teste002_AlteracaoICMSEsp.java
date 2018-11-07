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
public void testScenario01(){ 
SistemaSenior.abrirTela("NF019TIE");
SistemaSenior.selecionarItem("opAlterar", "&Alterar");
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
SistemaSenior.posicionarLinhaGradePorValor("GridIcm","Sel.",CaixaAtribuicao.MARCADO,"Filial","1","Estado","AC","ICMS Sai Contrib.","5,00","ICMS Sai Não Contrib.","5,00","ICMS Ent Contrib.","4,00","ICMS Ent Não Contrib.","0,00","Mensagem","85","Mensagem 2","85","Mensagem 3","85","Mensagem 4","0");
SistemaSenior.clicar("Processar");//&Processar
SistemaSenior.clicar("button1");//Este e um campo de mensagem que foi clicado em 'Sim' ou 'Nao'.
SistemaSenior.clicar("button1");//Este e um campo de mensagem que foi clicado em 'Sim' ou 'Nao'.
SistemaSenior.selecionarItem("opInserir", "&Inserir");
} 

 
}