package senior.erp.testes.agronegocio;

import org.junit.*;
import com.senior.framework.testes.*;
import senior.erp.TCBaseERPNucleo;
import senior.erp.MetodosComuns;
import senior.erp.SystemName;
import senior.erp.SystemUsers;

public class Teste01_EntradaRomaneioVariavel {

	@BeforeClass
	public static void preExecucaoCasodeTeste() {
		SistemaSenior.iniciarSistema(SystemName.SAPIENS, SystemUsers.SUPORTE);
	}

	@AfterClass
	public static void posExecucaoCasodeTeste() {
		SistemaSenior.finalizarSistema();
	}

	@Before
	public void preExecucaoCenario() {
		SistemaSeniorComTransacao.iniciarTransacao();
	}

	@After
	public void posExecucaoCenario() {
		MetodosComuns.reverterTransacao();
	}-

	@Test
	public void testScenario01() {
		SistemaSenior.abrirTela("MFSelFil");
		SistemaSenior.clicar("BtnOk");// &Ok
		SistemaSenior.abrirTela("USERMENU_0_1_NF460PFO_SCTR");
		SistemaSenior.preencherCampo("DECodFor", "120.034");
		SistemaSenior.teclar(Tecla.TAB);
		SistemaSenior.preencherCampo("DEObjCtr", " ");
		SistemaSenior.teclar(3, Tecla.TAB);
		SistemaSenior.preencherCampo("DETipVlz", "2");
		SistemaSenior.teclar(33, Tecla.TAB);
		SistemaSenior.preencherCampo("DECodMoe", "120");
		SistemaSenior.teclar(Tecla.TAB);
		SistemaSenior.preencherCampo("DECodApl", "2");
		SistemaSenior.teclar(13, Tecla.TAB);
		SistemaSenior.preencherCampo("DECodApl", "1");
		SistemaSenior.teclar(Tecla.TAB);
		SistemaSenior.preencherCampo("DEIniVig", "20/11/2018");
		SistemaSenior.teclar(Tecla.TAB);
		SistemaSenior.preencherCampo("DEFimVig", "05/12/2018");
		SistemaSenior.teclar(16, Tecla.TAB);
		SistemaSenior.preencherCampo("DECodCpg", "015");
		SistemaSenior.teclar(2, Tecla.TAB);
		SistemaSenior.preencherCampo("DECodTra", "100");
		SistemaSenior.teclar(Tecla.TAB);
		SistemaSenior.preencherCampo("DETnsPro", "90421");
		SistemaSenior.teclar(Tecla.TAB);
		SistemaSenior.preencherCampo("DECodSaf", "2018/2019");
		SistemaSenior.teclar(7, Tecla.TAB);
		SistemaSenior.preencherCampo("DEObsMot", " ");
		SistemaSenior.preencherLinhaCorrenteGrade("GridCcp", "Transa��o", "90421", "Produto", "10006.0001", "Deriva��o",
				"001", "Complemento", "MILHO EM GRAOS COMERCIAL TIPO 1 - PADRAO", "Dep�sito", "D004-04",
				"UM Fornecedor", "K.G", "Qtde UM Fornecedor", "50000", "Qtde UM Fornecedor", "50.000,00000",
				"Pre�o UM Fornecedor", "1", "Pre�o UM Fornecedor", "1,0000000000", "Situa��o", "A");
		SistemaSenior.clicar("BtAcrescFinanc");// Acr�sc. Financ. (&7)
		SistemaSenior.posicionarLinhaGradePorValor("GridIxA", "Sel.", CaixaAtribuicao.DESMARCADO, "Item Classifica��o",
				"101", "Descri��o (Item Classifica��o)", "Complemento de Pre�o", "Sit.", "I", "Usu�rio Gera��o", "0");
		SistemaSenior.posicionarLinhaGradePorValor("GridIxA", "Sel.", CaixaAtribuicao.DESMARCADO, "Item Classifica��o",
				"101", "Descri��o (Item Classifica��o)", "Complemento de Pre�o", "Sit.", "I", "Usu�rio Gera��o", "0");
		SistemaSenior.preencherNovaLinhaGrade("GridFxI", "Sel.", CaixaAtribuicao.MARCADO, "Tipo", "V",
				"Valor Acr�sc. Finan.", "1", "Valor Acr�sc. Finan.", "1,00000", "Agrupar itens", "N",
				"Uni. Acr�s. Financ.", "K.G");
		SistemaSenior.preencherLinhaCorrenteGrade("GridFxI", "Uni. Acr�s. Financ.", "SC", "Valor Acr�sc. Finan.", "1",
				"Tipo", "V");
		SistemaSenior.posicionarLinhaGradePorValor("GridFxI", "Tipo", "V", "Descri��o (Tipo)", "Valor",
				"Uni. Acr�s. Financ.", "SC", "Valor Acr�sc. Finan.", "1,00000", "% Acr�sc. Financ.", "0,0000",
				"Agrupar itens", "N", "Sit.", "A", "Tipo Faturamento", "2", "Descri��o (Tipo Faturamento)",
				"Na fixa��o");
		SistemaSenior.clicar("BtnProcessar");// &Processar
		SistemaSenior.clicar("button1");// Este e um campo de mensagem que foi clicado em 'Sim' ou 'Nao'.
		SistemaSenior.clicar("button1");// Este e um campo de mensagem que foi clicado em 'Sim' ou 'Nao'.
		SistemaSenior.clicar("BtnSair");// &Sair
		SistemaSenior.preencherCampo("DEObjCtr", " ");
		SistemaSenior.clicar("Aprovar");// Liber&ar
		SistemaSenior.clicar("button1");// Este e um campo de mensagem que foi clicado em 'Sim' ou 'Nao'.
		SistemaSenior.clicar("button1");// Este e um campo de mensagem que foi clicado em 'Sim' ou 'Nao'.
		SistemaSenior.preencherCampo("DEObjCtr", " ");
		SistemaSenior.clicar("Aprovar");// &Aprovar
		SistemaSenior.clicar("button1");// Este e um campo de mensagem que foi clicado em 'Sim' ou 'Nao'.
		SistemaSenior.clicar("button1");// Este e um campo de mensagem que foi clicado em 'Sim' ou 'Nao'.
		SistemaSenior.preencherCampo("DEObjCtr", " ");
	}

}